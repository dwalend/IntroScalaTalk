package net.walend.present

import java.net.URL

/**
 *
 *
 * @author david 
 * @since 4/29/15
 */
trait Slide {
  def items:Seq[Item]
}

trait Item

case class Text(text:String,style:Style = Style.headLine) extends Item

case class LinkedText(text:String,url:URL,style:Style = Style.headLine) extends Item 

object LinkedText {
  def apply(text:String,urlText:String,style:Style = Style.headLine) = LinkedText(text,new URL(urlText),style)
}

sealed case class Style()

object Style {
  val title = Style()
  val subTitle = Style()
  val headLine = Style()
  val supportLine = Style()
  val scalaCode = Style()
}