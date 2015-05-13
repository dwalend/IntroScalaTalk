package net.walend.intro2scala

import net.walend.present.{SimplePresentation, MarkDown}

/**
 *
 *
 * @author dwalend
 * @since v0.1.2
 */
object IntroToScala {

  def main (args: Array[String]) {
    val allSlides = Start.slides ++ ProsAndCons.slides ++ Slick.slides
    val presentation = SimplePresentation(allSlides)

    MarkDown.render(presentation)
  }
}
