name := "Spark-Scala-SBT"

organization := "com.myOrg"

version := "0.1"

scalaVersion := "2.11.10"

autoScalaLibrary := false

val sparkVersion: String = "2.3.0"

val sparkDependencies = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)

libraryDependencies ++= sparkDependencies ++ testDependencies