import javax.inject.Inject
import microservice.MetricsFilter
import play.api.http.HttpFilters

class Filters @Inject()(metricsFilter: MetricsFilter) extends HttpFilters {
  val filters = Seq(metricsFilter) // replace by Nil and see difference in Gatling reports
}