import dk.kalhauge.slides.show
import dk.kalhauge.slides.slide
import section.*
import java.io.File

fun main(args: Array<String>) {
  val show = show("Kotlin") {
    subtitle = "A pragmatic approach to programming"
    author = "Anders Kalhauge \\and Kasper Østerbye"
    date = "2017-08-25"

    slide {
      background = "kotlin-google"
      }

    pragmaticSection()
    functionsSection()
    specialSection()

    }
  File("/Users/AKA/DatSoftLyngby/4sem2017fall-iot/slides/01-Introduction/Kotlin-Lund-Bendsen.tex").printWriter().use { out ->
    show.print(out)
    }

  }