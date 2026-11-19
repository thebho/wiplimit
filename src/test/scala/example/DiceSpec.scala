package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DiceSpec extends AnyFlatSpec with Matchers {
  "Dice.roll" should "return values between 1 and 6 with modifier 0" in {
    val results = (1 to 100).map(_ => Dice.roll(0))
    results.foreach { r =>
      r should be >= 1
      r should be <= 6
    }
  }

  it should "return values between 0 and 5 with modifier -1" in {
    val results = (1 to 100).map(_ => Dice.roll(-1))
    results.foreach { r =>
      r should be >= 0
      r should be <= 5
    }
  }

  it should "return values between 3 and 8 with modifier 2" in {
    val results = (1 to 100).map(_ => Dice.roll(2))
    results.foreach { r =>
      r should be >= 3
      r should be <= 8
    }
  }

  "Dice.rollWithSeed" should "return deterministic values with the same seed" in {
    val roll1 = Dice.rollWithSeed(0, 42L)
    val roll2 = Dice.rollWithSeed(0, 42L)
    roll1 should equal(roll2)
  }

  it should "return different values with different seeds" in {
    val roll1 = Dice.rollWithSeed(0, 42L)
    val roll2 = Dice.rollWithSeed(0, 43L)
    // While not guaranteed, it's extremely unlikely they're equal
    roll1 should not equal roll2
  }
}
