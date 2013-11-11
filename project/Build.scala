import com.earldouglas.xsbtwebplugin.Container
import sbt._
import Keys._
import sbt.Keys.settings
import com.earldouglas.xsbtwebplugin.WebPlugin._

import com.earldouglas.xsbtwebplugin.PluginKeys._

object BuildSettings {
  val buildVersion = "0.0.1"

  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "ch.hsr.intte",
    version := buildVersion,
    shellPrompt := ShellPrompt.buildShellPrompt,
    exportJars := false,
    autoScalaLibrary := false
  )
}

object Resolvers {
  val sunrepo = "Sun Maven2 Repo" at "http://download.java.net/maven/2"
  val sunrepoGF = "Sun GF Maven2 Repo" at "http://download.java.net/maven/glassfish"
  val oraclerepo = "Oracle Maven2 Repo" at "http://download.oracle.com/maven"
  val primerepo = "PrimeFaces Maven Repository" at "http://repository.primefaces.org"

  val oracleResolvers = Seq(sunrepo, sunrepoGF, oraclerepo, primerepo)
}

object Dependencies {
  val jettywebapp = "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container,test"
  val jettyjsp = "org.eclipse.jetty" % "jetty-jsp" % "8.1.7.v20120910" % "container,test"
  val javaxservlet = "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar")
  val javaxservletjsp = "org.eclipse.jetty.orbit" % "javax.servlet.jsp" % "2.2.0.v201112011158" % "container,test" artifacts Artifact("javax.servlet.jsp", "jar", "jar")

  val jsfapi = "org.apache.myfaces.core" % "myfaces-api" % "2.1.12" % "compile"
  val jsfimpl = "org.apache.myfaces.core" % "myfaces-impl" % "2.1.12" % "compile"
  val primefaces = "org.primefaces" % "primefaces" % "4.0"

  val elapi = "javax.el" % "el-api" % "2.2"
  val elimpl = "org.glassfish.web" % "el-impl" % "2.2" % "container,test"

  val logbackclassic = "ch.qos.logback" % "logback-classic" % "1.0.6"
}

object IntteUBuild extends Build {

  import Dependencies._
  import BuildSettings._

  val dpredditWebappDeps = Seq(
    jettywebapp,
    jettyjsp,
    javaxservlet,
    javaxservletjsp,

    jsfapi,
    jsfimpl,
    primefaces,

    elapi,
    elimpl,

    logbackclassic
  )
  val dpredditSpaDeps = Seq();

  lazy val dpreddit = Project(
    "dpreddit",
    file("."),
    settings = buildSettings ++ Seq(
      name := "DP Reddit"
    )
  ) dependsOn (dpredditWebapp)

  lazy val dpredditWebapp = Project(
    "dpreddit-webapp",
    file("webapp"),
    settings = buildSettings ++ webSettings ++ Seq(libraryDependencies ++= dpredditWebappDeps) ++ Seq (
      port in container.Configuration := 8080
    )
  ) dependsOn (dpredditSpa)

  lazy val dpredditSpa = Project(
    "dpreddit-spa",
    file("spa"),
    settings = buildSettings ++ Seq(libraryDependencies ++= dpredditSpaDeps)
  )
}