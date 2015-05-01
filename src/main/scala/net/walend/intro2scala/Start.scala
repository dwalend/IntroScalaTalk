package net.walend.intro2scala

import net.walend.present.{BlankLine, SimpleSlide, LinkTextLine, TextLine, Style}

/**
 * @author david 
 * @since 4/29/15
 */

object Start {

  val Cover = SimpleSlide (Seq(
    TextLine("An Introduction To Scala With Examples Using Slick and Spray", Style.Title),
    BlankLine,
    TextLine("David Walend", Style.SubTitle),
    TextLine("Boston Scala Meetup, May 20th, 2015", Style.SubTitle),
    LinkTextLine("Slides Online", "https://github.com/dwalend/IntroScalaTalk", Style.SubTitle)
  ))

  val Outline = SimpleSlide(Seq(
    TextLine("Scala", Style.HeadLine),
    TextLine("A Scalable Language", Style.SupportLine),
    BlankLine,
    TextLine("Slick", Style.HeadLine),
    TextLine("A minimalist Database layer", Style.SupportLine),
    BlankLine,
    TextLine("Spray", Style.HeadLine),
    TextLine("A micro web-service framework", Style.SupportLine),
    BlankLine,
    TextLine("Examples from HMS Catalyst SHRINE Data Steward App", Style.SupportLine)
  ))

  //todo shrine catalyst slide

  val ScalaWhat = SimpleSlide(Seq(
    TextLine("Scala",Style.Title),
    LinkTextLine("A Scalable Programming Language","http://www.scala-lang.org/what-is-scala.html",Style.HeadLine),
    TextLine("Scales in Scope",Style.HeadLine),
    TextLine("Scripting, Web pages, Desktop, Servers, Distributed",Style.SupportLine),
    TextLine("Scales for Differences in Knowledge and Experience",Style.HeadLine),
    TextLine("Enables Different Coding Styles",Style.SupportLine),
    TextLine("Strong Support for Procedural, OO, and Functional Styles",Style.SupportLine),
    TextLine("Has a Goal of Scaling Through Time",Style.HeadLine)
  ))

  val slides = Seq(Cover,Outline,ScalaWhat)
}

//todo a slide about shrine and gage the room
