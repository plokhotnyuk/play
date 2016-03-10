package microservice

import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject
import javax.inject.Singleton
import akka.util.ByteString
import play.api.libs.streams.Accumulator
import play.api.mvc._

class MetricsController @Inject() (metricsRegistry: MetricsRegistry) extends Controller {
  val metrics = Action {
    Ok(metricsRegistry.requestCounter.get.toString)
  }
}

trait MetricsFilter extends EssentialFilter

class MetricsFilterImpl @Inject() (metricsRegistry: MetricsRegistry) extends MetricsFilter {
  def apply(nextFilter: EssentialAction) = new EssentialAction {
    override def apply(rh: RequestHeader): Accumulator[ByteString, Result] = {
      metricsRegistry.requestCounter.incrementAndGet()
      nextFilter(rh)
    }
  }
}

@Singleton
class MetricsRegistry {
  val requestCounter = new AtomicLong
}