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

package fr.myprysm.vertx.core.scala.config

import fr.myprysm.vertx.core.config.{ConfigService => JConfigService}
import fr.myprysm.vertx.core.{VerticleOptions => JVerticleOptions}
import io.vertx.core.{AsyncResult, Handler}
import io.vertx.core.json.{JsonArray, JsonObject}

/**
  * Configuration Service that provides all the configuration items for the application
  */
class ConfigService(private val _asJava: Object) {

  /**
    * Get the configured verticles as a <code>JsonArray</code>.
    *
    * @param handler the result handler
    * @return this
    */
  def getVerticles(handler: Handler[AsyncResult[scala.collection.mutable.Buffer[VerticleOptions]]]): ConfigService = {
    asJava.asInstanceOf[JConfigService].getVerticles({ x: AsyncResult[java.util.List[JVerticleOptions]] => handler.handle(AsyncResultWrapper[java.util.List[JVerticleOptions], scala.collection.mutable.Buffer[VerticleOptions]](x, a => a.asScala.map(x => VerticleOptions(x)))) })
    this
  }

  /**
    * Get the whole configuration as a <code>JsonObject</code>.
    *
    * @param handler the result handler
    * @return this
    */
  def getConfig(handler: Handler[AsyncResult[io.vertx.core.json.JsonObject]]): ConfigService = {
    asJava.asInstanceOf[JConfigService].getConfig({ x: AsyncResult[JsonObject] => handler.handle(AsyncResultWrapper[JsonObject, io.vertx.core.json.JsonObject](x, a => a)) })
    this
  }

  /**
    * Get the configuration item identified by the <code>key</code> as a <code>String</code>.
    *
    * @param key     the key to search
    * @param handler the result handler
    * @return this
    */
  def getString(key: String, handler: Handler[AsyncResult[String]]): ConfigService = {
    asJava.asInstanceOf[JConfigService].getString(key.asInstanceOf[java.lang.String], { x: AsyncResult[java.lang.String] => handler.handle(AsyncResultWrapper[java.lang.String, String](x, a => a.asInstanceOf[String])) })
    this
  }

  /**
    * Get the configuration item identified by the <code>key</code> as a <code>Boolean</code>.
    *
    * @param key     the key to search
    * @param handler the result handler
    * @return this
    */
  def getBoolean(key: String, handler: Handler[AsyncResult[Boolean]]): ConfigService = {
    asJava.asInstanceOf[JConfigService].getBoolean(key.asInstanceOf[java.lang.String], { x: AsyncResult[java.lang.Boolean] => handler.handle(AsyncResultWrapper[java.lang.Boolean, Boolean](x, a => a.asInstanceOf[Boolean])) })
    this
  }

  /**
    * Get the configuration item identified by the <code>key</code> as an <code>Integer</code>.
    *
    * @param key     the key to search
    * @param handler the result handler
    * @return this
    */
  def getInteger(key: String, handler: Handler[AsyncResult[Int]]): ConfigService = {
    asJava.asInstanceOf[JConfigService].getInteger(key.asInstanceOf[java.lang.String], { x: AsyncResult[java.lang.Integer] => handler.handle(AsyncResultWrapper[java.lang.Integer, Int](x, a => a.asInstanceOf[Int])) })
    this
  }

  /**
    * Get the configuration item identified by the <code>key</code> as a <code>Long</code>.
    *
    * @param key     the key to search
    * @param handler the result handler
    * @return this
    */
  def getLong(key: String, handler: Handler[AsyncResult[Long]]): ConfigService = {
    asJava.asInstanceOf[JConfigService].getLong(key.asInstanceOf[java.lang.String], { x: AsyncResult[java.lang.Long] => handler.handle(AsyncResultWrapper[java.lang.Long, Long](x, a => a.asInstanceOf[Long])) })
    this
  }

