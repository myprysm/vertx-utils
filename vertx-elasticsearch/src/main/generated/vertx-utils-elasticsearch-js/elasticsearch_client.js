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

/** @module vertx-utils-elasticsearch-js/elasticsearch_client */
var utils = require('vertx-js/util/utils');
var IndicesClient = require('vertx-utils-elasticsearch-js/indices_client');
var Vertx = require('vertx-js/vertx');
var ClusterClient = require('vertx-utils-elasticsearch-js/cluster_client');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JElasticsearchClient = Java.type('fr.myprysm.vertx.elasticsearch.ElasticsearchClient');
var IndexRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.index.IndexRequest');
var MultiGetResponse = Java.type('fr.myprysm.vertx.elasticsearch.action.get.MultiGetResponse');
var BulkResponse = Java.type('fr.myprysm.vertx.elasticsearch.action.bulk.BulkResponse');
var GetRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.get.GetRequest');
var DeleteResponse = Java.type('fr.myprysm.vertx.elasticsearch.action.delete.DeleteResponse');
var UpdateResponse = Java.type('fr.myprysm.vertx.elasticsearch.action.update.UpdateResponse');
var UpdateRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest');
var BulkRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest');
var GetResponse = Java.type('fr.myprysm.vertx.elasticsearch.action.get.GetResponse');
var MultiGetRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest');
var DeleteRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest');
var MainResponse = Java.type('fr.myprysm.vertx.elasticsearch.action.main.MainResponse');
var BaseRequest = Java.type('fr.myprysm.vertx.elasticsearch.action.BaseRequest');
var IndexResponse = Java.type('fr.myprysm.vertx.elasticsearch.action.index.IndexResponse');

