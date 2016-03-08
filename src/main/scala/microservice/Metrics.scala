package microservice

import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject
import javax.inject.Singleton
import akka.util.ByteString
import play.api.libs.streams.Accumulator
import play.api.mvc._

@Singleton
class MetricsController extends Controller {
  val metrics = Action {
    Ok(Metrics.cnt.get.toString)
  }
}

trait MetricsFilter extends EssentialFilter

class MetricsFilterImpl @Inject() extends MetricsFilter {
  def apply(nextFilter: EssentialAction) = new EssentialAction {
    override def apply(rh: RequestHeader): Accumulator[ByteString, Result] = {
      Metrics.cnt.incrementAndGet()
      nextFilter(rh)
    }
  }
}

object Metrics {
  val cnt = new AtomicLong
}