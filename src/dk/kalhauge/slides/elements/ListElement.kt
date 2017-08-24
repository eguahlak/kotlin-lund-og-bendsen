package dk.kalhauge.slides.elements

import dk.kalhauge.slides.*
import java.io.PrintWriter

class ListElement : Element(), ListableElement {
  val elements = mutableListOf<ListableElement>()

  override fun print(out: PrintWriter, indent: String) {
    out.println("""$indent\begin{itemize}""")
    elements.forEach {
      when (it) {
        is ListElement -> it.print(out, indent+"  ")
        else -> it.print(out, """$indent  \item """)
        }
      }
    out.println("""$indent\end{itemize}""")
    }

  fun <E: ListableElement> element(element: E, init: E.() -> Unit) {
    element.init()
    elements += element
    }

  }

fun Slide.list(init: ListElement.() -> Unit) = element(ListElement(), init)
fun ListElement.list(init: ListElement.() -> Unit) = element(ListElement(), init)