class Person(id: Int, name: String) {
  val id = id

  var name = name

  }

fun foo1(a: Int, b: Int?): Int {
  if (b == null) return 0
  return a*b
  }

fun foo2(a: Int, b: Int?) =
  if (b == null) 0
  else a*b

