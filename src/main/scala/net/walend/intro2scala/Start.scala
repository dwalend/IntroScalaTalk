package net.walend.intro2scala

import net.walend.present.{CodeBlock, LinkTextLine, BlankLine, Style, TextLine, SimpleSlide}


/**
 * @author david 
 * @since 4/29/15
 */

object Start {

  val Cover = SimpleSlide ("Cover",Seq(
    TextLine("An Introduction To Scala", Style.Title),
    TextLine("With Examples Using Slick and Spray", Style.Title),
    BlankLine,
    TextLine("David Walend", Style.SubTitle),
    TextLine("Boston Scala Meetup, May 20th, 2015", Style.SubTitle),
    LinkTextLine("Slides Online", "https://github.com/dwalend/IntroScalaTalk", Style.SubTitle)
  ))

  val Abstract = SimpleSlide("Abstract",Seq(TextLine(
    """The Boston Area Scala Enthusiasts MeetUp has had several requests for an introductory level talk on Scala. I recently put such a talk together for my coworkers at HMS that I'd like to share. I'll describe a bit about how Scala's founders intended it to be a scalable programming language, and cite examples that show some success. I will also share some anecdotes from my own career regarding pros and cons of Scala at work and in hobby code. Along the way I'll introduce case classes, Options, a little functional programming, Slick -- a database library, and Spray -- a web service library, all through example code pulled out of source control at my day job."""
    .stripMargin,Style.SupportLine)))

  val Outline = SimpleSlide("Outline",Seq(
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

  val ScalaWhat = SimpleSlide("ScalaWhat",Seq(
    TextLine("Scala",Style.Title),
    LinkTextLine("A Scalable Programming Language","http://www.scala-lang.org/what-is-scala.html",Style.HeadLine),
    TextLine("Scales in Scope",Style.HeadLine),
    TextLine("Scripting, Web pages, Desktop, Servers, Distributed",Style.SupportLine),
    TextLine("Roots in Category Theory Make Code Expressive"),
    TextLine("Scales for Differences in Knowledge and Experience",Style.HeadLine),
    TextLine("Enables Different Coding Styles",Style.SupportLine),
    TextLine("Strong Support for Procedural, OO, and Functional Styles",Style.SupportLine),
    TextLine("Creators Intend it to Scale Through Time",Style.HeadLine)
  ))

  val CodeStyle = SimpleSlide("CodStyle",Seq(
    TextLine("Scala Enables Different Coding Styles",Style.Title),
    TextLine("Custom Control Flow"),
    TextLine("First-class Functions and Higher-Order Functions",Style.SupportLine),
    CodeBlock("""  def configForTest[T](key:String,value:String)(block: => T):T = {
                |    val originalValue = System.getProperty(key)
                |    System.setProperty(key,value)
                |    try{
                |      block
                |    }
                |    finally {
                |      System.setProperty(key,originalValue)
                |    }
                |  }"""),
    TextLine("Strongly Typed - the Most Complete Type System in Use"),
    TextLine("Solid Platform for Domain-Specific Languages"),
    TextLine("Style Support is Built From Language Primitives, Not Language Changes",Style.HeadLine),
    TextLine("Extremely Clear Code",Style.HeadLine),
    TextLine("Use the Style That Best Matches the Task at Hand",Style.SupportLine)
  ))

  val FuncOption = SimpleSlide("FuncOption",Seq(
    TextLine("Functional Programming with Option[T]",Style.Title),
    TextLine("Handle Empty Fields With Container",Style.HeadLine),
    TextLine("Replace null checks, Cure for Sir Tony Hoare's ",Style.SupportLine),
    LinkTextLine(""""billion-dollar mistake ... the invention of the null reference in 1965"""","http://en.wikipedia.org/wiki/Tony_Hoare#Apologies_and_retractions",Style.SupportLine),
    BlankLine,
    TextLine("Option[T] is an abstract class with two subclasses",Style.HeadLine),
    TextLine("Some - When a Value is Present",Style.SupportLine),
    TextLine("None - When No Value is Present",Style.SupportLine),
    BlankLine,
    TextLine("Takes a type parameter T",Style.SupportLine),
    BlankLine,
    CodeBlock("""
val someString:Option[String] = Some("some string")
val noString:Option[String] = None
              """)
  ))

  val FuncFold = SimpleSlide("FuncFold",Seq(
    TextLine("Functional Programming with Option.fold()()",Style.HeadLine),
    BlankLine,
    TextLine("Option's fold()() method takes two functions"),
    TextLine("def fold[Out](ifEmpty: => Out)(f: (T) => Out): Out",Style.ScalaCode),
    TextLine("Some[T].fold()() evaluates the right function with the value as an argument",Style.SupportLine),
    TextLine("None[T].fold()() evaluates the left (no-argument) function",Style.SupportLine),
    CodeBlock(
      s"""
         |val yourOption:Option[String] = ...
         |val stringToPrint = yourOption.fold(".")(string => s"!$$string!")
         |""".stripMargin),
    LinkTextLine("Read more about fold()()","https://coderwall.com/p/4l73-a/scala-fold-foldleft-and-foldright",Style.SupportLine)
  ))

  val slides = Seq(Cover,Abstract,Outline,ScalaWhat,CodeStyle,FuncOption,FuncFold)
}

//todo a slide about shrine and gage the room
