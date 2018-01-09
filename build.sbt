enablePlugins(AwsLambdaPlugin)

lazy val root = (project in file(".")).
  settings(
    name := "alexa-slack-link-sample",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.2",
    retrieveManaged := true
  ).
  settings(
    libraryDependencies ++= dependencies
  )

lazy val dependencies = Seq(
    "com.amazon.alexa" % "alexa-skills-kit" % "1.8.1",
    "org.json4s" %% "json4s-jackson" % "3.5.3",
    "com.amazonaws" % "aws-lambda-java-core" % "1.2.0",
    "org.skinny-framework" %% "skinny-http-client" % "2.5.2",
    "com.amazonaws" % "aws-lambda-java-log4j" % "1.0.0"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

region := Some("ap-northeast-1")
awsLambdaMemory := Some(192)
awsLambdaTimeout := Some(300)
