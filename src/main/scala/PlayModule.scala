import microservice.{MetricsFilterImpl, MetricsFilter}
import play.api._
import play.api.inject.Module

class PlayModule extends Module {
  def bindings(environment: Environment, configuration: Configuration) =
    Seq(bind[MetricsFilter].to[MetricsFilterImpl].eagerly())
}