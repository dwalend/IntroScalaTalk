package net.walend.present

import java.net.URL

/**
 *
 *
 * @author david 
 * @since 4/29/15
 */
trait Presentation {
  def slides:Seq[Slide]
}

case class SimplePresentation(slides:Seq[Slide]) extends Presentation {

}

trait Slide {
  def items:Seq[Item]
}

case class SimpleSlide(items:Seq[Item]) extends Slide

trait Item

trait Line extends Item

object BlankLine extends Line

case class TextLine(text:String,style:Style = Style.HeadLine) extends Line

case class LinkTextLine(text:String,url:URL,style:Style = Style.HeadLine) extends Line

object LinkTextLine extends ((String,URL,Style) => LinkTextLine) {
  def apply(text:String,urlText:String,style:Style):LinkTextLine = LinkTextLine(text,new URL(urlText),style)
  def apply(text:String,urlText:String):LinkTextLine = LinkTextLine(text,new URL(urlText))
}

trait Block extends Item

case class CodeBlock(code:String) extends Block

sealed case class Style(name:String)

object Style {
  val Title = Style("Title")
  val SubTitle = Style("SubTitle")
  val HeadLine = Style("HeadLine")
  val SupportLine = Style("SupportLine")
  val ScalaCode = Style("ScalaCode")

  val allStyles = Set(Title,SubTitle,HeadLine,SupportLine,ScalaCode)
}