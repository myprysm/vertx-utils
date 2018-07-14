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

/** @module vertx-utils-elasticsearch-js/indices_client */
var utils = require('vertx-js/util/utils');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JIndicesClient = Java.type('fr.myprysm.vertx.elasticsearch.IndicesClient');
var DeleteIndexResponse = Java.type('fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexResponse');
var GetIndexRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest');
var DeleteIndexRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest');

/**
 Vertx Elasticsearch indices client.
 <p>
 See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html">Indices API on elastic.co</a>

 @class
*/
var IndicesClient = function(j_val) {

  var j_indicesClient = j_val;
  var that = this;

  /**
   Asynchronously deletes an index using the Delete Index API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-delete-index.html">
   Delete Index API on elastic.co</a>

   @public
   @param request {Object} the request 
   @param handler {function} the handler 
   */
  this.delete = function(request, handler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_indicesClient["delete(fr.myprysm.vertx.elasticsearch.action.admin.indices.delete.DeleteIndexRequest,io.vertx.core.Handler)"](request != null ? new DeleteIndexRequest(new JsonObject(Java.asJSONCompatible(request))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously checks if one or more aliases exist using the Aliases Exist API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices-aliases.html">
   Indices Aliases API on elastic.co</a>

   @public
   @param request {Object} the request 
   @param handler {function} the handler 
   */
  this.exists = function(request, handler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_indicesClient["exists(fr.myprysm.vertx.elasticsearch.action.admin.indices.get.GetIndexRequest,io.vertx.core.Handler)"](request != null ? new GetIndexRequest(new JsonObject(Java.asJSONCompatible(request))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_indicesClient;
};

IndicesClient._jclass = utils.getJavaClass("fr.myprysm.vertx.elasticsearch.IndicesClient");
IndicesClient._jtype = {
  accept: function(obj) {
    return IndicesClient._jclass.isInstance(obj._jdel);
  },
  wrap: function(jdel) {
    var obj = Object.create(IndicesClient.prototype, {});
    IndicesClient.apply(obj, arguments);
    return obj;
  },
  unwrap: function(obj) {
    return obj._jdel;
  }
};
IndicesClient._create = function(jdel) {
  var obj = Object.create(IndicesClient.prototype, {});
  IndicesClient.apply(obj, arguments);
  return obj;
}
module.exports = IndicesClient;