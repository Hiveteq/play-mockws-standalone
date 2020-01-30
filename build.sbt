name := "play-mockws-standalone"

scalacOptions ++= Seq("-deprecation", "-feature")

organization := "de.leanovate.play-mockws"

val playVersion = "2.8.0"

val playWsStandaloneVersion = "2.1.2"

fork := true

resolvers += "Typesafe repository".at("https://repo.typesafe.com/typesafe/releases/")

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-ahc-ws-standalone" % playWsStandaloneVersion % "provided",
  "com.typesafe.play" %% "play-ws-standalone-json" % playWsStandaloneVersion % "provided",
  "com.typesafe.play" %% "play-ws-standalone-xml" % playWsStandaloneVersion % "provided",
  "com.typesafe.play"      %% "play-test"               % playVersion % "provided",
  "org.scala-lang.modules" %% "scala-collection-compat" % "2.1.3"
)

libraryDependencies ++= Seq(
  "org.scalatest"  %% "scalatest"   % "3.0.8",
  "org.scalacheck" %% "scalacheck"  % "1.14.3",
  "org.mockito"    % "mockito-core" % "3.2.4"
).map(_ % Test)

publishTo := Some("DHL Artifactory".at("https://repo.dhlparcel.nl/artifactory/dhlparcel-sbt-local"))

credentials += Credentials(Path.userHome / ".sbt" / ".artifactory-credentials")

isSnapshot := false
