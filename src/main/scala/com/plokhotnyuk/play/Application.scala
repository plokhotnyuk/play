package com.plokhotnyuk.play

import javax.inject.Singleton
import akka.stream.scaladsl.Source
import play.api.mvc._
import play.api.libs.json.Json

@Singleton
class Application extends Controller {
  val json = Action {
    Ok(Json.toJson(HelloWorld("Hello, World!"))(Json.writes[HelloWorld]))
  }

  val plaintext = Action {
    Ok("Hello, World!")
  }

  val chunked = Action {
    Ok.chunked(Source.single("Hello, World!"))
  }
}

case class HelloWorld(message: String)
