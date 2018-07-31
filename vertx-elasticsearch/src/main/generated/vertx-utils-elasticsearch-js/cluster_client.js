/*
 * Copyright 2014 Red Hat, Inc.
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

/** @module vertx-utils-elasticsearch-js/cluster_client */
var utils = require('vertx-js/util/utils');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JClusterClient = Java.type('fr.myprysm.vertx.elasticsearch.ClusterClient');
var ClusterUpdateSettingsRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest');
var BaseRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.BaseRequest');
var ClusterUpdateSettingsResponse = Java.type('fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsResponse');

/**
 Vertx Elasticsearch cluster client.
 <p>
 See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html">Cluster API on elastic.co</a>

 @class
*/
var ClusterClient = function(j_val) {

  var j_clusterClient = j_val;
  var that = this;

  /**
   Asynchronously updates cluster wide specific settings using the Cluster Update Settings API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
   API on elastic.co</a>

   @public
   @param request {Object} the request 
   @param handler {function} the handler 
   */
  this.putSettings = function(request, handler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_clusterClient["putSettings(fr.myprysm.vertx.elasticsearch.action.admin.cluster.ClusterUpdateSettingsRequest,io.vertx.core.Handler)"](request != null ? new ClusterUpdateSettingsRequest(new JsonObject(Java.asJSONCompatible(request))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously get cluster wide specific settings using the Cluster Update Settings API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
   API on elastic.co</a>

   @public
   @param request {Object} the request 
   @param handler {function} the handler 
   */
  this.getSettings = function() {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_clusterClient["getSettings(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        __args[0](utils.convReturnDataObject(ar.result()), null);
      } else {
        __args[0](null, ar.cause());
      }
    });
    }  else if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_clusterClient["getSettings(fr.myprysm.vertx.elasticsearch.action.BaseRequest,io.vertx.core.Handler)"](__args[0] != null ? new BaseRequest(new JsonObject(Java.asJSONCompatible(__args[0]))) : null, function(ar) {
      if (ar.succeeded()) {
        __args[1](utils.convReturnDataObject(ar.result()), null);
      } else {
        __args[1](null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_clusterClient;
};

ClusterClient._jclass = utils.getJavaClass("fr.myprysm.vertx.elasticsearch.ClusterClient");
ClusterClient._jtype = {
  accept: function(obj) {
    return ClusterClient._jclass.isInstance(obj._jdel);
  },
  wrap: function(jdel) {
    var obj = Object.create(ClusterClient.prototype, {});
    ClusterClient.apply(obj, arguments);
    return obj;
  },
  unwrap: function(obj) {
    return obj._jdel;
  }
};
ClusterClient._create = function(jdel) {
  var obj = Object.create(ClusterClient.prototype, {});
  ClusterClient.apply(obj, arguments);
  return obj;
}
module.exports = ClusterClient;