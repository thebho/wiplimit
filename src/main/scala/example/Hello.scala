package example

object Dice {
  private val rnd = new scala.util.Random

  def roll(modifier: Int): Int = {
    1 + rnd.nextInt(6) + modifier
  }

  def rollWithSeed(modifier: Int, seed: Long): Int = {
    val seededRnd = new scala.util.Random(seed)
    1 + seededRnd.nextInt(6) + modifier
  }
}

object Pipeline {
  def simulateStage(items: Int, roll: Int): (Int, Int) = {
    if (items >= roll) {
      (roll, items - roll)
    } else {
      (items, 0)
    }
  }
}

object Simulation {
  def runIteration(modifiers: (Int, Int, Int, Int), stages: Int): Int = {
    val (mod1, mod2, mod3, mod4) = modifiers
    var ui = 0
    var dev = 0
    var qa = 0
    var done = 0

    for (_ <- 1 to stages) {
      val roll1 = Dice.roll(mod1)
      ui += roll1

      val (move2, remaining2) = Pipeline.simulateStage(ui, Dice.roll(mod2))
      ui = remaining2
      dev += move2

      val (move3, remaining3) = Pipeline.simulateStage(dev, Dice.roll(mod3))
      dev = remaining3
      qa += move3

      val (move4, remaining4) = Pipeline.simulateStage(qa, Dice.roll(mod4))
      qa = remaining4
      done += move4
    }

    done - (ui + dev + qa)
  }

  def runIterationWithSeeds(modifiers: (Int, Int, Int, Int), stages: Int, seeds: Seq[Long]): Int = {
    val (mod1, mod2, mod3, mod4) = modifiers
    var ui = 0
    var dev = 0
    var qa = 0
    var done = 0
    var seedIndex = 0

    for (_ <- 1 to stages) {
      val roll1 = Dice.rollWithSeed(mod1, seeds(seedIndex % seeds.length))
      seedIndex += 1
      ui += roll1

      val roll2 = Dice.rollWithSeed(mod2, seeds(seedIndex % seeds.length))
      seedIndex += 1
      val (move2, remaining2) = Pipeline.simulateStage(ui, roll2)
      ui = remaining2
      dev += move2

      val roll3 = Dice.rollWithSeed(mod3, seeds(seedIndex % seeds.length))
      seedIndex += 1
      val (move3, remaining3) = Pipeline.simulateStage(dev, roll3)
      dev = remaining3
      qa += move3

      val roll4 = Dice.rollWithSeed(mod4, seeds(seedIndex % seeds.length))
      seedIndex += 1
      val (move4, remaining4) = Pipeline.simulateStage(qa, roll4)
      qa = remaining4
      done += move4
    }

    done - (ui + dev + qa)
  }
}

object Hello extends App {
  val mod1 = -1
  val mod2 = 0
  val mod3 = 1
  val mod4 = 2
  val iterations: Int = 10000
  println("WIP Limit App")

  val output = scala.collection.mutable.ListBuffer.empty[Int]

  for (_ <- 1 to iterations) {
    val result = Simulation.runIteration((mod1, mod2, mod3, mod4), 10)
    output.append(result)
  }

  println(s"${mod1} | ${mod2} | ${mod3} | ${mod4}")
  println(s"Mean:   ${output.sum / iterations}")
  println(s"Median: ${output.sortWith(_ < _)(iterations / 2)}")
  println(s"Mode:   ${output.groupBy(i => i).mapValues(_.size).maxBy(_._2)._1}")
  println(s"Max:    ${output.max}")
  println(s"Min:    ${output.min}")
}
