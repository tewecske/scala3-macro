name        := "scala3-macro"
description := "scala 3 macro"
version     := "0.0.1"


val sharedSettings = Seq(
  scalacOptions ++= Seq("-Xfatal-warnings", "-no-indent", "-deprecation"),
  libraryDependencies ++= Seq(
    "dev.zio"                       %% "zio-test"         % Versions.ZioVersion % Test,
  ),
  scalaVersion := Versions.Scala_3,
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
)

lazy val backend = project
  .in(file("backend"))
  .settings(
    sharedSettings,
    Compile / run / mainClass := Some("base.Main"),
    libraryDependencies ++= Seq(
    )
  )
  .dependsOn(shared)


lazy val shared = project
  .in(file("shared"))
  .settings(
    sharedSettings,
  )
