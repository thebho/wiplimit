package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SimulationSpec extends AnyFlatSpec with Matchers {
  "Simulation.runIteration" should "complete with valid modifiers" in {
    val result = Simulation.runIteration((-1, 0, 1, 2), 10)
    result should be >= 0
  }

  it should "return deterministic results with seeded random generator" in {
    val seeds = Seq(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L, 22L, 23L, 24L, 25L, 26L, 27L, 28L, 29L, 30L, 31L, 32L, 33L, 34L, 35L, 36L, 37L, 38L, 39L, 40L)
    val result1 = Simulation.runIterationWithSeeds((-1, 0, 1, 2), 10, seeds)
    val result2 = Simulation.runIterationWithSeeds((-1, 0, 1, 2), 10, seeds)
    result1 should equal(result2)
  }

  it should "produce reasonable statistics with multiple iterations" in {
    val results = (1 to 100).map(_ => Simulation.runIteration((-1, 0, 1, 2), 10))
    val mean = results.sum / results.length
    val max = results.max
    val min = results.min

    mean should be > 0
    max should be > min
    max should be <= 10
    min should be >= 0
  }

  it should "handle different modifier combinations" in {
    val result1 = Simulation.runIteration((0, 0, 0, 0), 5)
    val result2 = Simulation.runIteration((1, 1, 1, 1), 5)
    val result3 = Simulation.runIteration((-1, -1, -1, -1), 5)

    result1 should be >= 0
    result2 should be >= 0
    result3 should be >= 0
  }

  it should "complete more items with positive modifiers" in {
    val results1 = (1 to 50).map(_ => Simulation.runIteration((-1, -1, -1, -1), 10))
    val results2 = (1 to 50).map(_ => Simulation.runIteration((1, 1, 1, 1), 10))

    val mean1 = results1.sum / results1.length
    val mean2 = results2.sum / results2.length

    mean2 should be > mean1
  }

  it should "handle single stage iteration" in {
    val result = Simulation.runIteration((0, 0, 0, 0), 1)
    result should be >= 0
  }

  it should "handle many stages" in {
    val result = Simulation.runIteration((0, 0, 0, 0), 100)
    result should be >= 0
  }
}
