name := "play"
organization := "com.github.plokhotnyuk"
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.8"
val root = (project in file("."))
  .enablePlugins(PlayScala)
  .enablePlugins(GatlingPlugin)
  .disablePlugins(PlayLayoutPlugin)
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-ws" % "2.5.2",
  "com.typesafe.akka" %% "akka-actor" % "2.4.4",
  "com.typesafe.akka" %% "akka-stream" % "2.4.4",
  "com.typesafe.akka" %% "akka-slf4j" % "2.4.4",
  "io.netty" % "netty-codec-http" % "4.0.36.Final",
  "io.netty" % "netty-transport-native-epoll" % "4.0.36.Final" classifier "linux-x86_64",
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