package net.walend.intro2scala

import net.walend.present.{CodeBlock, LinkTextLine, BlankLine, Style, TextLine, SimpleSlide}
import net.walend.present.Shortcuts._

/**
 * @author david 
 * @since 4/29/15
 */

object Start {

  val Cover = SimpleSlide ("Cover",
    t("An Introduction To Scala"),
    t("With Examples Using Slick and Spray"),
    blank,
    st("David Walend"),
    st("HUIT Summit, June 2nd, 2016"),
    st("Slides Online (MarkDown)", "https://dwalend.github.io/IntroScalaTalk/Cover.md")
  )

  val Abstract = SimpleSlide("Abstract",p(
    """I've had several requests for an introductory level talk on Scala, including both my department at work and the Boston Scala Meetup. I'll describe a bit about how Scala's founders intended it to be a scalable programming language, and cite examples that show some success. I will also share some anecdotes from my own career regarding pros and cons of Scala at work and in hobby code. Along the way I'll introduce case classes, Options, a little functional programming, maybe Slick -- a database library, and Spray -- a web service library, all through example code pulled out of source control at my day job.""" //TODO
  ))

  val Outline = SimpleSlide("Outline",
    l1("Scala"),
    l2("A Scalable Language"),
    blank,
    l2("Slick"),
    l3("A functional database library"),
    blank,
    l2("Spray"),
    l3("A micro web-service framework"),
    blank,
    l3("Examples from HMS Catalyst SHRINE Data Steward App")
  )

  //todo shrine catalyst slide
  val Shrine = SimpleSlide("SHRINE",p(
    """
      |SHRINE is a tool for discovering cohorts of similar patients for clinical trials and population studies by querying distributed EMR databases. Shrine started migrating (from Java) to Scala in 2008, making it one of the oldest open source Scala projects still running.
      |
      |Supported by the Harvard Catalyst Department for Clinical and Translational Sciences (Award 1 UL1 TR001102-01 from the National Center for Advancing TranslationalSciences [NCATS], a part of the National Institutes of Health)
    """.stripMargin
  ))

  val ScalaWhat = SimpleSlide("ScalaWhat",
    TextLine("Scala",Style.Title),
    LinkTextLine("A Scalable Programming Language","http://www.scala-lang.org/what-is-scala.html",Style.HeadLine),
    TextLine("Scales in Scope",Style.HeadLine),
    TextLine("Scripting, Web pages, Desktop, Servers, Distributed - Both Homo- and Heterogeneous",Style.SupportLine),
    TextLine("Roots in Category Theory Make Code Expressive"),
    TextLine("Scales for Differences in Knowledge and Experience",Style.HeadLine),
    TextLine("Enables Different Coding Styles",Style.SupportLine),
    TextLine("Strong Support for Procedural, OO, and Functional Styles",Style.SupportLine),
    TextLine("Creators Intend it to Scale Through Time",Style.HeadLine)
  )

  val CodeStyle = SimpleSlide("CodStyle",
    TextLine("Scala Enables Different Coding Styles",Style.Title),
    TextLine("Extremely Clear Code",Style.HeadLine),
    TextLine("Use the Style That Best Matches the Task at Hand",Style.SupportLine),
    TextLine("Styles Built From Language Primitives",Style.HeadLine),
    TextLine("Not Language Changes",Style.SupportLine),
    TextLine("Solid Platform for Domain-Specific Languages",Style.HeadLine)
  )

  val CodeStyleFromLanguage = SimpleSlide("CodeStyleFromLanguage",
    TextLine("Scala Enables Different Coding Styles",Style.Title),
    TextLine("Strongly Typed",Style.HeadLine),
    TextLine("The Most Complete Type System in Common Use",Style.SupportLine),
    TextLine("Custom Control Flow",Style.HeadLine),
    TextLine("First-class Functions and Higher-Order Functions",Style.SupportLine),
    CodeBlock("""  def configForTest[T](key:String,value:String)(block: => T):T = {
                |    val originalValue = System.getProperty(key)
                |    System.setProperty(key,value)
                |    try { block }
                |    finally { System.setProperty(key,originalValue) }
                |  }
                |...
                |  def testAutoApprove() {
                |    configForTest("shrine.steward.mode","AutoApprove"){
                |      ... //test code
                |    }
                |  }
                |
                |  """.stripMargin)
  )

  val FuncOption = SimpleSlide("FuncOption",
    TextLine("Functional Programming with Option[T]",Style.Title),
    TextLine("Handle Potentially Empty Fields With This Container",Style.HeadLine),
    TextLine("Replace Nulls And Null Checks, Fix Sir Tony Hoare's ",Style.SupportLine),
    LinkTextLine(""""billion-dollar mistake ... the invention of the null reference in 1965"""","http://en.wikipedia.org/wiki/Tony_Hoare#Apologies_and_retractions",Style.SupportLine),
    BlankLine,
    TextLine("Option[T] is an abstract class with two subclasses",Style.HeadLine),
    TextLine("Some - Holds the value when a value exists",Style.SupportLine),
    TextLine("None - When no value is present",Style.SupportLine),
    BlankLine,
    TextLine("Takes a type parameter T",Style.SupportLine),
    BlankLine,
    CodeBlock("""
val someString:Option[String] = Some("some string") //same as Option("some string")
val noString:Option[String] = None
val suspectString:Option[String] = Option(null) // == None
              """)
  )

  val FuncFold = SimpleSlide("FuncFold",
    TextLine("Functional Programming with Option's fold()()",Style.HeadLine),
    BlankLine,
    TextLine("Option[T]'s fold()() method takes two functions",Style.SupportLine),
    TextLine("def fold[Out](ifEmpty: => Out)(f: (T) => Out): Out",Style.ScalaCode),
    TextLine("Some[T].fold()() evaluates the right function with the value as an argument",Style.SupportLine),
    TextLine("None[T].fold()() evaluates the left (no-argument) function",Style.SupportLine),
    CodeBlock(
      s"""
         |val yourOption:Option[String] = ...
         |val stringToPrint = yourOption.fold(".")(string => s"!$$string!")
         |""".stripMargin),
    LinkTextLine("Read more about fold()()","https://coderwall.com/p/4l73-a/scala-fold-foldleft-and-foldright",Style.SupportLine)
  )

  val slides = Seq(Cover,Abstract,Outline,Shrine,ScalaWhat,CodeStyle,CodeStyleFromLanguage,FuncOption,FuncFold)
}

//todo a slide about shrine and gage the room
