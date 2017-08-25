package dk.kalhauge.numbers

data class Rational private constructor(val n: Long, val d: Long) {

  companion object {
    fun normalised(n: Long, d: Long) =
      with (n gcd d) { Rational(n/this, d/this) }
    }

  override fun toString() = "$n/$d"

  operator fun times(other: Rational) =
      normalised(this.n*other.n, this.d*other.d)

  operator fun div(other: Rational) =
      normalised(this.n*other.d, this.d*other.n)

  operator fun unaryMinus() = normalised(-n, d)

  operator fun plus(other: Rational) =
      normalised(this.n*other.d + this.d*other.n, this.d*other.d)

  operator fun minus(other: Rational) = this + -other

  }

infix fun Long.over(other: Long) = Rational.normalised(this, other)

infix  fun Int.over(other: Long) = this.toLong() over other

infix fun Long.gcd(other: Long): Long =
  if (other == 0L) this
  else other gcd this%other

infix fun Int.gcd(other: Long) = this.toLong() gcd other


fun main(args: Array<String>) {
  println(1440 gcd 30)
  println("144/30 = ${144 over 30}")
  println("-22/7 + 1/7 = ${-(22 over 7) + (1 over 7)}")
  println("144/-30 = ${144 over -30}")
  }