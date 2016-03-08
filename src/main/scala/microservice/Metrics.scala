package microservice

import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject
import javax.inject.Singleton
import akka.stream.Materializer
import play.api.mvc._
import scala.concurrent.Future

@Singleton
class MetricsController extends Controller {
  val metrics = Action {
    Ok(Metrics.cnt.get.toString)
  }
}

trait MetricsFilter extends Filter

class MetricsFilterImpl @Inject() (implicit val mat: Materializer) extends MetricsFilter {
  def apply(nextFilter: (RequestHeader) => Future[Result])(rh: RequestHeader): Future[Result] = {
    Metrics.cnt.incrementAndGet()
    nextFilter(rh)
  }
}

object Metrics {
  val cnt = new AtomicLong
}