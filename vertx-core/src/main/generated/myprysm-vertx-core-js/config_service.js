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

/** @module myprysm-vertx-core-js/config_service */
var utils = require('vertx-js/util/utils');

var io = Packages.io;
var JsonObject = io.vertx.core.json.JsonObject;
var JConfigService = Java.type('fr.myprysm.vertx.core.config.ConfigService');
var VerticleOptions = Java.type('fr.myprysm.vertx.core.VerticleOptions');

/**
 Configuration Service that provides all the configuration items for the application

 @class
*/
var ConfigService = function(j_val) {

  var j_configService = j_val;
  var that = this;

  /**
   Get the configured verticles as a <code>JsonArray</code>.

   @public
   @param handler {function} the result handler 
   @return {ConfigService} this
   */
  this.getVerticles = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_configService["getVerticles(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnListSetDataObject(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Get the whole configuration as a <code>JsonObject</code>.

   @public
   @param handler {function} the result handler 
   @return {ConfigService} this
   */
  this.getConfig = function(handler) {
    var __args = arguments;
    if (__args.length === 1 && typeof __args[0] === 'function') {
      j_configService["getConfig(io.vertx.core.Handler)"](function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnJson(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Get the configuration item identified by the <code>key</code> as a <code>String</code>.

   @public
   @param key {string} the key to search 
   @param handler {function} the result handler 
   @return {ConfigService} this
   */
  this.getString = function(key, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_configService["getString(java.lang.String,io.vertx.core.Handler)"](key, function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Get the configuration item identified by the <code>key</code> as a <code>Boolean</code>.

   @public
   @param key {string} the key to search 
   @param handler {function} the result handler 
   @return {ConfigService} this
   */
  this.getBoolean = function(key, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_configService["getBoolean(java.lang.String,io.vertx.core.Handler)"](key, function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Get the configuration item identified by the <code>key</code> as an <code>Integer</code>.

   @public
   @param key {string} the key to search 
   @param handler {function} the result handler 
   @return {ConfigService} this
   */
  this.getInteger = function(key, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_configService["getInteger(java.lang.String,io.vertx.core.Handler)"](key, function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Get the configuration item identified by the <code>key</code> as a <code>Long</code>.

   @public
   @param key {string} the key to search 
   @param handler {function} the result handler 
   @return {ConfigService} this
   */
  this.getLong = function(key, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_configService["getLong(java.lang.String,io.vertx.core.Handler)"](key, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnLong(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Get the configuration item identified by the <code>key</code> as a <code>Float</code>.

   @public
   @param key {string} the key to search 
   @param handler {function} the result handler 
   @return {ConfigService} this
   */
  this.getFloat = function(key, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_configService["getFloat(java.lang.String,io.vertx.core.Handler)"](key, function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Get the configuration item identified by the <code>key</code> as a <code>Double</code>.

   @public
   @param key {string} the key to search 
   @param handler {function} the result handler 
   @return {ConfigService} this
   */
  this.getDouble = function(key, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_configService["getDouble(java.lang.String,io.vertx.core.Handler)"](key, function(ar) {
      if (ar.succeeded()) {
        handler(ar.result(), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Get the configuration item identified by the <code>key</code> as a <code>JsonArray</code>.

   @public
   @param key {string} the key to search 
   @param handler {function} the result handler 
   @return {ConfigService} this
   */
  this.getArray = function(key, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_configService["getArray(java.lang.String,io.vertx.core.Handler)"](key, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnJson(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  /**
   Get the configuration item identified by the <code>key</code> as a <code>JsonObject</code>.

   @public
   @param key {string} the key to search 
   @param handler {function} the result handler 
   @return {ConfigService} this
   */
  this.getObject = function(key, handler) {
    var __args = arguments;
    if (__args.length === 2 && typeof __args[0] === 'string' && typeof __args[1] === 'function') {
      j_configService["getObject(java.lang.String,io.vertx.core.Handler)"](key, function(ar) {
      if (ar.succeeded()) {
        handler(utils.convReturnJson(ar.result()), null);
      } else {
        handler(null, ar.cause());
      }
    });
      return that;
    } else throw new TypeError('function invoked with invalid arguments');
  };

  // A reference to the underlying Java delegate
  // NOTE! This is an internal API and must not be used in user code.
  // If you rely on this property your code is likely to break if we change it / remove it without warning.
  this._jdel = j_configService;
};

ConfigService._jclass = utils.getJavaClass("fr.myprysm.vertx.core.config.ConfigService");
ConfigService._jtype = {
  accept: function(obj) {
    return ConfigService._jclass.isInstance(obj._jdel);
  },
  wrap: function(jdel) {
    var obj = Object.create(ConfigService.prototype, {});
    ConfigService.apply(obj, arguments);
    return obj;
  },
  unwrap: function(obj) {
    return obj._jdel;
  }
};
ConfigService._create = function(jdel) {
  var obj = Object.create(ConfigService.prototype, {});
  ConfigService.apply(obj, arguments);
  return obj;
}
module.exports = ConfigService;