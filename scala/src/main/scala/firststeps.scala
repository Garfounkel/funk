object Tp{
  case class Majeure(name: String, surname: String="test")

  def tupleToCase (tuple: (String, String)) : Majeure = Majeure(tuple._1, tuple._2)

  def even (x: Int): Boolean = x % 2 == 0

  def applyOnFirstTen (pred : Int => Boolean): Unit = {
    def loop (i: Int=0, n: Int=10): Unit = (even(i), n > 0) match {
      case (_, false) => ()
      case (false, true) => loop(i + 1, n)
      case _ => println(i); loop(i + 1, n - 1)
    }
    loop()
  }

  def list10First(pred : Int => Boolean): List[Int] = {
    def loop (i: Int=0, n: Int=10): List[Int] = (even(i), n > 0) match {
      case (_, false) => Nil
      case (false, true) => loop(i + 1, n)
      case _ => i::loop(i + 1, n - 1)
    }
    loop()
  }
}

object Main extends App {
  val scia = Tp.tupleToCase(("SCIA", "ML"))
  println(scia)

  Tp.applyOnFirstTen(Tp.even)
  println(Tp.list10First(Tp.even))

  println(List(Tp.Majeure("SCIA"), Tp.Majeure("GITM")).map(x => x.name))

  println(Stream.from(0).filter(Tp.even).take(10).toList)
}
