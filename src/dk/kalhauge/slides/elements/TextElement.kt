package dk.kalhauge.slides.elements

import dk.kalhauge.slides.*
import java.io.PrintWriter

class TextElement(var text: String) : Element(), ListableElement {
  override fun print(out: PrintWriter, indent: String) { out.println("$indent$text") }
  fun print(out: PrintWriter, indent: String, newLine: Boolean) {
    out.println("$indent$text\\\\[.5em]")
    }
  }

fun Slide.text(text: String) = element(TextElement(text), { })
fun ListElement.text(text: String) = element(TextElement(text), { })

