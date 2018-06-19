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

package fr.myprysm.vertx.core.config;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.serviceproxy.ServiceExceptionMessageCodec;

public class ConfigServiceExceptionMessageCodec implements MessageCodec<ConfigServiceException, ConfigServiceException> {
    private ServiceExceptionMessageCodec delegate = new ServiceExceptionMessageCodec();

    @Override
    public void encodeToWire(Buffer buffer, ConfigServiceException e) {
        delegate.encodeToWire(buffer, e);
    }

    @Override
    public ConfigServiceException decodeFromWire(int pos, Buffer buffer) {
        return new ConfigServiceException(delegate.decodeFromWire(pos, buffer));
    }

    @Override
    public ConfigServiceException transform(ConfigServiceException exception) {
        return exception;
    }

    @Override
    public String name() {
        return "ConfigServiceException";
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
