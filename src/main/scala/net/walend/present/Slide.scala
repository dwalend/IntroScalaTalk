package net.walend.present

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

  def name:String
}

case class SimpleSlide(name:String,items:Item*) extends Slide

trait Item

trait Line extends Item

object BlankLine extends Line

object TextLine {
  def apply(text:String,style:Style = Style.HeadLine) = FragLine(Seq(TextFragment(text)),style)
}

object LinkTextLine extends {
  def apply(text:String,urlText:String,style:Style) = FragLine(Seq(LinkFragment(text,urlText)),style)
}

trait Block extends Item

case class CodeBlock(code:String,syntax:CodeSyntax = CodeSyntax.Scala) extends Block

trait Fragment

case class TextFragment(text:String,style:Style = Style.Plain) extends Fragment

case class LinkFragment(frag:TextFragment,urlText:String) extends Fragment

object LinkFragment extends ((TextFragment,String) => LinkFragment) {
  def apply(text:String,urlText:String,style:Style):LinkFragment = LinkFragment(TextFragment(text,style),urlText)
  def apply(text:String,urlText:String):LinkFragment = LinkFragment(TextFragment(text),urlText)
}

object EmptyFragment extends Fragment

case class FragLine(frags:Seq[Fragment],style: Style = Style.SupportLine) extends Line

sealed case class Style(name:String)

object Style {
  val Title = Style("Title")
  val SubTitle = Style("SubTitle")
  val HeadLine = Style("HeadLine")
  val SupportLine = Style("SupportLine")
  val TertiaryLine = Style("TertiaryLine")
  val ScalaCode = Style("ScalaCode")
  val Quote = Style("Quote")
  val Plain = Style("Plain")

  val allStyles = Set(Title,SubTitle,HeadLine,SupportLine,TertiaryLine,ScalaCode,Plain)
}

sealed case class CodeSyntax(name:String)

object CodeSyntax {
  val Scala = CodeSyntax("Scala")
  val Java = CodeSyntax("Java")
  val Bash = CodeSyntax("Bash")
  val Sql = CodeSyntax("Sql")

  val allCodeSyntax = Set(Scala,Java,Bash)
}

object Shortcuts {

  def t(text:String) = TextLine(text,Style.Title)
  def t(text:String,link:String) = LinkTextLine(text,link,Style.Title)
  def t(frags:Fragment*) = FragLine(frags,Style.Title)

  def st(text:String) = TextLine(text,Style.SubTitle)
  def st(text:String,link:String) = LinkTextLine(text,link,Style.SubTitle)
  def st(frags:Fragment*) = FragLine(frags,Style.SubTitle)

  def l1(text:String) = TextLine(text,Style.HeadLine)
  def l1(text:String,link:String) = LinkTextLine(text,link,Style.HeadLine)
  def l1(frags:Fragment*) = FragLine(frags,Style.HeadLine)

  def l2(text:String) = TextLine(text,Style.SupportLine)
  def l2(text:String,link:String) = LinkTextLine(text,link,Style.SupportLine)
  def l2(frags:Fragment*) = FragLine(frags,Style.SupportLine)

  def l3(text:String) = TextLine(text,Style.TertiaryLine)
  def l3(text:String,link:String) = LinkTextLine(text,link,Style.TertiaryLine)
  def l3(frags:Fragment*) = FragLine(frags,Style.TertiaryLine)

  def c(text:String) = TextLine(text,Style.ScalaCode)
  def c(text:String,link:String) = LinkTextLine(text,link,Style.ScalaCode)

  def q(text:String) = TextLine(text,Style.Quote)
  def q(text:String,link:String) = LinkTextLine(text,link,Style.Quote)

  def p(text:String) = TextLine(text,Style.Plain)
  def p(text:String,link:String) = LinkTextLine(text,link,Style.Plain)
  def p(frags:Fragment*) = FragLine(frags,Style.Plain)

  val blank = BlankLine

}