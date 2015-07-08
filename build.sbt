scalaVersion := "2.11.7"

libraryDependencies ++= akka

val akkaVersion = "2.4-M2"

lazy val akka = Seq(
   "com.typesafe.akka" %% "akka-actor" % akkaVersion,
   "com.typesafe.akka" %% "akka-typed-experimental" % akkaVersion
)

scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-unchecked",
    "-feature",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-Xlint",
    "-Xfatal-warnings",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture"
)