/**
 Vertx Elasticsearch client.
 <p>
 See Elasticsearch documentation for precise usage (either
 java client
 or
 <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/">rest client</a>
 ).

 @class
*/
var ElasticsearchClient = function(j_val) {

  var j_elasticsearchClient = j_val;
  var that = this;

  /**
   Close the client.

   @public

   */
  this.close = function() {
    var __args = arguments;
    if (__args.length === 0) {
      j_elasticsearchClient["close()"]();
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Provides an {@link IndicesClient} which can be used to access the Indices API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/indices.html">Indices API on elastic.co</a>

   @public

   @return {IndicesClient} the indices client
   */
  this.indices = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return utils.convReturnVertxGen(IndicesClient, j_elasticsearchClient["indices()"]());
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Provides a {@link ClusterClient} which can be used to access the Cluster API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html">Cluster API on elastic.co</a>

   @public

   @return {ClusterClient} the cluster client
   */
  this.cluster = function() {
    var __args = arguments;
    if (__args.length === 0) {
      return utils.convReturnVertxGen(ClusterClient, j_elasticsearchClient["cluster()"]());
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously pings the remote Elasticsearch cluster and returns true if the ping succeeded, false otherwise.

   @public
   @param request {Object} the optional request 
   @param handler {function} the handler 
   */
  this.ping = function() {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_elasticsearchClient["ping(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        __args[0](ar.result(), null);
      } else {
        __args[0](null, ar.cause());
      }
    });
    }  else if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_elasticsearchClient["ping(fr.myprysm.vertx.elasticsearch.action.BaseRequest,io.vertx.core.Handler)"](__args[0] != null ? new BaseRequest(new JsonObject(Java.asJSONCompatible(__args[0]))) : null, function(ar) {
      if (ar.succeeded()) {
        __args[1](ar.result(), null);
      } else {
        __args[1](null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously get the cluster info otherwise provided when sending an HTTP request to port 9200.

   @public
   @param request {Object} the optional request 
   @param handler {function} the handler 
   */
  this.info = function() {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_elasticsearchClient["info(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        __args[0](utils.convReturnDataObject(ar.result()), null);
      } else {
        __args[0](null, ar.cause());
      }
    });
    }  else if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_elasticsearchClient["info(fr.myprysm.vertx.elasticsearch.action.BaseRequest,io.vertx.core.Handler)"](__args[0] != null ? new BaseRequest(new JsonObject(Java.asJSONCompatible(__args[0]))) : null, function(ar) {
      if (ar.succeeded()) {
        __args[1](utils.convReturnDataObject(ar.result()), null);
      } else {
        __args[1](null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously retrieves a document by id using the Get API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>

   @public
   @param request {Object} the request 
   @param handler {function} the handler 
   */
  this.get = function(request, handler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_elasticsearchClient["get(fr.myprysm.vertx.elasticsearch.action.get.GetRequest,io.vertx.core.Handler)"](request != null ? new GetRequest(new JsonObject(Java.asJSONCompatible(request))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously checks for the existence of a document. Returns true if it exists, false otherwise.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html">Get API on elastic.co</a>

   @public
   @param request {Object} the request 
   @param handler {function} the handler 
   */
  this.exists = function(request, handler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_elasticsearchClient["exists(fr.myprysm.vertx.elasticsearch.action.get.GetRequest,io.vertx.core.Handler)"](request != null ? new GetRequest(new JsonObject(Java.asJSONCompatible(request))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously retrieves multiple documents by id using the Multi Get API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-multi-get.html">Multi Get API on elastic.co</a>

   @public
   @param request {Object} the request 
   @param handler {function} the handler 
   */
  this.multiGet = function(request, handler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_elasticsearchClient["multiGet(fr.myprysm.vertx.elasticsearch.action.get.MultiGetRequest,io.vertx.core.Handler)"](request != null ? new MultiGetRequest(new JsonObject(Java.asJSONCompatible(request))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously index a document using the Index API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-index_.html">Index API on elastic.co</a>

   @public
   @param request {Object} the request 
   @param handler {function} the handler 
   */
  this.index = function(request, handler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_elasticsearchClient["index(fr.myprysm.vertx.elasticsearch.action.index.IndexRequest,io.vertx.core.Handler)"](request != null ? new IndexRequest(new JsonObject(Java.asJSONCompatible(request))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously updates a document using the Update API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-update.html">Update API on elastic.co</a>

   @public
   @param request {Object} the request 
   @param handler {function} the handler 
   */
  this.update = function(request, handler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_elasticsearchClient["update(fr.myprysm.vertx.elasticsearch.action.update.UpdateRequest,io.vertx.core.Handler)"](request != null ? new UpdateRequest(new JsonObject(Java.asJSONCompatible(request))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously deletes a document by id using the Delete API.
   <p>
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-delete.html">Delete API on elastic.co</a>

   @public
   @param request {Object} the request 
   @param handler {function} the handler 
   */
  this.delete = function(request, handler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_elasticsearchClient["delete(fr.myprysm.vertx.elasticsearch.action.delete.DeleteRequest,io.vertx.core.Handler)"](request != null ? new DeleteRequest(new JsonObject(Java.asJSONCompatible(request))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Asynchronously executes a bulk request using the Bulk API
  
   See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-bulk.html">Bulk API on elastic.co</a>

   @public
   @param request {Object} 
   @param handler {function} 
   */
  this.bulk = function(request, handler) {
    var __args = arguments;
    if (__args.length === 2 && (typeof __args[0] === 'object' && __args[0] != null) && typeof __args[1] === 'function') {
      j_elasticsearchClient["bulk(fr.myprysm.vertx.elasticsearch.action.bulk.BulkRequest,io.vertx.core.Handler)"](request != null ? new BulkRequest(new JsonObject(Java.asJSONCompatible(request))) : null, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_elasticsearchClient;
};

ElasticsearchClient._jclass = utils.getJavaClass("fr.myprysm.vertx.elasticsearch.ElasticsearchClient");
ElasticsearchClient._jtype = {
  accept: function(obj) {
    return ElasticsearchClient._jclass.isInstance(obj._jdel);
  },
  wrap: function(jdel) {
    var obj = Object.create(ElasticsearchClient.prototype, {});
    ElasticsearchClient.apply(obj, arguments);
    return obj;
  },
  unwrap: function(obj) {
    return obj._jdel;
  }
};
ElasticsearchClient._create = function(jdel) {
  var obj = Object.create(ElasticsearchClient.prototype, {});
  ElasticsearchClient.apply(obj, arguments);
  return obj;
}
/**
 Create an ElasticSearch client which maintains its own data source.

 @memberof module:vertx-utils-elasticsearch-js/elasticsearch_client
 @param vertx {Vertx} the Vert.x instance 
 @param config {Object} the configuration 
 @return {ElasticsearchClient} the client
 */
ElasticsearchClient.createNonShared = function(vertx, config) {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && (typeof __args[1] === 'object' && __args[1] != null)) {
    return utils.convReturnVertxGen(ElasticsearchClient, JElasticsearchClient["createNonShared(io.vertx.core.Vertx,io.vertx.core.json.JsonObject)"](vertx._jdel, utils.convParamJsonObject(config)));
  } else throw new TypeError('function invoked with invalid arguments');
};

/**
 Create an ElasticSearch client which shares its data source with any other Mongo clients created with the same
 client name.

 @memberof module:vertx-utils-elasticsearch-js/elasticsearch_client
 @param vertx {Vertx} the Vert.x instance 
 @param config {Object} the configuration 
 @param clientName {string} the client name 
 @return {ElasticsearchClient} the client
 */
ElasticsearchClient.createShared = function() {
  var __args = arguments;
  if (__args.length === 2 && typeof __args[0] === 'object' && __args[0]._jdel && (typeof __args[1] === 'object' && __args[1] != null)) {
    return utils.convReturnVertxGen(ElasticsearchClient, JElasticsearchClient["createShared(io.vertx.core.Vertx,io.vertx.core.json.JsonObject)"](__args[0]._jdel, utils.convParamJsonObject(__args[1])));
  }else if (__args.length === 3 && typeof __args[0] === 'object' && __args[0]._jdel && (typeof __args[1] === 'object' && __args[1] != null) && typeof __args[2] === 'string') {
    return utils.convReturnVertxGen(ElasticsearchClient, JElasticsearchClient["createShared(io.vertx.core.Vertx,io.vertx.core.json.JsonObject,java.lang.String)"](__args[0]._jdel, utils.convParamJsonObject(__args[1]), __args[2]));
  } else throw new TypeError('function invoked with invalid arguments');
};

module.exports = ElasticsearchClient;