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

package fr.myprysm.vertx.core.utils;

import fr.myprysm.vertx.validation.JsonValidation;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
public class ClassUtils {

    private static ScanResult scan;

    private ClassUtils() {

    }

    /**
     * Get the scan result of all the classes contained in the application.
     *
     * @return the scan result
     */
    public synchronized static ScanResult getScan() {
        if (scan == null) {
            log.info("Scanning classpath...");
            scan = new FastClasspathScanner().scan();
            log.info("Classpath scanned.");
        }

        return scan;
    }

    /**
     * Validates that the provided class has a static method identified with "name"
     * and with provided param classes
     *
     * @param clazz  the class to check
     * @param name   the name of the static method
     * @param params the params class
     * @return the validation
     */
    public static JsonValidation hasStaticMethod(Class<?> clazz, String name, Class<?>... params) {
        return JsonValidation.holds(obj -> Arrays.stream(clazz.getMethods())
                        .filter(method -> name.equals(method.getName()))
                        .filter(method -> {
                            Class<?>[] types = method.getParameterTypes();
                            if (types.length == params.length) {
                                return IntStream.range(0, params.length)
                                        .allMatch(i -> params[i].isAssignableFrom(types[i]));

                            }
                            return false;
                        })
                        .anyMatch(method -> Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers())),
                "Class " + clazz.getName() + " has no static method " + name);

    }

    /**
     * Get a class from its name.
     * <p>
     * Returns null when the class does not exist
     *
     * @param clazz the class to get
     * @return the class
     */
    public static Class<?> getClass(String clazz) {
        return getClass(clazz, true);
    }

    /**
     * Get a class from its name.
     * <p>
     * Either throw or return null when the class does not exist, depending on the ignoreExceptions flag
     *
     * @param clazz            the class to get
     * @param ignoreExceptions set to false to throw an {@link IllegalArgumentException} when the class cannot be located
     * @return the class
     */
    public static Class<?> getClass(String clazz, boolean ignoreExceptions) {
        return getScan().classNameToClassRef(clazz, ignoreExceptions);
    }
}
