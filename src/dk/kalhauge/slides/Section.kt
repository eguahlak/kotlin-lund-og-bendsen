package dk.kalhauge.slides

import java.io.PrintWriter

class Section(val name: String) : Part(), Whole {
  override val parts = mutableListOf<Part>()

  override fun print(out: PrintWriter, indent: String) {
    if (parent is Section) out.println("""\subsection{$name}""")
    else out.println("""
      |\section{$name}
      |
      |\frame{\tableofcontents[currentsection]}
      |
      """.trimMargin())
    parts.forEach { it.print(out, indent) }
    }

  }

fun Whole.section(name: String, init: Section.() -> Unit) = part(Section(name), init)