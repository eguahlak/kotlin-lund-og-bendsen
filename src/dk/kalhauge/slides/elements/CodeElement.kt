package dk.kalhauge.slides.elements

import dk.kalhauge.slides.Element
import dk.kalhauge.slides.Slide
import java.io.PrintWriter

class CodeElement(val language: String, var code: String = "") : Element() {

  operator fun String.unaryPlus() { code += this }

  override fun print(out: PrintWriter, indent: String) {
    out.println("""$indent\begin{$language}""")
    out.println(code.trimMargin())
    out.println("""$indent\end{$language}""")
    }

  }

fun Slide.kotlin(code: String = "", init: CodeElement.() -> Unit = {}) = element(CodeElement("kotlin", code), init)
fun Slide.java(code: String = "", init: CodeElement.() -> Unit = {}) = element(CodeElement("java", code), init)
fun Slide.csharp(code: String = "", init: CodeElement.() -> Unit = {}) = element(CodeElement("cs", code), init)

fun code(text: String) = "\\code{$text}"
operator fun String.unaryMinus() = "\\code{$this}"