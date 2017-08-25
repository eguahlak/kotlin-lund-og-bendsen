package section

import dk.kalhauge.slides.*
import dk.kalhauge.slides.elements.*

fun Show.specialSection() = section("Other cool things") {

  section("Collection and Streams") {

    slide("Mappings and filtering") {
      kotlin("""
        |import java.io.File
        |
        |fun wordsOf(path: String, del: String): List<String> =
        |  File(path)
        |    .readLines()
        |    .map { line: String -> line.split(del) }    // 1
        |    .flatten()
        |    .filter { word -> word.isNotBlank() }       // 2
        |    .map { it.toLowerCase() }                   // 3
        """)
      text("Lambdas with (1) and without (2) types and with shorthand ${-"it"} (3)")
      kotlin("""
        |fun main(args: Array<String>) {
        |  wordsOf("shakespeare.txt", "[^A-Za-z']")
        |    .take(100)
        |    .forEach { println(it) }
        |  }
        """)
      }

    slide("Mappings and filtering") {
      kotlin("""
        |import java.io.File
        |
        |fun wordsOf(path: String, delimiter: String) =
        |  File(path)
        |    .readLines()
        |    .map { it.split(delimiter) }
        |    .flatten()
        |    .filter { it.isNotBlank() }
        |    .map { it.toLowerCase() }
        """)
      text("All shortened with ${-"it"} and most types inferred")
      }

    }

    section("Domain Specific languages") {

      slide("This slideshow is written in Kotlin") {
        kotlin("""
          |fun main(args: Array<String>) {
          |  val show = show("Kotlin") {
          |    subtitle = "A pragmatic approach to programming"
          |    author = "Anders Kalhauge \\and Kasper Osterbye"
          |    date = "2017-08-25"
          |
          |    slide { background = "kotlin-google" }
          |    pragmaticSection()
          |    functionsSection()
          |    specialSection()
          |    }
          |
          |  File("Kotlin.tex").printWriter().use { out ->
          |    show.print(out)
          |    }
          |  }
          """)
        }

      slide("A closer view on the slide definition") {
        kotlin("""
          |class Slide(var title: String) : Part() {
          |  var subtitle: String = ""
          |  val elements = mutableListOf<Element>()
          |
          |  override fun print(out: PrintWriter, ind: String) {
          |    (*\dots{}*)
          |    }
          |
          |  fun <E: Element> element(e: E, init: E.() -> Unit) {
          |    e.init()
          |    elements += e
          |    }
          |
          |  }
          """)
        }

      slide("And on an image element") {
        kotlin("""
          |class ImageElement(
          |    val path: String,
          |    val scale: Double? = null
          |    ) : Element() {
          |
          |  override fun print(out: PrintWriter, ind: String) {
          |    (*\dots{}*)
          |    }
          |
          |  }
          |
          |fun Slide.image(
          |    path: String,
          |    scale: Double? = null,
          |    init: ImageElement.() -> Unit = { }
          |    ) =
          |  element(ImageElement(path, scale), init)
          """)
        }

      }

    section("Coroutines") {

      slide("Coroutines by Kasper") {
        list {
          text("Asynchronous")
          text("Non-blocking")
          text("Only syntax is the ${-"suspend"} keyword, rest is library")
          text("Coroutine builders")
          list {
            text("${-"launch"}")
            text("${-"async"}")
            text("${-"runBlocking"}")
            text("${-"createSequence"}")
            text("${-"createIterator"}")
            }
          }
        }

      slide("A binary search tree example") {
        text("First the Tree class")
        kotlin("""
          |class Tree(var root: Node = Node.Empty())
          |  : Sequence<String> {
          |  
          |  override fun iterator() = root.iterator()
          |
          |  fun insert(data: String) {
          |    root = root.with(data)
          |    }
          |
          |  }
          """)
        }

      slide("And then the Node class structure") {
        kotlin("""
          |sealed class Node : Sequence<String> {
          |  abstract fun with(data: String): Node
          |
          |  data class Branch(
          |      val data: String,
          |      val left: Node = Empty(),
          |      val right: Node = Empty()
          |      ): Node() { (*\dots{}*) }
          |
          |  class Empty: Node() {
          |    override fun with(data: String) = Branch(data)
          |    override fun iterator() =
          |      emptySequence<String>().iterator()
          |    }
          |
          |  }
          """)
        }

      slide("A closer look at the ${-"Node.Branch"} class") {
        kotlin("""
          |data class Branch(
          |    val data: String,
          |    val left: Node = Empty(),
          |    val right: Node = Empty()
          |    ): Node() {
          |
          |  override fun with(data: String) =
          |    when {
          |      data < this.data ->
          |        Branch(this.data, left.with(data), right)
          |      data > this.data ->
          |        Branch(this.data, left, right.with(data))
          |      else -> this
          |      }
          |
          |  override fun iterator() = (*\cool{Cool stuff goes here\dots{}}*)
          |  }
          """)
        }

      slide("And the \\cool{Cool Stuff}") {
        kotlin("""
          |override fun iterator() = buildIterator {
          |  yieldAll(left)
          |  yield(data)
          |  yieldAll(right)
          |  }
          """)
        text("And a main to run it")
        kotlin("""
          |fun main(args: Array<String>) {
          |  val bst = Tree()
          |  val rnd = Random()
          |  (1..2000).forEach {
          |    bst.insert("ST${'$'}{rnd.nextInt(900) + 100}")
          |    }
          |  println(  bst.take(10).toList()  )
          |  }
          """)
        }

      }


  }