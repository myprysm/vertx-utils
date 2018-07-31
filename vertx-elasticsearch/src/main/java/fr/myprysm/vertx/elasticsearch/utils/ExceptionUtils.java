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

package fr.myprysm.vertx.elasticsearch.utils;

import io.reactivex.functions.Action;

/**
 * Utilities for exceptions.
 */
public final class ExceptionUtils {

    /**
     * Service class.
     */
    private ExceptionUtils() {
        //
    }

    /**
     * Swallow a potential exception thrown by the action.
     *
     * @param action the action
     */
    public static void swallowException(Action action) {
        try {
            action.run();
        } catch (Exception e) {
            //exception does nothing...
        }
    }
}
