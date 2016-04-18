package microservice

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class JsonPostRouteGatlingTest extends Simulation {
  val lowScenariosMsgsPerSecond = 10000
  val lowScenariosConnectionsPerSecond = 50
  val stressScenariosConnectionsPerSecond = 100
  val msgsPerSecondPerConnection = lowScenariosMsgsPerSecond / lowScenariosConnectionsPerSecond
  val httpConf = http
    .baseURL("http://localhost:9000")
    .maxConnectionsPerHost(1)
    .shareConnections
  val jsonPost = repeat(msgsPerSecondPerConnection)(exec(
    http("json_POST")
      .post("/json")
      .body(StringBody("""{"message":"Hello, World!"}"""))
      .asJSON
      .check(status.in(200))
  ))
  val partDuration = 30.seconds
  setUp(scenario("JsonPost")
    .exec(jsonPost)
    .inject(
      rampUsersPerSec(1) to lowScenariosConnectionsPerSecond during partDuration,
      constantUsersPerSec(lowScenariosConnectionsPerSecond) during partDuration,
      rampUsersPerSec(lowScenariosConnectionsPerSecond) to stressScenariosConnectionsPerSecond during partDuration,
      nothingFor(partDuration),
      constantUsersPerSec(lowScenariosConnectionsPerSecond) during partDuration
    )
  ).protocols(httpConf)
}