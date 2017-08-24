package dk.kalhauge.slides.elements

import dk.kalhauge.slides.Element
import dk.kalhauge.slides.Slide
import java.io.PrintWriter

class ImageElement(val path: String, val scale: Double? = null) : Element() {
  var url: String? = null

  override fun print(out: PrintWriter, indent: String) {
    if (scale != null) {
      out.println("""
        |\begin{center}
        | \includegraphics[scale=${scale}]{image/$path}
        |\end{center}
        """.trimMargin() )
      }
    else {
      out.println("""
        |\begin{center}
        | \includegraphics[width=\textwidth]{image/$path}
        |\end{center}
        """.trimMargin() )
      }
    }

  }

fun Slide.image(path: String, scale: Double? = null, init: ImageElement.() -> Unit = { }) = element(ImageElement(path, scale), init)
