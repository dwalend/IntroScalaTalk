package net.walend.present

import net.walend.present.Style.{ScalaCode, HeadLine, SupportLine, SubTitle, Title,Plain,TertiaryLine,Quote}

/**
 * Render a slide stack in MarkDown
 *
 * @author david 
 * @since 4/30/15
 */
object MarkDown {

  def render(presentation:Presentation):Seq[String] = {

    val footers = makeFooters(presentation)

    val slideText = presentation.slides.map(render)

    val markDownForSlides = slideText.zip(footers).map(x => s"${x._1}${x._2}")

    markDownForSlides
  }

  def makeFooters(presentation: Presentation):Seq[String] = {
    val slideOptions = presentation.slides.map(Some(_))
    val nextSlides: Seq[Option[Slide]] = slideOptions.tail :+ None
    val previousSlides: Seq[Option[Slide]] = None +: slideOptions //No good reason to drop the last item

    val triple: Seq[(Slide, Option[Slide], Option[Slide])] = presentation.slides.zip(previousSlides).zip(nextSlides).map(x => (x._1._1,x._1._2,x._2))

    def footer(previous:Option[Slide],next:Option[Slide]):String = {

      val emtpyFrag:Fragment = EmptyFragment
      val prevFrag = previous.fold(emtpyFrag)(previous => LinkFragment("Prev",fileName(previous)))
      val nextFrag = next.fold(emtpyFrag)(next => LinkFragment("Next",fileName(next)))

      val line = FragLine(Seq(prevFrag,nextFrag))
      render(line)
    }
    triple.map(x => footer(x._2,x._3))
  }

  def fileName(slide:Slide):String = {
    s"${slide.name}.md"
  }

  def render(slide:Slide):String = {
    slide.items.map(render).mkString("")
  }

  def render(item:Item):String = {
    item match {
      case blankLine: Line if blankLine == BlankLine => render(blankLine)
      case codeBlock:CodeBlock => render(codeBlock)
      case fragLine:FragLine => render(fragLine)
    }
  }

  def render(blankLine: Line):String = "\n"

  def render(codeBlock: CodeBlock):String = {
    val prefix = s"```${codeBlock.syntax.name}\n"
    val postfix = "\n```\n"
    s"$prefix${codeBlock.code}$postfix"
  }

  def render(fragLine: FragLine):String = {
    val prefix = linePrefix(fragLine.style)
    val postfix = linePostfix.getOrElse(fragLine.style,"\n")
    prefix + fragLine.frags.map(render).mkString(" ") + postfix
  }

  def render(frag:Fragment):String = {
    frag match {
      case text:TextFragment => render(text)
      case link:LinkFragment => render(link)
      case emptyFragment:Fragment if emptyFragment == EmptyFragment => ""
    }
  }

  def render(textFragment: TextFragment):String = {
    val prefix = if(textFragment.style == ScalaCode) linePrefix(ScalaCode)
    else ""
    val postfix = linePostfix.getOrElse(textFragment.style,"")
    s"$prefix${textFragment.text}$postfix"
  }

  def render(linkFragment: LinkFragment):String = {
    s"[${render(linkFragment.frag)}](${linkFragment.urlText})"
  }

  val linePrefix = Map(
    Title -> "#",
    SubTitle -> "###",
    HeadLine -> "##",
    SupportLine -> "###",
    TertiaryLine -> "###",
    ScalaCode -> "```Scala\n",
    Quote -> "> ",
    Plain -> ""
  )

  val linePostfix = Map[Style,String](
    ScalaCode -> "\n```\n"
  )

}
