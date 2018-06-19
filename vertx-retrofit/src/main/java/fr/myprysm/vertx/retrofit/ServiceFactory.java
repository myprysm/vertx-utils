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

package fr.myprysm.vertx.retrofit;

import fr.myprysm.vertx.json.Json;
import okhttp3.ConnectionPool;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Generic factory to create a Retrofit Service Interface
 * for the configured, baseUrl, apiKey and accessToken.
 */
public final class ServiceFactory {

    public static final Integer DEFAULT_MAX_IDLE_CONNECTIONS = 20;
    public static final Integer DEFAULT_KEEP_ALIVE_DURATION = 2;
    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MINUTES;

    /**
     * Keep a common client to share connection pool across all factory instances.
     * <p>
     * Avoid threading issues, optimizes Retrofit + OkHttp usage.
     * <p>
     * Provide a new connection pool to the builder to change from the default,
     * but this configuration should be way enough for all usages.
     */
    private static final OkHttpClient BASE_CLIENT = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(DEFAULT_MAX_IDLE_CONNECTIONS, DEFAULT_KEEP_ALIVE_DURATION, DEFAULT_TIME_UNIT))
            .build();
    private final Retrofit retrofit;

    private ServiceFactory(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    /**
     * Create a new service factory to call a remote API with {@link Retrofit} and {@link OkHttpClient}.
     * <p>
     * Generated services are async by default, Rx2 call adapter and Jackson are bound.
     *
     * @param baseUrl the base url for generated service interfaces
     * @return the factory
     */
    public static ServiceFactory.Builder forBase(String baseUrl) {
        return new Builder(baseUrl);
    }

    /**
     * Create a new service factory to call a remote API with {@link Retrofit} and {@link OkHttpClient}.
     * <p>
     * Generated services are async by default, Rx2 call adapter and Jackson are bound.
     *
     * @param baseUrl the base url for generated service interfaces
     * @return the factory
     */
    public static ServiceFactory.Builder forBase(HttpUrl baseUrl) {
        return new Builder(baseUrl);
    }

    /**
     * Create a new service instance with the base url configured in the {@link ServiceFactory}
     *
     * @param serviceClass the class of the service interface to generate
     * @param <T>          the service interface
     * @return the service interface
     */
    public <T> T create(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

    /**
     * Builds a new {@link ServiceFactory} for remote http calls
     */
    public static final class Builder {

        private static final String AUTHORIZATION_HEADER = "Authorization";
        private static final String BEARER_HEADER = "Bearer";
        private static final String KEY_PARAM = "key";
        private HttpUrl baseUrl;

        private String accessToken = null;
        private String authorizationHeader = AUTHORIZATION_HEADER;
        private String tokenType = BEARER_HEADER;

        private String apiKey = null;
        private String keyParam = KEY_PARAM;


        /**
         * Constructs a new empty builder
         */
        public Builder() {

        }

        /**
         * Constructs a new builder with provided base url
         *
         * @param baseUrl the base url
         */
        public Builder(String baseUrl) {
            baseUrl(baseUrl);
        }

        /**
         * Constructs a new builder with provided base url
         *
         * @param baseUrl the base url
         */
        public Builder(HttpUrl baseUrl) {
            baseUrl(baseUrl);
        }

        /**
         * Set the base url to the builder.
         * <p>
         * It must must be a valid url.
         *
         * @param baseUrl the base url
         * @return the builder
         */
        public Builder baseUrl(String baseUrl) {
            return baseUrl(HttpUrl.parse(baseUrl));
        }

        /**
         * Set the base url to the builder.
         * <p>
         * It must must be a valid url.
         *
         * @param baseUrl the base url
         * @return the builder
         */
        public Builder baseUrl(HttpUrl baseUrl) {
            if (baseUrl == null) {
                throw new IllegalArgumentException("The provided base URL is not valid");
            }
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Adds an API Key to the builder.
         * <p>
         * It must not be blank.
         *
         * @param apiKey the key
         * @return the builder
         */
        public Builder apiKey(String apiKey) {
            if (apiKey != null && StringUtils.isBlank(apiKey)) {
                throw new IllegalArgumentException("API Key is blank");
            }
            this.apiKey = apiKey;
            return this;
        }

        /**
         * Set the parameter name that will be used with the key.
         * <p>
         * It must not be blank.
         *
         * @param keyParam the parameter name
         * @return the builder
         */
        public Builder keyParam(String keyParam) {
            if (StringUtils.isBlank(keyParam)) {
                throw new IllegalArgumentException("API Key parameter is blank");
            }
            this.keyParam = keyParam;
            return this;
        }

        /**
         * Adds an access token to the builder.
         * <p>
         * It must not be blank.
         *
         * @param accessToken the token
         * @return the builder
         */
        public Builder accessToken(String accessToken) {
            if (accessToken != null && StringUtils.isBlank(accessToken)) {
                throw new IllegalArgumentException("Access token is blank");
            }

            this.accessToken = accessToken;
            return this;
        }

        /**
         * Set the authorization header name to the builder.
         * <p>
         * It must not be blank.
         *
         * @param authorizationHeader the header name
         * @return the builder
         */
        public Builder authorizationHeader(String authorizationHeader) {
            if (StringUtils.isBlank(authorizationHeader)) {
                throw new IllegalArgumentException("Authorization header is blank");
            }
            this.authorizationHeader = authorizationHeader;
            return this;
        }

        /**
         * Set the token type to the builder.
         * <p>
         * When provided as <code>null</code> or blank, then the token is just
         * added as is as value of the header
         *
         * @param tokenType the token type
         * @return the builder
         */
        public Builder tokenType(String tokenType) {
            if (StringUtils.isBlank(tokenType)) {
                tokenType = "";
            }
            this.tokenType = tokenType;
            return this;
        }

        public ServiceFactory build() {
            OkHttpClient.Builder clientBuilder = BASE_CLIENT.newBuilder();
            if (baseUrl == null) {
                throw new IllegalArgumentException("Base URL is required");
            }

            if (StringUtils.isNotBlank(accessToken)) {
                clientBuilder.addInterceptor(new AuthorizationHeaderInterceptor(accessToken, authorizationHeader, tokenType));
            }

            if (StringUtils.isNotBlank(apiKey)) {
                clientBuilder.addInterceptor(new ApiKeyParameterInterceptor(apiKey, keyParam));
            }

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .callFactory(clientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                    .addConverterFactory(JacksonConverterFactory.create(Json.mapper))
                    .build();
            return new ServiceFactory(retrofit);
        }
    }
}