  /**
    * Get the configuration item identified by the <code>key</code> as a <code>Float</code>.
    *
    * @param key     the key to search
    * @param handler the result handler
    * @return this
    */
  def getFloat(key: String, handler: Handler[AsyncResult[Float]]): ConfigService = {
    asJava.asInstanceOf[JConfigService].getFloat(key.asInstanceOf[java.lang.String], { x: AsyncResult[java.lang.Float] => handler.handle(AsyncResultWrapper[java.lang.Float, Float](x, a => a.asInstanceOf[Float])) })
    this
  }

  /**
    * Get the configuration item identified by the <code>key</code> as a <code>Double</code>.
    *
    * @param key     the key to search
    * @param handler the result handler
    * @return this
    */
  def getDouble(key: String, handler: Handler[AsyncResult[Double]]): ConfigService = {
    asJava.asInstanceOf[JConfigService].getDouble(key.asInstanceOf[java.lang.String], { x: AsyncResult[java.lang.Double] => handler.handle(AsyncResultWrapper[java.lang.Double, Double](x, a => a.asInstanceOf[Double])) })
    this
  }

  /**
    * Get the configuration item identified by the <code>key</code> as a <code>JsonArray</code>.
    *
    * @param key     the key to search
    * @param handler the result handler
    * @return this
    */
  def getArray(key: String, handler: Handler[AsyncResult[io.vertx.core.json.JsonArray]]): ConfigService = {
    asJava.asInstanceOf[JConfigService].getArray(key.asInstanceOf[java.lang.String], { x: AsyncResult[JsonArray] => handler.handle(AsyncResultWrapper[JsonArray, io.vertx.core.json.JsonArray](x, a => a)) })
    this
  }

  def asJava = _asJava

  /**
    * Get the configuration item identified by the <code>key</code> as a <code>JsonObject</code>.
    *
    * @param key     the key to search
    * @param handler the result handler
    * @return this
    */
  def getObject(key: String, handler: Handler[AsyncResult[io.vertx.core.json.JsonObject]]): ConfigService = {
    asJava.asInstanceOf[JConfigService].getObject(key.asInstanceOf[java.lang.String], { x: AsyncResult[JsonObject] => handler.handle(AsyncResultWrapper[JsonObject, io.vertx.core.json.JsonObject](x, a => a)) })
    this
  }

  /**
    * Like [[getVerticles]] but returns a [[scala.concurrent.Future]] instead of taking an AsyncResultHandler.
    */
  def getVerticlesFuture(): scala.concurrent.Future[scala.collection.mutable.Buffer[VerticleOptions]] = {
    val promiseAndHandler = handlerForAsyncResultWithConversion[java.util.List[JVerticleOptions], scala.collection.mutable.Buffer[VerticleOptions]](x => x.asScala.map(x => VerticleOptions(x)))
    asJava.asInstanceOf[JConfigService].getVerticles(promiseAndHandler._1)
    promiseAndHandler._2.future
  }

  /**
    * Like [[getConfig]] but returns a [[scala.concurrent.Future]] instead of taking an AsyncResultHandler.
    */
  def getConfigFuture(): scala.concurrent.Future[io.vertx.core.json.JsonObject] = {
    val promiseAndHandler = handlerForAsyncResultWithConversion[JsonObject, io.vertx.core.json.JsonObject](x => x)
    asJava.asInstanceOf[JConfigService].getConfig(promiseAndHandler._1)
    promiseAndHandler._2.future
  }

  /**
    * Like [[getString]] but returns a [[scala.concurrent.Future]] instead of taking an AsyncResultHandler.
    */
  def getStringFuture(key: String): scala.concurrent.Future[String] = {
    val promiseAndHandler = handlerForAsyncResultWithConversion[java.lang.String, String](x => x.asInstanceOf[String])
    asJava.asInstanceOf[JConfigService].getString(key.asInstanceOf[java.lang.String], promiseAndHandler._1)
    promiseAndHandler._2.future
  }

