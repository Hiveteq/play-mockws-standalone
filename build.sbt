import scala.collection.immutable

ThisBuild / organization     := "io.github.hiveteq.play"
ThisBuild / organizationName := "Hiveteq"
// Mandatory metadata for the release to the Sonatype Central Portal.
// publishTo / credentials / signing are managed by sbt-ci-release 1.11.x.
ThisBuild / homepage   := Some(url("https://github.com/hiveteq/play-mockws-standalone"))
ThisBuild / licenses   := List("MIT" -> url("http://opensource.org/licenses/MIT"))
ThisBuild / developers := List(
  Developer(
    "sdudzin",
    "Siarhei Dudzin",
    "",
    url("https://hiveteq.github.io")
  )
)

ThisBuild / versionScheme := Some("early-semver")
ThisBuild / scalaVersion  := scala3
ThisBuild / fork          := true
ThisBuild / resolvers += "Typesafe repository".at("https://repo.typesafe.com/typesafe/releases/")
// sbt 2.x requires JDK 17+ to run, but the published artifacts must stay
// usable on JDK 11 (Play 3.0's baseline), so target Java 11 bytecode.
ThisBuild / scalacOptions ++= Seq("-release", "11")

fork := true

val scala213                = "2.13.13"
val scala3                  = "3.3.8"
val playVersion             = "3.0.11"
val playWsStandaloneVersion = "3.0.13"

val testDependencies: Seq[ModuleID] = Seq(
  "org.scalatest"     %% "scalatest"       % "3.2.20",
  "org.scalatestplus" %% "scalacheck-1-17" % "3.2.18.0",
  "org.scalacheck"    %% "scalacheck"      % "1.19.0",
  "org.mockito"        % "mockito-core"    % "5.23.0"
).map(_ % Test)

val noPublishingSettings = Seq(
  publish / skip      := true,
  publishLocal / skip := true
)

def scalaCollectionsCompat(scalaVersion: String): immutable.Seq[ModuleID] = {
  CrossVersion.partialVersion(scalaVersion) match {
    case Some((2, n)) if n == 12 =>
      List("org.scala-lang.modules" %% "scala-collection-compat" % "2.14.0")
    case _ =>
      Nil
  }
}

addCommandAlias("format", ";scalafmtSbt;scalafmtAll")

lazy val root = (project in file("."))
  .settings(
    name := "play-mockws-standalone-root",
  )
  .settings(noPublishingSettings)
  .aggregate(play30)

lazy val play30 = (project in file("play-mockws-standalone"))
  .settings(
    name               := "play-mockws-standalone",
    crossScalaVersions := Seq(scala213, scala3)
  )
  .settings(
    libraryDependencies ++= Seq(
      "org.playframework" %% "play-ahc-ws-standalone"  % playWsStandaloneVersion % "provided",
      "org.playframework" %% "play-ws-standalone-json" % playWsStandaloneVersion % "provided",
      "org.playframework" %% "play-ws-standalone-xml"  % playWsStandaloneVersion % "provided",
      "org.playframework" %% "play-test"               % playVersion             % "provided"
    ),
    libraryDependencies ++= testDependencies
  )
