package dk.kalhauge.slides

import dk.kalhauge.slides.elements.TextElement
import java.io.PrintWriter

class Slide(var title: String) : Part() {
  var subtitle: String = ""
  val elements = mutableListOf<Element>()
  var background: String? = null

  override fun print(out: PrintWriter, indent: String) {
    val back = background
    if (back == null) {
      out.println("""$indent\begin{frame}[fragile]""")
      if (title.isNotBlank()) out.println("""$indent\frametitle{$title}""")
      if (subtitle.isNotBlank()) out.println("""$indent\framesubtitle{$subtitle}""")
      elements.forEach {
        when (it) {
          is TextElement -> it.print(out, indent+"  ", true)
          else -> it.print(out, indent+"  ")
          }
        }
      out.println("""$indent\end{frame}""")
      }
    else {
      out.println("""
        |$indent{
        |$indent  \usebackgroundtemplate{\includegraphics[height=\paperheight,width=\paperwidth]{image/$back}}
        |$indent  \setbeamertemplate{navigation symbols}{}
        |$indent  \begin{frame}[plain]
        |$indent  \end{frame}
        |$indent}
        """.trimMargin()
        )
      }
    out.println("%")
    out.println("%")
    out.println("%")
    }

  fun <E: Element> element(element: E, init: E.() -> Unit) {
    element.init()
    elements += element
    }

  }

fun Whole.slide(title: String = "", init: Slide.() -> Unit) = part(Slide(title), init)


