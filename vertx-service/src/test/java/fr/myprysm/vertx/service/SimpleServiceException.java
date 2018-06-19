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

import io.vertx.serviceproxy.ServiceException;

public class SimpleServiceException extends ServiceException {
    public static final int CONFIG_NOT_FOUND = 1;
    public static final int CONFIG_TYPE_MISMATCH = 2;
    public static final int UNKNOWN_ERROR = 3;
    public static final int NUMBER_TOO_LARGE = 4;

    public SimpleServiceException(int failureCode, String message) {
        super(failureCode, message);
    }

    public SimpleServiceException(ServiceException exc) {
        this(exc.failureCode(), exc.getMessage());
    }

//    public SimpleServiceException(int failureCode, String message, JsonObject debugInfo) {
//        super(failureCode, message, debugInfo);
//    }
}
