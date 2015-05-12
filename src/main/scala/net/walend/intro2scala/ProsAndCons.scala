package net.walend.intro2scala

import net.walend.present
import net.walend.present.BlankLine
import net.walend.present.CodeBlock
import net.walend.present.LinkTextLine
import net.walend.present.SimpleSlide
import net.walend.present.Style
import net.walend.present.TextLine
import net.walend.present._

/**
 * @author david
 */

object ProsAndCons {


  val Pros1 = SimpleSlide("Pros1",Seq(
    TextLine("Scala - Pros", Style.Title),
    TextLine("Thinking About a Problem Results in Readable, Consice Code", Style.HeadLine),
    FragLine(Seq(TextFragment("See"),LinkFragment("Dijkstra's Algorithm","http://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode"),TextFragment("With"),LinkFragment("Semirings: Java vs Scala","http://dwalend.github.io/blog/2014/10/05/Semirings/"))),
    TextLine("FP Helps Make Code Comprehendable", Style.HeadLine),
    LinkTextLine("Let Your Pattern Book Collect Dust","http://alvinalexander.com/scala/how-scala-killed-oop-strategy-design-pattern#What_about_those_other_OOP_design_patterns", Style.HeadLine),
    TextLine("Gentle Learning Curve From Java",Style.HeadLine),
    TextLine("Use Scala as a Better Java on the First Day",Style.SupportLine),
    FragLine(Seq(TextFragment("Compare"),LinkFragment("Scala's Intents","http://www.scala-lang.org/what-is-html"),TextFragment("to"),LinkFragment("Java's","http://www.oracle.com/technetwork/java/intro-141325.html#334"))),
    TextLine("Knowledge of Java Libraries Carries Forward",Style.SupportLine),
    TextLine("Out \"Javas\" Java in all but Community and Uptake")
  ))

  val slides = Seq(Pros1)
}

//todo a slide about shrine and gage the room
