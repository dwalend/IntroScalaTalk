package net.walend.intro2scala

import net.walend.present.{LinkTextLine, Style, TextLine, SimpleSlide}

/**
 *
 *
 * @author dwalend
 * @since v0.1.2
 */
object WrapUp {

  val WrapUp = SimpleSlide("WrapUp",Seq(
    TextLine("Scala Leads Where We Need To Go",Style.Title),
    TextLine("Java Follows Scala",Style.HeadLine),
    TextLine("JDK8 Streams, Lambdas, Optional",Style.SupportLine),
    TextLine("Modern Hardware Provides More Cores",Style.HeadLine),
    TextLine("Not a Faster Solver For the Halting Problem",Style.SupportLine),
    TextLine("Functional Programming Can Get Back Your 'Free Lunch'",Style.SupportLine),
    TextLine("Big Data Tools Are Written in Scala - Spark, Storm, Neo4J, Many Others",Style.SupportLine),
    TextLine("Scala.js Compiles Scala to JavaScript",Style.HeadLine),
    TextLine("Even Browser Code Could Be Fun",Style.SupportLine)
  ))

  val PartingQuote = SimpleSlide("PartingQuote",Seq(
    TextLine("Always favor readability ...",Style.Quote),
    TextLine("... take advantage of simple language features that afford great power but avoid the esoteric ones ...",Style.Quote),
    TextLine("... complexity is the tax of sophistication - you must always ensure that its utility exceeds its cost.",Style.Quote),
    LinkTextLine("Twitter's _Effective Scala","http://twitter.github.io/effectivescala/",Style.Plain)

  ))

  val slides = Seq(WrapUp,PartingQuote)

}
