name := "play"
organization := "com.github.plokhotnyuk"
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.8"
val root = (project in file("."))
  .enablePlugins(PlayScala, PlayAkkaHttpServer)
  .disablePlugins(PlayNettyServer)
  .enablePlugins(GatlingPlugin)
  .disablePlugins(PlayLayoutPlugin)
libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-ws" % "2.6.0-M1",
  "io.gatling" % "gatling-test-framework" % "2.2.0" % "test",
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.0" % "test"
)
javaOptions in Gatling := Seq(
  "-server",
  "-Xms1g",
  "-Xmx1g",
  "-XX:NewSize=512m",
  "-XX:ReservedCodeCacheSize=128m",
  "-XX:+UseG1GC",
  "-XX:MaxGCPauseMillis=30",
  "-XX:+UseNUMA",
  "-XX:-UseBiasedLocking",
  "-XX:+AlwaysPreTouch",
  "-Dio.netty.eventLoopThreads=" + Math.min(Math.max(sys.runtime.availableProcessors / 2, 1), 8)
)