// Comment to get more information during initialization
logLevel := Level.Warn

// https://github.com/scoverage/sbt-scoverage/releases
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.1.0")

// https://github.com/scoverage/sbt-coveralls/releases
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.3.11")

// https://github.com/codacy/sbt-codacy-coverage/releases
addSbtPlugin("com.codacy" % "sbt-codacy-coverage" % "3.0.3")

// https://github.com/scalameta/scalafmt
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")

// https://github.com/sbt/sbt-ci-release
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.12")

addDependencyTreePlugin

// https://github.com/aiyanbo/sbt-dependency-updates
addSbtPlugin("org.jmotor.sbt" % "sbt-dependency-updates" % "1.2.9")

// needed until https://github.com/sbt/sbt-ci-release/pull/298 is merged
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.11.0")

// Scoverage coverage-parser relies on version 1.x
// scala-xml 2.0 is most of the time non breaking
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
