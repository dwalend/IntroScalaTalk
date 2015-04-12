scalatex.SbtPlugin.projectSettings

scalaVersion := "2.11.6"

lazy val readme = scalatex.ScalatexReadme(
  folder = "readme",
  url = "https://github.com/dwalend/IntroScalaTalk/tree/master",
  source = "Readme",
  targetFolder = "target/site"
)