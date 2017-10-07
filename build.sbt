name := "play"
organization := "com.github.plokhotnyuk"
version := "1.0-SNAPSHOT"
scalaVersion := "2.12.3"
val root = (project in file("."))
  .enablePlugins(PlayScala, PlayNettyServer)
  .disablePlugins(PlayAkkaHttpServer)
  .disablePlugins(PlayLayoutPlugin)
libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-ws" % "2.6.6"
)
javaOptions := Seq(
  "-server",
  "-Xms1g",
  "-Xmx1g",
  "-XX:NewSize=512m",
  "-XX:ReservedCodeCacheSize=128m",
  "-XX:+UseG1GC",
  "-XX:MaxGCPauseMillis=10",
  "-XX:+UseNUMA",
  "-XX:-UseBiasedLocking",
  "-XX:+AlwaysPreTouch"
)