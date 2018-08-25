lazy val akkaHttpVersion = "10.1.3"
lazy val akkaVersion    = "2.5.14"
lazy val circe = "0.9.3"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.blindheron",
      scalaVersion    := "2.12.6"
    )),
    name := "akka-http",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,
      "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0",
      "de.heikoseeberger" %% "akka-http-circe" % "1.20.1",

      "io.circe" %% "circe-generic" % circe,


      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion     % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"            % "3.0.5"         % Test
    )
  )

