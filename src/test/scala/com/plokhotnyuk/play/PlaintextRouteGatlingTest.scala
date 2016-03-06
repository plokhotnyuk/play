package com.plokhotnyuk.play

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class PlaintextRouteGatlingTest extends Simulation {
  val lowScenariosMsgsPerSecond = 20000
  val lowScenariosConnectionsPerSecond = 50
  val stressScenariosConnectionsPerSecond = 100
  val msgsPerSecondPerConnection = lowScenariosMsgsPerSecond / lowScenariosConnectionsPerSecond
  val httpConf = http
    .baseURL("http://localhost:9000")
    .maxConnectionsPerHost(1)
    .shareConnections
  val plaintextGet = repeat(msgsPerSecondPerConnection)(exec(
    http("plaintext_GET")
      .get("/plaintext")
      .check(status.in(200))
  ))
  val partDuration = 30.seconds
  setUp(scenario("PlaintextGet")
    .exec(plaintextGet)
    .inject(
      rampUsersPerSec(1) to lowScenariosConnectionsPerSecond during partDuration,
      constantUsersPerSec(lowScenariosConnectionsPerSecond) during partDuration,
      rampUsersPerSec(lowScenariosConnectionsPerSecond) to stressScenariosConnectionsPerSecond during partDuration,
      nothingFor(partDuration),
      constantUsersPerSec(lowScenariosConnectionsPerSecond) during partDuration
    )
  ).protocols(httpConf)
}