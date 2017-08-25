import java.util.*
import kotlin.coroutines.experimental.buildSequence

fun main(args: Array<String>) {
  val bst = BST()
  val rnd = Random()
  val N = 200;
  for (n in 1..N)
      bst.insert("ST${rnd.nextInt(900) + 100}")
  println(bst.asSequence().take(10).toList())
  }

class BST {
    class Node(var elem: String, var left: Node? = null, var right: Node? = null)

    private var root: Node? = null;

    fun insert(elem: String) {
      root = insert(elem, root)
      }

    private fun insert(elem: String, node: Node? ): Node{
        if (node == null) return Node(elem, null, null)
        val comp = elem.compareTo(node.elem, true)
        when {
            comp == 0  -> node.elem = elem
            comp < 0 -> node.left = insert(elem, node.left)
            comp > 0 -> node.right = insert(elem, node.right)
        }
        return node;
    }

//    fun with(element: String, node: Node?) =
//      when {
//        node == null -> Node(element)
//        element < node.elem ->
//        }

    fun asSequence(): Sequence<String> {
        return asSequence(root)
    }
    private fun asSequence(n: Node?) : Sequence<String>{
        return buildSequence{
            if (n == null ) return@buildSequence
            yieldAll(asSequence(n.left))
            yield(n.elem)
            yieldAll(asSequence(n.right))
        }
    }

}