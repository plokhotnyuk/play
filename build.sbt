name := "play"
organization := "com.github.plokhotnyuk"
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.7"
val root = (project in file("."))
  .enablePlugins(PlayScala)
  .enablePlugins(GatlingPlugin)
  .disablePlugins(PlayLayoutPlugin)
libraryDependencies ++= Seq(
  ws,
  "io.gatling" % "gatling-test-framework" % "2.2.0-SNAPSHOT" % "test",
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.0-SNAPSHOT" % "test"
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