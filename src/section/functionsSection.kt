package section

import dk.kalhauge.numbers.over
import dk.kalhauge.slides.*
import dk.kalhauge.slides.elements.*

fun Show.functionsSection() =

  section("Functions as first class members") {

    slide("Functions as first class members") {
      list {
        text("Procedural programming")
        text("Object oriented programming")
        text("Functional programming")
        text("Domain specific languages")
        }
      }

    slide("Procedural programming support") {
      list {
        text("Functions defined at package level")
        text("Properties defined at package level")
        text("Data classes")
        }
      }

    slide("OOP support") {
      list {
        text("Enhanced readability")
        list {
          text("Properties taken seriously")
          text("Operator overloads")
          text("Extension functions (and properties)")
          text("Smart casting")
          text("No semicolons (almost)")
          }
        text("Robustness")
        list {
          text("Default is most restrictive")
          text("Functions are closed (not virtual) until opened")
          text("Values are always ${-"final"}")
          text("Null values are normally not allowed")
          text("Classes are closed (final) until opened")
          }
        }
      }

    slide("Functional programming support") {
      list {
        text("Immutable objects are easily implemented with data classes")
        text("Union types can be implemented with sealed classes")
        text("Conditional expressions supports writing pure functions")
        text("Tail recursive awareness supports efficient recursivity")
        text("Streams and collections")
        text("Lambdas and functions as first class members")
        }
      }

    slide("Extension functions and a little more") {
      text("A class for implementing rational numbers")
      kotlin("""
        |data class Rational
        |    private constructor(val n: Long, val d: Long) {
        |
        |  companion object {
        |    fun normalised(n: Long, d: Long) =
        |      with (n gcd d) { Rational(n/this, d/this) }
        |    }
        |
        |  override fun toString() = "${'$'}n/${'$'}d"
        |
        |  operator fun times(other: Rational) =
        |      normalised(this.n*other.n, this.d*other.d)
        | (* \dots *)
        |
        |  }
        """)
      }

    slide("Extension functions and a little more") {
      kotlin("""
        |data class Rational
        |    private constructor(val n: Long, val d: Long) {
        | (* \dots *)
        |
        |  operator fun div(other: Rational) =
        |      normalised(this.n*other.d, this.d*other.n)
        |
        |  operator fun unaryMinus() = normalised(-n, d)
        |
        |  operator fun plus(other: Rational) =
        |      normalised(
        |          this.n*other.d + this.d*other.n,
        |          this.d*other.d
        |          )
        |
        |  operator fun minus(other: Rational) = this + -other
        |  }
        """)
      }

    slide("Extension functions and a little more") {
      text("And some helper functions")
      kotlin("""
        |infix fun Long.over(other: Long) =
        |  Rational.normalised(this, other)
        |
        |infix fun Int.over(other: Long) =
        |  this.toLong() over other
        |
        |infix fun Long.gcd(other: Long): Long =
        |  if (other == 0L) this
        |  else other gcd this%other
        |
        |infix fun Int.gcd(other: Long) =
        |  this.toLong() gcd other
        """)
      }

    slide("Extension functions and a little more") {
      text("And how to use the class")
      kotlin("""
        |fun main(args: Array<String>) {
        |  println(1440 gcd 30)
        |  println("144/30 = ${144 over 30}")
        |  println("-22/7 + 1/7 = ${-(22 over 7) + (1 over 7)}")
        |  println("144/-30 = ${144 over -30}")
        |  }
        """)
      text("Outputs:")
      output("""
        |30
        |144/30 = 24/5
        |-22/7 + 1/7 = -3/1
        |-144/30 = -24/5
        """)
      }

    }

