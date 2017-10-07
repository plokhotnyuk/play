package microservice

import javax.inject.Singleton
import com.google.inject.Inject
import play.api.libs.json.Json._
import play.api.libs.json.{JsValue, OFormat}
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HelloWorldController @Inject() (components: ControllerComponents)
                                     (implicit ec: ExecutionContext) extends AbstractController(components) {
  implicit val helloWorldFormat: OFormat[HelloWorld] = format[HelloWorld]
  val jsonGet: Action[AnyContent] = components.actionBuilder.async {
    Future(Ok(toJson(HelloWorld("Hello, World!"))))
  }
  val plaintextGet: Action[AnyContent] = components.actionBuilder.async {
    Future(Ok("Hello, World!"))
  }
  val jsonPost: Action[JsValue] = components.actionBuilder.async(parse.json) { request =>
    Future(Ok(toJson(request.body)))
  }
  val plaintextPost: Action[String] = components.actionBuilder.async(parse.tolerantText) { request =>
    Future(Ok(request.body))
  }
}

case class HelloWorld(message: String)