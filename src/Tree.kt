import java.util.*
import kotlin.coroutines.experimental.buildIterator

class Tree(var root: Node = Node.Empty()) : Sequence<String> {

  override fun iterator() = root.iterator()

  fun insert(data: String) { root = root with data }

  }

sealed class Node : Sequence<String> {

  abstract infix fun with(data: String): Node

  data class Branch(val data: String, val left: Node, val right: Node): Node() {
    override fun with(data: String) =
        when {
          data < this.data -> Branch(this.data, left with data, right)
          data > this.data -> Branch(this.data, left, right with data)
          else -> this
        }

    override fun iterator() = buildIterator {
      yieldAll(left)
      yield(data)
      yieldAll(right)
      }

    }

  class Empty: Node() {
    override fun with(data: String) = Branch(data, Empty(), Empty())
    override fun iterator() = emptySequence<String>().iterator()
    }

  }

fun main(args: Array<String>) {
  val bst = Tree()
  val rnd = Random()
  (1..2000).forEach {
    bst.insert("ST${rnd.nextInt(900) + 100}")
    }

  println(  bst.take(10).toList()  )

  }

