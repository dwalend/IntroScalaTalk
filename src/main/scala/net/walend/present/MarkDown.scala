package net.walend.present

import net.walend.present.Style.{ScalaCode, HeadLine, SupportLine, SubTitle, Title}

/**
 * Render a slide stack in MarkDown
 *
 * @author david 
 * @since 4/30/15
 */
object MarkDown {

  def render(slide:Slide):String = {
    slide.items.map(render).mkString("")
  }

  def render(item:Item):String = {
    item match {
      case text: TextLine => render(text)
      case linkedText: LinkTextLine => render(linkedText)
      case blankLine: Line if blankLine == BlankLine => render(blankLine)
    }
  }

  def render(blankLine: Line):String = "\n"

  def render(text:TextLine):String = {
    val prefix = linePrefix(text.style)
    val postfix = linePostfix.getOrElse(text.style,"\n")
    s"$prefix${text.text}$postfix"
  }

  def render(text:LinkTextLine):String = {
    val prefix = linePrefix(text.style)
    val postfix = linePostfix.getOrElse(text.style,"\n")
    s"$prefix[${text.text}](${text.url.toString})$postfix"
  }

  val linePrefix = Map(
    Title -> "#",
    SubTitle -> "###",
    HeadLine -> "##",
    SupportLine -> "###",
    ScalaCode -> "```Scala\n"
  )

  val linePostfix = Map[Style,String](
    ScalaCode -> "\n```\n"
  )

}
