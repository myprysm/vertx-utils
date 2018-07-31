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

import fr.myprysm.vertx.elasticsearch.converter.CommonConverters;
import io.vertx.core.json.JsonObject;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;

/**
 * ResponseListener that forwards a Json response to an {@link ActionListener}.
 */
public class JsonResponseListener implements ResponseListener {

    /**
     * The action listener.
     */
    private final ActionListener<JsonObject> listener;

    /**
     * Build a response listener from the action listener.
     *
     * @param listener the action listener
     */
    public JsonResponseListener(ActionListener<JsonObject> listener) {
        this.listener = listener;
    }

    @Override
    public void onSuccess(Response response) {
        try {
            JsonObject jsonResponse = CommonConverters.responseAsJson(response);
            listener.onResponse(jsonResponse);
        } catch (Exception exc) {
            listener.onFailure(exc);
        }
    }

    @Override
    public void onFailure(Exception exception) {
        listener.onFailure(exception);
    }
}
