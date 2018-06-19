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

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Appends a bearer as headerName header on every request originated by the client
 */
public class AuthorizationHeaderInterceptor implements Interceptor {
    private final String bearer;
    private final String headerName;

    public AuthorizationHeaderInterceptor(String accessToken, String authorizationHeader, String tokenType) {
        headerName = authorizationHeader;
        bearer = "".equals(tokenType) ? accessToken : tokenType + ": " + accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder().addHeader(headerName, bearer).build();

        Response response = chain.proceed(request);
        return response;
    }
}
