name := "play"
organization := "com.github.plokhotnyuk"
version := "1.0-SNAPSHOT"
scalaVersion := "2.12.6"
val root = (project in file("."))
  .enablePlugins(PlayScala, PlayNettyServer)
  .disablePlugins(PlayAkkaHttpServer)
  .disablePlugins(PlayLayoutPlugin)
libraryDependencies ++= Seq(
  guice,
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "0.29.16",
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "0.29.16",
  "com.typesafe.play" %% "play-ws" % "2.6.13"
)
javaOptions := Seq(
  "-server",
  "-Xms2g",
  "-Xmx2g",
  "-XX:NewSize=1g",
  "-XX:MaxNewSize=1g",
  "-XX:ReservedCodeCacheSize=512m",
  "-XX:+UseG1GC",
  "-XX:MaxGCPauseMillis=10",
  "-XX:+UseNUMA",
  "-XX:-UseBiasedLocking",
  "-XX:+AlwaysPreTouch"
)