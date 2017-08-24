package dk.kalhauge.slides

import java.io.PrintWriter

interface Printable {
  fun print(out: PrintWriter, indent: String)
  }

interface Whole : Printable {
  val parts: MutableList<Part>
  fun <P: Part> part(part: P, init: P.() -> Unit) {
    part.parent = this
    part.init()
    parts += part
    }

  }

abstract class Part : Printable {
  lateinit var parent: Whole
  }

class Show(var title: String) : Whole {
  var subtitle = ""
  var date = ""
  var author = ""

  override val parts = mutableListOf<Part>()

  fun print(out: PrintWriter = PrintWriter(System.out, true)) { print(out, "") }

  override fun print(out: PrintWriter, indent: String) {

    out.println("""
      |\documentclass{beamer}
      |\makeatletter
      |\def\input@path{{../setup/}{.}}
      |\makeatother
      |\graphicspath{{../setup/}{.}}
      |\usetheme{CPH}
      |
      |\title{$title}
      |\subtitle{$subtitle}
      |\date{$date}
      |\author{$author}
      |
      |\begin{document}
      |
      |\begin{frame}
      |  \titlepage
      |\end{frame}
      |
      |\section*{Outline}
      |
      |\begin{frame}[t]
      |\frametitle{Outline}
      |\tableofcontents
      |\end{frame}
      |
      """.trimMargin()
      )
    parts.forEach { it.print(out, "") }
    out.println("""\end{document}""")
    }

  }

fun show(title: String, init: Show.() -> Unit): Show {
  val show = Show(title)
  show.init()
  return show
  }

