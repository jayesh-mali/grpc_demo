name := "grpc_demo"
import scalapb.compiler.Version.{grpcJavaVersion, scalapbVersion}

version := "0.1"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file(".")).
  aggregate(service)

val service = project.settings(
  name := "service",
  libraryDependencies ++= Seq(
    "proto_plugin" %% "proto_plugin" % "0.1.0-SNAPSHOT",
    "com.github.pjfanning" %% "scala-faker" % "0.5.3"
  )
)

val proto_plugin = project.settings(
  name := "proto_plugin",
  libraryDependencies ++= Seq(
    "io.grpc" % "grpc-netty" % grpcJavaVersion,
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapbVersion
  ),
  Compile / PB.targets += scalapb
    .gen(
      flatPackage = true
    ) -> (Compile / sourceManaged).value,
  Compile / PB.protoSources := Seq(file("proto_plugin/protobuf/")),
)


