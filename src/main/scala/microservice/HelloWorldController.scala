package microservice

import javax.inject.Singleton
import play.api.libs.json.Json._
import play.api.mvc._

@Singleton
class HelloWorldController extends Controller {
  implicit val helloWorldFormat = format[HelloWorld]

  val jsonGet = Action {
    Ok(toJson(HelloWorld("Hello, World!")))
  }

  val plaintextGet = Action {
    Ok("Hello, World!")
  }

  val jsonPost = Action(parse.json) { request =>
    Ok(toJson(request.body))
  }

  val plaintextPost = Action(parse.tolerantText) { request =>
    Ok(request.body)
  }
}

case class HelloWorld(message: String)