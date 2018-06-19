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

import fr.myprysm.vertx.core.utils.ClassUtils;
import io.reactivex.plugins.RxJavaPlugins;
import io.vertx.core.Vertx;
import io.vertx.reactivex.RxHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Launcher extends io.vertx.core.Launcher {

    /**
     * Main entry point.
     *
     * @param args the user command line arguments.
     */
    public static void main(String[] args) {
        new Launcher().dispatch(args);
    }

    @Override
    public void afterStartingVertx(Vertx vertx) {
        RxJavaPlugins.setComputationSchedulerHandler(s -> RxHelper.scheduler(vertx));
        RxJavaPlugins.setIoSchedulerHandler(s -> RxHelper.blockingScheduler(vertx));
        RxJavaPlugins.setNewThreadSchedulerHandler(s -> RxHelper.scheduler(vertx));
        log.info("Bound RxJava2 schedulers to vertx");
        // Load classes while not yet on a vertx thread so that further operations
        // on the scan will be fast for each component
        ClassUtils.getScan();
    }
}
