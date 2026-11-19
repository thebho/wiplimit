package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PipelineSpec extends AnyFlatSpec with Matchers {
  "Pipeline.simulateStage" should "move items correctly when sufficient items exist" in {
    val (moved, remaining) = Pipeline.simulateStage(10, 3)
    moved should equal(3)
    remaining should equal(7)
  }

  it should "move all items when fewer items exist than the roll" in {
    val (moved, remaining) = Pipeline.simulateStage(2, 5)
    moved should equal(2)
    remaining should equal(0)
  }

  it should "move no items when stage is empty" in {
    val (moved, remaining) = Pipeline.simulateStage(0, 5)
    moved should equal(0)
    remaining should equal(0)
  }

  it should "move exactly the roll amount when items equal the roll" in {
    val (moved, remaining) = Pipeline.simulateStage(5, 5)
    moved should equal(5)
    remaining should equal(0)
  }

  it should "handle edge case with roll of 0" in {
    val (moved, remaining) = Pipeline.simulateStage(10, 0)
    moved should equal(0)
    remaining should equal(10)
  }

  it should "handle edge case with large numbers" in {
    val (moved, remaining) = Pipeline.simulateStage(1000, 250)
    moved should equal(250)
    remaining should equal(750)
  }
}
