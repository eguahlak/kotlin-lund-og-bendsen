package section

import dk.kalhauge.slides.*
import dk.kalhauge.slides.elements.*

fun Show.pragmaticSection() = section("Pragmatism") {

  slide("The pragmatic approach") {
    list {
      text("Interoperable")
      text("More readable code")
      list {
        text("One-line properties")
        text("Type inference")
        text("Extension functions for most frequent use-cases")
        }
      text("Focus on robustness")
      list {
        text("Null safe types")
        text("Restrictive defaults")
        }
      }
    kotlin("""
      |fun main(args: Array<String>) {
      |  println("Hello World!")
      |  }
      """)
    }

  section("Integration to the existing world") {

    slide("Platform integration") {
      list {
        text("Compiles to JVM")
        list {
          text("Seamless integration into Java lagacy code")
          text("Utilises Java libraries")
          text("Compiles to Android with very little footprint")
          }
        text("Compiles to JavaScript")
        list {
          text("JetBrains Chrome Extension")
          }
        text("Compiles to LLVM and native code")
        }
      }

    slide("Package Managers and Libraries") {
      text("Package managers include:")
      list {
        text("Gradle")
        text("Maven")
        text("Native IDEA")
        }
      text("Libraries for much more than...")
      list {
        text("Anko - Androd")
        text("Kotlin-NoSQL - MongoDB")
        text("HamKrest - Hamcrest matchers for Kotlin")
        }
      text("... and of course any existing Java library")
      }


    }

  section("Working with properties") {

    slide("Properties conceptually") {
      list {
        text("Changes or reveals the state of an object")
        text("Normally implemented by")
        list {
          text("A backing (hidden) field")
          text("An accessor (getter)")
          text("A mutator (setter) - if the property is not read-only")
           }
        text("Accessors should not change state")
        text("Mutators should be re-entrant")
        }
      text("A new perspective:")
      list {
        text("\\textbf{From}: A private field exposed with methods get end set")
        text("\\textbf{To}: A public property backed up by a private field")
        }
      }

    slide("Properties in Java") {
      java("""
        |public class Person {
        |  private final int id;
        |  private String name;
        |
        |  public int getId() { return id; }
        |
        |  public String getName() { return name; }
        |
        |  public void setName(String value) {
        |    if (value == null) throw new RuntimeException();
        |    name = value;
        |    }
        |
        |  public Person(int id, String name) {
        |    this.id = id;
        |    setName(name);
        |    }
        |  }
        """)
      }

    slide("Java properties problems") {
      text("A lot of irritating parantheses:")
      java("kurt.setLastName(sonja.getLastName());")
      text("Null checks are runtime:")
      java("somePerson.setName(null);")
      text("... compiles, but throws an exception.")
      text("Properties and fields compiles differently.")
      }

    slide("Properties in C\\# - I") {
      csharp("""
        |public class Person {
        |  private readonly int id;
        |  private string name;
        |
        |  public int Id { get { return id; } }
        |
        |  public string Name {
        |    get { return name; }
        |    set {
        |      if (value == null) throw new Exception();
        |      name = value;
        |      }
        |    }
        |
        |  public Person(int id, string name) {
        |    this.id = id;
        |    Name = name;
        |    }
        |  }
        """)
      }

    slide("Properties in C\\# - II") {
      csharp("""
        |public class Person {
        |  public int Id { get; private set; }
        |  public string Name { get; set; }
        |
        |  public Person(int id, string name) {
        |    this.id = id;
        |    Name = name;
        |    }
        |
        |  }
        """)
      text("Shorter, but without null checks")
      csharp("kurt.LastName = sonja.LastName;")
      text("More readable, but")
      text("${-"Name"} still compiles to ${-"get\\_Name"} and ${-"set\\_Name"}")
      }

    slide("Properties in Kotlin - I") {
      kotlin("""
        |class Person(id: Int, name: String) {
        |  private val _id: Int
        |  private var _name: String
        |
        |  val id: Int get() = _id
        |
        |  var name: String
        |    get() = _name
        |    set(value) { _name = value }
        |
        |  init {
        |    _id = id
        |    _name = name
        |    }
        |
        |  }
        """)
      }

    slide("Properties in Kotlin - II") {
      kotlin("""
        |class Person(id: Int, name: String) {
        |  val id: Int
        |    get() = field
        |
        |  var name: String = ""
        |    get() = field
        |    set(value) { field = value }
        |
        |  init {
        |    this.id = id
        |    this.name = name
        |    }
        |
        |  }
        """)
      text("Using reserved word ${-"field"} for backup field")
      }

     slide("Properties in Kotlin - III") {
      kotlin("""
        |class Person(id: Int, name: String) {
        |  val id: Int
        |
        |  var name: String = ""
        |
        |  init {
        |    this.id = id
        |    this.name = name
        |    }
        |
        |  }
        """)
      text("Default implementation of ${-"get"} and ${-"set"}...")
      }

     slide("Properties in Kotlin - IV") {
      kotlin("""
        |class Person(id: Int, name: String) {
        |  val id: Int = id
        |
        |  var name: String = name
        |
        |  }
        """)
      text("No need for constructor body (${-"init"}) ...")
      }

     slide("Properties in Kotlin - V") {
      kotlin("""
        |class Person(id: Int, name: String) {
        |  val id = id
        |
        |  var name = name
        |
        |  }
        """)
      text("Types are inferred ...")
      }

     slide("Properties in Kotlin - VI") {
      kotlin("""
        |class Person(val id: Int, var name: String)
        """)
      text("Properties can be declared in the constructor")
      text("No need for ${-"\\{"} and ${-"\\}"}, when no definitions left in class body")
      text("And of course no parantheses here either")
      kotlin("""
        |kurt.firstName = sonja.firstName
        """)
      }

     slide("Properties in Kotlin - Destructuring") {
      kotlin("""data class Person(val id: Int, var name: String)""")
      text("Add ${-"data"} and you can destructure the object:")
      kotlin("""
        |val person = Person(7, "Kurt")
        |val (i, n) = person
        |println("The name of the person is ${'$'}n")
        """)
      text("And you can easily copy some or all of the object")
      kotlin("""
        |val kurt = Person(7, "Kurt")
        |val anotherKurt = kurt.copy(id = 17)
        """)
      }

    }

  section("Strong but discrete types") {

    slide("Default types cannot be null") {
      text("Add a ${-"?"} to make a type nullable")
      kotlin("""
        |val name: String = null // illegal
        |var textMaybe: String? = null // OK
        |var textForSure: String = "A text"
        |textMayBe = "A text anyway"
        |textForSure = textMayBe // illegal String != String?
        """)
      kotlin("""
        |fun foo(a: Int, b: Int?): Int {
        |  if (b == null) return 0
        |  return a*b // ok: b is casted from Int? to Int here
        |  }
        """)
      text("or more elegant")
      kotlin("""
        |fun foo(a: Int, b: Int?) =
        |  if (b == null) 0
        |  else a*b
        """)
      }

    slide("${-"open"} your classes to inherit them") {
      kotlin("""
        |open class Pet(val name: String, var age: Int)
        |
        |class Dog(val pitch: String, name: String, age: Int)
        |  : Pet(name, age)
        |
        |class Cat(var count: Int = 9, name: String, age: Int)
        |  : Pet(name, age)
        """)
      text("When a type is known, Kotlin casts automaticly")
      kotlin("""
        |fun describe(pet: Pet) =
        |  when (pet) {
        |    is Dog ->
        |       "The dog ${'$'}{pet.name} barks in ${'$'}{pet.pitch}"
        |    is Cat ->
        |      "Only ${'$'}{pet.count} lives left for ${'$'}{pet.name}"
        |    else ->
        |      "${'$'}{pet.name} might be a ${'$'}{pet.age} year rabbit"
        |    }
        """)
      }

    }

  }