  /**
    * Like [[getBoolean]] but returns a [[scala.concurrent.Future]] instead of taking an AsyncResultHandler.
    */
  def getBooleanFuture(key: String): scala.concurrent.Future[Boolean] = {
    val promiseAndHandler = handlerForAsyncResultWithConversion[java.lang.Boolean, Boolean](x => x.asInstanceOf[Boolean])
    asJava.asInstanceOf[JConfigService].getBoolean(key.asInstanceOf[java.lang.String], promiseAndHandler._1)
    promiseAndHandler._2.future
  }

  /**
    * Like [[getInteger]] but returns a [[scala.concurrent.Future]] instead of taking an AsyncResultHandler.
    */
  def getIntegerFuture(key: String): scala.concurrent.Future[Int] = {
    val promiseAndHandler = handlerForAsyncResultWithConversion[java.lang.Integer, Int](x => x.asInstanceOf[Int])
    asJava.asInstanceOf[JConfigService].getInteger(key.asInstanceOf[java.lang.String], promiseAndHandler._1)
    promiseAndHandler._2.future
  }

  /**
    * Like [[getLong]] but returns a [[scala.concurrent.Future]] instead of taking an AsyncResultHandler.
    */
  def getLongFuture(key: String): scala.concurrent.Future[Long] = {
    val promiseAndHandler = handlerForAsyncResultWithConversion[java.lang.Long, Long](x => x.asInstanceOf[Long])
    asJava.asInstanceOf[JConfigService].getLong(key.asInstanceOf[java.lang.String], promiseAndHandler._1)
    promiseAndHandler._2.future
  }

  /**
    * Like [[getFloat]] but returns a [[scala.concurrent.Future]] instead of taking an AsyncResultHandler.
    */
  def getFloatFuture(key: String): scala.concurrent.Future[Float] = {
    val promiseAndHandler = handlerForAsyncResultWithConversion[java.lang.Float, Float](x => x.asInstanceOf[Float])
    asJava.asInstanceOf[JConfigService].getFloat(key.asInstanceOf[java.lang.String], promiseAndHandler._1)
    promiseAndHandler._2.future
  }

  /**
    * Like [[getDouble]] but returns a [[scala.concurrent.Future]] instead of taking an AsyncResultHandler.
    */
  def getDoubleFuture(key: String): scala.concurrent.Future[Double] = {
    val promiseAndHandler = handlerForAsyncResultWithConversion[java.lang.Double, Double](x => x.asInstanceOf[Double])
    asJava.asInstanceOf[JConfigService].getDouble(key.asInstanceOf[java.lang.String], promiseAndHandler._1)
    promiseAndHandler._2.future
  }

  /**
    * Like [[getArray]] but returns a [[scala.concurrent.Future]] instead of taking an AsyncResultHandler.
    */
  def getArrayFuture(key: String): scala.concurrent.Future[io.vertx.core.json.JsonArray] = {
    val promiseAndHandler = handlerForAsyncResultWithConversion[JsonArray, io.vertx.core.json.JsonArray](x => x)
    asJava.asInstanceOf[JConfigService].getArray(key.asInstanceOf[java.lang.String], promiseAndHandler._1)
    promiseAndHandler._2.future
  }

  /**
    * Like [[getObject]] but returns a [[scala.concurrent.Future]] instead of taking an AsyncResultHandler.
    */
  def getObjectFuture(key: String): scala.concurrent.Future[io.vertx.core.json.JsonObject] = {
    val promiseAndHandler = handlerForAsyncResultWithConversion[JsonObject, io.vertx.core.json.JsonObject](x => x)
    asJava.asInstanceOf[JConfigService].getObject(key.asInstanceOf[java.lang.String], promiseAndHandler._1)
    promiseAndHandler._2.future
  }

}

object ConfigService {
  def apply(asJava: JConfigService) = new ConfigService(asJava)
}
