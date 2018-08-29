package microservice

import akka.util.ByteString
import javax.inject.Singleton
import com.github.plokhotnyuk.jsoniter_scala.core._
import com.google.inject.Inject
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class HelloWorldController @Inject() (components: ControllerComponents)
                                     (implicit ec: ExecutionContext) extends AbstractController(components) {
  val jsonGet: Action[AnyContent] = components.actionBuilder.async {
    Future(Ok(ByteString.fromArrayUnsafe(writeToArray(HelloWorld("Hello, World!")))))
  }
  val plaintextGet: Action[AnyContent] = components.actionBuilder.async {
    Future(Ok("Hello, World!"))
  }
  val jsonPost: Action[ByteString] = components.actionBuilder.async(parse.byteString) { request =>
    if (request.headers.get("Content-Type").contains("application/json; charset=utf-8")) {
      val message = readFromArray[HelloWorld](request.body.toArray)
      Future(Ok(ByteString.fromArrayUnsafe(writeToArray(message))))
    } else Future(NotAcceptable)
  }
  val plaintextPost: Action[String] = components.actionBuilder.async(parse.tolerantText) { request =>
    Future(Ok(request.body))
  }
}