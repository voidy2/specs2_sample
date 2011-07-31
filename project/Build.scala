import sbt._
import Keys._

object MyBuild extends Build {
  import Dependencies._

  lazy val buildSettings = Seq(
    name := "My Project",
    organization := "org.myproject",
    version := "1.0",
    scalaVersion := "2.9.0-1",
    initialCommands in console := "import org.specs2._",
    offline := true,
    ivyLoggingLevel := UpdateLogging.Full,
    showTiming := true,
    timingFormat := {
      import java.text.DateFormat
      DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL)
    },
    showSuccess := true,
    shellPrompt := { s => 
      "%s@%s> ".format(Project.extract(s).currentRef.project, System.getProperty("user.name"))
    }
  )

  lazy val root = Project("root", file(".")) settings(
    docDirectory <<= baseDirectory(_ / "doc"),
    libraryDependencies ++= allDependencies,
    //testFrameworks += TestFrameworks.Specs2,
    testOptions := Seq(
      Tests.Filter(s => Seq("Spec", "Suite", "Unit", "all").exists(s.endsWith(_))),
      Tests.Argument(TestFrameworks.Specs2, "console", "html", "junitxml")
    ),
    parallelExecution in Test := true,
    checksums := Nil
  )

  override lazy val settings = super.settings ++ buildSettings
}

object Dependencies {
  val specs2 = Seq(
    "org.specs2" %% "specs2" % "1.5",
    "org.specs2" %% "specs2-scalaz-core" % "6.0.RC2" % "test"
  )
  val allDependencies = specs2
}

/**
import grizzled.sys._
import OperatingSystem._

// now do stuff with the plugin...
libraryDependencies ++=
  if (os ==Windows)
    ("org.example" % "windows-only" % "1.0") :: Nil
  else
    Nil
*/
