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

package fr.myprysm.vertx.core;

import fr.myprysm.vertx.core.config.ConfigServiceLocalImpl;
import fr.myprysm.vertx.core.reactivex.config.ConfigService;
import fr.myprysm.vertx.core.utils.ClassUtils;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.CompletableHelper;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.healthchecks.HealthChecks;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArrayList;

public class StarterVerticle extends AbstractVerticle {
    public static final String CONFIG_PATH = "path";
    private static final Logger LOG = LoggerFactory.getLogger(StarterVerticle.class);
    private static HealthChecks healthChecks;
    private final CopyOnWriteArrayList<String> deployments = new CopyOnWriteArrayList<>();
    private String path;
    private ConfigService configService;
    private MessageConsumer<JsonObject> configServiceConsumer;

    /**
     * Get the HealthChecks instance.
     * Creates the health checks on first access with the provided vertx instance.
     *
     * @param vertx the vertx instance in case it must be created
     * @return the health checks
     */
    public static HealthChecks getHealthChecks(Vertx vertx) {
        if (healthChecks == null) {
            healthChecks = HealthChecks.create(vertx);
        }
        return healthChecks;
    }

    @Override
    public void start(Future<Void> startFuture) {
        path = config().getString(CONFIG_PATH, "config.yml");
        healthChecks = getHealthChecks(vertx);
        readConfiguration()
                .andThen(Completable.defer(this::scanClasses))
                .andThen(Single.defer(() -> configService.rxGetVerticles()))
                .flatMapObservable(Observable::fromIterable)
                .flatMapCompletable(this::deployVerticle)
                .subscribe(CompletableHelper.toObserver(startFuture));
    }

    /**
     * Perform the classpath scan without blocking the event loop
     *
     * @return the completable task
     */
    private Completable scanClasses() {
        return vertx.rxExecuteBlocking(future -> {
            ClassUtils.getScan();
            future.complete();
        }).toCompletable();
    }


    @Override
    public void stop(Future<Void> stopFuture) {
        Observable.fromIterable(deployments)
                .filter(this::isRunning)
                .flatMapCompletable(vertx::rxUndeploy, true)
                .onErrorComplete()
                .andThen(Completable.fromAction(() -> configServiceConsumer.unregister()))
                .subscribe(CompletableHelper.toObserver(stopFuture));
    }

    private boolean isRunning(String deploymentId) {
        return vertx.deploymentIDs().contains(deploymentId);
    }

    private Completable deployVerticle(VerticleOptions verticleConfig) {
        String verticleClass = verticleConfig.getVerticle();
        DeploymentOptions deploymentOptions = verticleConfig.getOptions();

        return vertx.rxDeployVerticle(verticleClass, deploymentOptions)
                .doOnSuccess(id -> {
                    LOG.info("Deployed {}:{}", verticleClass, id);
                    deployments.add(id);
                })
                .doOnError(throwable -> {
                    LOG.error("Unable to deploy verticle [{}] with config: {}", verticleClass, verticleConfig.toJson());
                    LOG.error("Reason: ", throwable);
                })
                .toCompletable();
    }


    /**
     * Loads the configuration from a file and register the service on the event bus.
     *
     * @return the loaded configuration as a {@link Single}
     */
    private Completable readConfiguration() {
        ConfigServiceLocalImpl realService = new ConfigServiceLocalImpl(path, vertx);
        configService = ConfigService.newInstance(realService);
        return realService.configure()
                .doOnComplete(() -> configServiceConsumer = new ServiceBinder(vertx.getDelegate())
                        .setAddress(fr.myprysm.vertx.core.config.ConfigService.ADDRESS)
                        .register(fr.myprysm.vertx.core.config.ConfigService.class, realService));

    }
}
