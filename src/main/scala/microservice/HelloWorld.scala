package microservice

case class HelloWorld(message: String)

object HelloWorld {
  import com.github.plokhotnyuk.jsoniter_scala.core.JsonValueCodec
  import com.github.plokhotnyuk.jsoniter_scala.macros.{JsonCodecMaker, CodecMakerConfig}

  implicit val helloWorldCodec: JsonValueCodec[HelloWorld] = JsonCodecMaker.make[HelloWorld]
}