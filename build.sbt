name := "cassandra-scaffolding"

version := "1.0"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "com.datastax.cassandra" % "cassandra-driver-core" % "3.3.0",
  "com.typesafe" % "config" % "1.3.1",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

