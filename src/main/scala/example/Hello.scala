package example


object Hello extends App {
  val rnd = new scala.util.Random
  val roll = () => 1 + rnd.nextInt((6 - 1) + 1)

  val mod1 = -1
  val mod2 = 0
  val mod3 = 1
  val mod4 = 2
  val iterations: Int = 10000
  println("WIP Limit App")

  val output = scala.collection.mutable.ListBuffer.empty[Int]

  for (a <- 1 to 10000) {
    var ui = 0
    var dev = 0
    var qa = 0
    var done = 0

    for( a <- 1 to 10){


      var move = 0
      val roll1 = roll() + mod1
      ui += roll1

      val roll2 = roll() + mod2
      if (ui >= roll2) {
        move = roll2
        ui -= roll2
      } else {
        move = ui
        ui = 0
      }
      dev += move
      move = 0

      val roll3 = roll() + mod3
      if (dev >= roll3) {
        move = roll3
        dev -= roll3
      } else {
        move = dev
        dev = 0
      }
      qa += move
      move = 0

      val roll4 = roll() + mod4
      if (qa >= roll4) {
        move = roll4
        qa -= roll4
      } else {
        move = qa
        qa = 0
      }
      done += move
      move = 0

      // println(roll1,roll2,roll3,roll4)
      // println(ui, dev, qa, done)
    }
    output.append(done - (ui + dev + qa))

  }
  println(s"${mod1} | ${mod2} | ${mod3} | ${mod4}")
  println(s"Mean:   ${output.sum/10000}")
  println(s"Median: ${output.sortWith(_ < _)(5000)}")
  println(s"Mode:   ${output.groupBy(i => i).mapValues(_.size).maxBy(_._2)._1}")
  println(s"Max:    ${output.max}")
  println(s"Min:    ${output.min}")
}
