ThisBuild / name := "wiplimit"
ThisBuild / version := "1.0.0"
ThisBuild / organization := "com.thebho"
ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    description := "WIP Limit Simulator - Simulates Work In Progress limits using dice rolls",
    mainClass in Compile := Some("example.Hello"),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked",
      "-Xlint"
    )
  )
