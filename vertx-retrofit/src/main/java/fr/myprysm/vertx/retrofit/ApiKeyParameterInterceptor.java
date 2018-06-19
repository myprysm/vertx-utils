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

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Appends an API Key as "key" param on every request originated by the client
 */
public class ApiKeyParameterInterceptor implements Interceptor {
    private final String apiKey;
    private final String paramName;

    public ApiKeyParameterInterceptor(String apiKey, String paramName) {
        this.apiKey = apiKey;
        this.paramName = paramName;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl newUrl = request.url().newBuilder().setQueryParameter(paramName, apiKey).build();
        request = request.newBuilder().url(newUrl).build();

        return chain.proceed(request);
    }
}
