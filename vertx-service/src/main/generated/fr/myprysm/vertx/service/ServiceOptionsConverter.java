/*
 * Copyright (c) 2014 Red Hat, Inc. and others
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package fr.myprysm.vertx.service;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link fr.myprysm.vertx.service.ServiceOptions}.
 *
 * NOTE: This class has been automatically generated from the {@link fr.myprysm.vertx.service.ServiceOptions} original class using Vert.x codegen.
 */
public class ServiceOptionsConverter {

  public static void fromJson(JsonObject json, ServiceOptions obj) {
    if (json.getValue("address") instanceof String) {
      obj.setAddress((String)json.getValue("address"));
    }
    if (json.getValue("discovery") instanceof Boolean) {
      obj.setDiscovery((Boolean)json.getValue("discovery"));
    }
    if (json.getValue("facade") instanceof String) {
      obj.setFacade((String)json.getValue("facade"));
    }
    if (json.getValue("factoryMethod") instanceof String) {
      obj.setFactoryMethod((String)json.getValue("factoryMethod"));
    }
    if (json.getValue("healthCheck") instanceof Boolean) {
      obj.setHealthCheck((Boolean)json.getValue("healthCheck"));
    }
    if (json.getValue("healthCheckName") instanceof String) {
      obj.setHealthCheckName((String)json.getValue("healthCheckName"));
    }
    if (json.getValue("healthCheckTimeout") instanceof Number) {
      obj.setHealthCheckTimeout(((Number)json.getValue("healthCheckTimeout")).longValue());
    }
    if (json.getValue("implementation") instanceof String) {
      obj.setImplementation((String)json.getValue("implementation"));
    }
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
    if (json.getValue("register") instanceof Boolean) {
      obj.setRegister((Boolean)json.getValue("register"));
    }
  }

  public static void toJson(ServiceOptions obj, JsonObject json) {
    if (obj.getAddress() != null) {
      json.put("address", obj.getAddress());
    }
    if (obj.getDiscovery() != null) {
      json.put("discovery", obj.getDiscovery());
    }
    if (obj.getFacade() != null) {
      json.put("facade", obj.getFacade());
    }
    if (obj.getFactoryMethod() != null) {
      json.put("factoryMethod", obj.getFactoryMethod());
    }
    if (obj.getHealthCheck() != null) {
      json.put("healthCheck", obj.getHealthCheck());
    }
    if (obj.getHealthCheckName() != null) {
      json.put("healthCheckName", obj.getHealthCheckName());
    }
    if (obj.getHealthCheckTimeout() != null) {
      json.put("healthCheckTimeout", obj.getHealthCheckTimeout());
    }
    if (obj.getImplementation() != null) {
      json.put("implementation", obj.getImplementation());
    }
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
    if (obj.getRegister() != null) {
      json.put("register", obj.getRegister());
    }
  }
}