import dk.kalhauge.slides.*
import dk.kalhauge.slides.elements.*
import section.functionsSection
import section.pragmaticSection
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

    section("Kotlin") {

      slide("Programming Examples") {
        java {
          +"""
          |class Person {
          |  private String name;
          |  }
          """
        }
        kotlin {
          +"""
          |class Person(var name: String)
          |"""
        }
      }

      section("An underview") {

        slide("Andet slide") {
          title = "Second slide"
          subtitle = "Lund og Bendsen"
          text("Killroy was here")
          list {
            text("Erste")
            text("Zwiete")
            text("Dritte")
            }
          }

        }

      }
    }
  File("/Users/AKA/DatSoftLyngby/4sem2017fall-iot/slides/01-Introduction/Kotlin-Lund-Bendsen.tex").printWriter().use { out ->
    show.print(out)
    }

  }