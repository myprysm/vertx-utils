/*
 * Copyright 2018 the original author or the original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.myprysm.vertx.service;

import fr.myprysm.vertx.core.StarterVerticle;
import fr.myprysm.vertx.core.utils.ClassUtils;
import fr.myprysm.vertx.core.utils.Utils;
import fr.myprysm.vertx.validation.ValidationResult;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.CompletableHelper;
import io.vertx.reactivex.SingleHelper;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.eventbus.MessageConsumer;
import io.vertx.reactivex.core.impl.AsyncResultSingle;
import io.vertx.reactivex.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;
import io.vertx.servicediscovery.types.EventBusService;
import io.vertx.serviceproxy.ServiceBinder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class ServiceVerticle extends AbstractVerticle {
    private static final String SERVICE_DISCOVERY_NAME = "service-verticle:" + Utils.instanceId;

    private List<MessageConsumer<JsonObject>> binds = new CopyOnWriteArrayList<>();
    private List<Service> services = new CopyOnWriteArrayList<>();
    private List<Record> records = new CopyOnWriteArrayList<>();

    private ServiceVerticleOptions config;
    private ServiceDiscovery discovery;


    List<MessageConsumer<JsonObject>> getBinds() {
        return Collections.unmodifiableList(binds);
    }

    List<Service> getServices() {
        return Collections.unmodifiableList(services);
    }

    List<Record> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public void start(Future<Void> startFuture) {
        validateConfiguration()
                .andThen(Completable.defer(this::configure))
                .subscribe(CompletableHelper.toObserver(startFuture));
    }


    /**
     * Validates that all the services are valid to start.
     *
     * @return a completable that finished when all the services have been declared valid.
     */
    private Completable validateConfiguration() {
        ValidationResult result = ServiceVerticleOptions.validate(config());

        if (result.isValid()) {
            config = new ServiceVerticleOptions(config());
            return Completable.complete();
        } else {
            log.error("Some service is invalid: {}", result.getReason().get());
        }
        return Completable.error(result.toException());
    }

    /**
     * Starts the service discovery then the configured services
     *
     * @return a completable that finishes once discovery and service are started
     */
    private Completable configure() {
        return configureDiscovery()
                .andThen(Observable.fromIterable(config.getServices().entrySet()))
                .map(entry -> entry.getValue().setName(entry.getKey()))
                .flatMap(this::prepareService)
                .flatMapCompletable(this::startService);
    }

    /**
     * Configure discovery.
     * Wait until fully started before allowing continuation.
     *
     * @return a completable that finishes when discovery is fully started
     */
    private Completable configureDiscovery() {
        return Completable.create(emitter ->
                ServiceDiscovery.create(vertx, new ServiceDiscoveryOptions().setName(SERVICE_DISCOVERY_NAME), discovery -> {
                    this.discovery = discovery;
                    emitter.onComplete();
                })
        );

    }

    /**
     * Prepares a service for startup from the options.
     *
     * @param options the service options
     * @param <T>     the facade
     * @return A triple with the options, the facade and a provider for the service
     */
    private <T> Observable<Triple<ServiceOptions, Class<T>, Provider<T>>> prepareService(ServiceOptions options) {
        Class<T> facade = getClass(options.getFacade());
        Provider<T> provider;
        if (options.getFactoryMethod() != null) {
            Method factoryMethod = getFactoryMethod(options, facade);
            provider = new MethodProvider<>(factoryMethod);
        } else {
            provider = new ImplementationProvider<>(getClass(options.getImplementation()));
        }

        return Observable.just(Triple.of(options, facade, provider));
    }


    /**
     * Get and unwrap safely the class.
     *
     * @param clazz the class name
     * @param <T>   the expected return type
     * @return the class representing the expected return type
     */
    @SuppressWarnings("unchecked")
    private <T> Class<T> getClass(String clazz) {
        return (Class<T>) ClassUtils.getClass(clazz, false);
    }

    /**
     * Find the factory method from configuration.
     * It must accept {@link Vertx} as first parameter and a {@link Handler} as second parameter.
     * The method must be static and public.
     * <p>
     * Throws a {@link NullPointerException} if no method matches.
     *
     * @param options      the service options
     * @param serviceClass the facade class
     * @param <T>          the facade
     * @return the factory method if it has been resolved.
     */
    private <T> Method getFactoryMethod(ServiceOptions options, Class<T> serviceClass) {
        return Arrays.stream(serviceClass.getMethods())
                .filter(method -> options.getFactoryMethod().equals(method.getName()))
                .filter(method -> {
                    Class<?>[] types = method.getParameterTypes();
                    return types.length == 2
                            && Vertx.class.isAssignableFrom(types[0])
                            && Handler.class.isAssignableFrom(types[1]);
                })
                .filter(method -> Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers()))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Unable to find static method " + options.getFactoryMethod() + " on class " + serviceClass.getName()));
    }

    /**
     * Unwraps the {@link Triple} to start effectively the service
     *
     * @param tuple the tuple containing options, service facade class and service provider
     * @param <T>   the service facade
     * @return a completable finished when service is started
     */
    private <T> Completable startService(Triple<ServiceOptions, Class<T>, Provider<T>> tuple) {
        return startService(tuple.getLeft(), tuple.getMiddle(), tuple.getRight());
    }

    /**
     * Starts a service for the requested facade, creating it with the provider.
     *
     * @param options  the service options
     * @param facade   the service facade class
     * @param provider the service provider
     * @param <T>      the service facade
     * @return a completable finished when the service is started, registered and published
     */
    private <T> Completable startService(ServiceOptions options, Class<T> facade, Provider<T> provider) {
        return provider.rxGet(vertx)
                .doOnSuccess(service -> {
                    if (options.getHealthCheck() && service instanceof HealthCheck) {
                        HealthCheck hc = (HealthCheck) service;
                        StarterVerticle.getHealthChecks(vertx)
                                .register(options.getHealthCheckName() != null ? options.getHealthCheckName() : hc.name(), options.getHealthCheckTimeout(), hc.beat());
                    }

                    this.register(options, facade, service);
                })
                .flatMapCompletable(service -> this.publish(options, facade));
    }

    /**
     * Publishes the service facade on the service discovery unless registration or discovery are disabled for th service.
     *
     * @param options the service options
     * @param facade  the service facade class
     * @param <T>     the service facade
     * @return a completable that will publish the service on discovery if requested
     */
    private <T> Completable publish(ServiceOptions options, Class<T> facade) {
        if (options.getRegister() && options.getDiscovery()) {
            return discovery.rxPublish(EventBusService.createRecord(options.getName(), options.getAddress(), facade))
                    .doOnSuccess(record -> {
                        log.info("Service [{}] registered ({})", facade.getName(), record.toJson());
                        records.add(record);
                    })
                    .toCompletable();
        }
        return Completable.complete();
    }

    /**
     * Registers the service on the event bus unless it is not requested.
     *
     * @param options the service options
     * @param facade  the service facade class
     * @param service the service implementation
     * @param <T>     the service facade
     */
    private <T> void register(ServiceOptions options, Class<T> facade, T service) {
        if (options.getRegister()) {
            binds.add(new MessageConsumer<>(new ServiceBinder(vertx.getDelegate())
                    .setAddress(options.getAddress())
                    .register(facade, service)));
            log.info("Service [{}] bound to [{}].", facade.getName(), options.getAddress());
        }
    }

    @Override
    public void stop(Future<Void> stopFuture) {
        Completable.mergeArrayDelayError(
                Observable.fromIterable(records).flatMapCompletable(this::unpublish, true),
                Observable.fromIterable(binds).flatMapCompletable(MessageConsumer::rxUnregister, true),
                Observable.fromIterable(services).flatMapCompletable(Service::close, true)
        ).andThen(Completable.fromAction(discovery::close))
                .subscribe(CompletableHelper.toObserver(stopFuture));
    }

    /**
     * Removes a published record from the service discovery
     *
     * @param record the record to unpublish/withdraw
     * @return the completable task
     */
    private Completable unpublish(Record record) {
        return discovery.rxUnpublish(record.getRegistration())
                .doOnComplete(() ->
                        log.info("Service [{}] unregistered ({}).", record.getMetadata().getString("service.interface"), record.getRegistration())
                );
    }

    /**
     * Asynchronous provider for services.
     *
     * @param <T> the facade
     */
    @FunctionalInterface
    private interface Provider<T> {
        /**
         * Builds the service asynchronously and calls the handler with it once initialized.
         *
         * @param vertx   vertx
         * @param handler the result handler
         */
        void asyncGet(Vertx vertx, Handler<AsyncResult<T>> handler);

        default Single<T> rxGet(io.vertx.reactivex.core.Vertx vertx) {
            return new AsyncResultSingle<>(handler -> asyncGet(vertx.getDelegate(), handler));
        }
    }

    /**
     * Provides a service through default "create" static method on the facade.
     * <p>
     * This is what is brought by Vert.x documentation when creating service & proxies.
     *
     * @param <T> the Facade
     */
    private class MethodProvider<T> implements Provider<T> {
        private final Method method;

        MethodProvider(Method factoryMethod) {
            method = factoryMethod;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void asyncGet(Vertx vertx, Handler<AsyncResult<T>> handler) {
            Single.fromCallable(() -> (T) method.invoke(null, vertx, handler))
                    .subscribe(SingleHelper.toObserver(handler));
        }
    }

    /**
     * Provides a service as a normal object.
     * <p>
     * Handles the startup lifecycle of the {@link Service} interface by:
     * <ol>
     * <li>Creating a new instance with the default constructor</li>
     * <li>Inserting the vertx instance</li>
     * <li>Calling the configuration method</li>
     * </ol>
     *
     * @param <T> the Facade
     */
    private class ImplementationProvider<T> implements Provider<T> {
        private final Class<? extends Service> service;
        private final Class<? extends T> implementation;

        @SuppressWarnings("unchecked")
        private ImplementationProvider(Class<? extends T> implementation) {
            this.service = (Class<? extends Service>) implementation;
            this.implementation = implementation;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void asyncGet(Vertx vertx, Handler<AsyncResult<T>> handler) {
            Single.fromCallable(() -> {
                Service service = this.service.newInstance();
                service.init(ServiceVerticle.this.vertx);
                service.configure()
                        .andThen(Single.just(service))
                        .map(implementation::cast)
                        .subscribe(SingleHelper.toObserver(handler));
                services.add(service);
                return (T) service;
            }).subscribe(SingleHelper.toObserver(handler));

        }
    }
}
