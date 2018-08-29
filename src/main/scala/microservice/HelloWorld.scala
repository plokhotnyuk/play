package microservice

import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._

case class HelloWorld(message: String)

object HelloWorld {
  implicit val helloWorldCodec: JsonValueCodec[HelloWorld] = JsonCodecMaker.make[HelloWorld](CodecMakerConfig())
}