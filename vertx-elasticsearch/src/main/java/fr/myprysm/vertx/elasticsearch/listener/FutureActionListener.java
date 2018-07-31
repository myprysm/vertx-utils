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

package fr.myprysm.vertx.elasticsearch.listener;


import fr.myprysm.vertx.elasticsearch.converter.Converter;
import fr.myprysm.vertx.elasticsearch.metrics.RequestMetrics;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import org.elasticsearch.action.ActionListener;

/**
 * Elasticsearch {@link Future} {@link ActionListener}.
 * <p>
 * Maps the response to the expected data type.
 * Any error fails the async result.
 *
 * @param <ResponseType> The ElasticSearch response type
 * @param <DataType>     The DataObject type
 */
public class FutureActionListener<ResponseType, DataType> implements ActionListener<ResponseType> {

    private final Converter<ResponseType, DataType> converter;
    private final Handler<AsyncResult<DataType>> handler;
    private final RequestMetrics metrics;

    public FutureActionListener(RequestMetrics metrics, Converter<ResponseType, DataType> converter, Handler<AsyncResult<DataType>> handler) {
        this.metrics = metrics;
        this.converter = converter;
        this.handler = handler;
    }

    @Override
    public void onResponse(ResponseType response) {
        metrics.endRequest();

        metrics.startConvertResponseToDataObject();
        try {
            DataType data = converter.convert(response);
            handler.handle(Future.succeededFuture(data));
        } catch (Exception exc) {
            metrics.errorConvertResponseToDataObject();
            handler.handle(Future.failedFuture(exc));
        }
        metrics.endConvertResponseToDataObject();
    }

    @Override
    public void onFailure(Exception exc) {
        handler.handle(Future.failedFuture(exc));
        metrics.errorRequest();
        metrics.endRequest();
    }

}
