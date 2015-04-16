scalatex.SbtPlugin.projectSettings

scalaVersion := "2.11.6"

lazy val deck = scalatex.ScalatexReadme(
  folder = "deck",
  url = "https://github.com/dwalend/IntroScalaTalk/tree/master",
  source = "Start",
  targetFolder = "target/deck"
)

