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

package fr.myprysm.vertx.core.scala

import fr.myprysm.vertx.core.{VerticleOptions => JVerticleOptions}
import io.vertx.core.json.JsonObject

class VerticleOptions(private val _asJava: JVerticleOptions) {

  def setOptions(value: DeploymentOptions) = {
    asJava.setOptions()
    this
  }

  def getOptions: DeploymentOptions = {
    DeploymentOptions(asJava.getOptions())
  }

  def setVerticle(value: String) = {
    asJava.setVerticle(value)
    this
  }

  def asJava = _asJava

  def getVerticle: String = {
    asJava.getVerticle().asInstanceOf[String]
  }
}

object VerticleOptions {

  def apply() = {
    new VerticleOptions(new JVerticleOptions(emptyObj()))
  }

  def apply(t: JVerticleOptions) = {
    if (t != null) {
      new VerticleOptions(t)
    } else {
      new VerticleOptions(new JVerticleOptions(emptyObj()))
    }
  }

  def fromJson(json: JsonObject): VerticleOptions = {
    if (json != null) {
      new VerticleOptions(new JVerticleOptions(json))
    } else {
      new VerticleOptions(new JVerticleOptions(emptyObj()))
    }
  }
}
