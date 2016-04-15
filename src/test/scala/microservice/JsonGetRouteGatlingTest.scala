package microservice

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class JsonGetRouteGatlingTest extends Simulation {
  val lowScenariosMsgsPerSecond = 20000
  val lowScenariosConnectionsPerSecond = 50
  val stressScenariosConnectionsPerSecond = 100
  val msgsPerSecondPerConnection = lowScenariosMsgsPerSecond / lowScenariosConnectionsPerSecond
  val httpConf = http
    .baseURL("http://localhost:9000")
    .maxConnectionsPerHost(1)
    .shareConnections
  val jsonGet = repeat(msgsPerSecondPerConnection)(exec(
    http("json_GET")
      .get("/json")
      .check(status.in(200))
  ))
  val partDuration = 30.seconds
  setUp(scenario("JsonGet")
    .exec(jsonGet)
    .inject(
      rampUsersPerSec(1) to lowScenariosConnectionsPerSecond during partDuration,
      constantUsersPerSec(lowScenariosConnectionsPerSecond) during partDuration,
      rampUsersPerSec(lowScenariosConnectionsPerSecond) to stressScenariosConnectionsPerSecond during partDuration,
      nothingFor(partDuration),
      constantUsersPerSec(lowScenariosConnectionsPerSecond) during partDuration
    )
  ).protocols(httpConf)
}