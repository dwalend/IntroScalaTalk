import scalatex.Hello
import scalatex.site.Site

import ammonite.ops._

/**
 *
 *
 * @author dwalend
 * @since v0.0
 */
object IntroToScala {

  val site = new scalatex.site.Site {
    def content = Map("index.html" -> Hello())
  }

  def main (args: Array[String]) {
    site.renderTo(cwd/'target/'site)
  }


}
