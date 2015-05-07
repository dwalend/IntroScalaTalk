package net.walend.intro2scala

import net.walend.present
import net.walend.present._

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
    TextLine("Deep Academic Roots in Category Theory Make Code Expressive"),
    TextLine("Has a Goal of Scaling Through Time",Style.HeadLine)
  ))

  val CodeStyle = SimpleSlide(Seq(
    TextLine("Scala Enables Different Coding Styles",Style.Title),
    TextLine("Custom Control Flow"),
    TextLine("First-class Functions and Higher-Order Functions",Style.SupportLine),
    TextLine("Strongly Typed - the Most Complete Type System in Use"),
    TextLine("Solid Platform for Domain-Specific Languages"),
    TextLine("Spray.io Routes",Style.SupportLine),
    TextLine("Style Support is Built From Language Primitives, Not Language Changes",Style.SupportLine),
    TextLine("Extremely Clear Code")
  ))

  val FuncOption = SimpleSlide(Seq(
    TextLine("Functional Programming with Option[T]",Style.Title),
    TextLine("Handle Empty Fields With Container",Style.HeadLine),
    TextLine("Replace null checks",Style.SupportLine),
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

  val FuncFold = SimpleSlide(Seq(
  //todo is fold an FP thing?
    TextLine("FP with Option.fold()()",Style.HeadLine),
    BlankLine,
    TextLine("Option's fold()() method takes two functions"),
    TextLine("def fold[Out](ifEmpty: ⇒ Out)(f: (T) ⇒ Out): Out",Style.ScalaCode),
    TextLine("Some[T].fold()() evaluates the right function with the value as an argument",Style.SupportLine),
    TextLine("None[T].fold()() evaluates the left (no-argument) function",Style.SupportLine),
    CodeBlock(
      s"""
         |val yourOption:Option[String] = ...
         |val stringToPrint = yourOption.fold(".")(string => s"!$$string!")
         |""".stripMargin),
    LinkTextLine("Read more about fold()()","https://coderwall.com/p/4l73-a/scala-fold-foldleft-and-foldright",Style.SupportLine)
  ))

  val slides = Seq(Cover,Outline,ScalaWhat,CodeStyle,FuncOption,FuncFold)
}

//todo a slide about shrine and gage the room
