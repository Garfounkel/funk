object Tp1{
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

  def test(): Unit = {
    val scia = tupleToCase(("SCIA", "ML"))
    println(scia)

    applyOnFirstTen(even)
    println(list10First(even))

    println(List(Majeure("SCIA"), Majeure("GITM")).map(x => x.name))

    println(Stream.from(0).filter(even).take(10).toList)
  }
}

object Tp2 {
  def last(list: List[Int]): Int = list match {
    case e :: Nil => e
    case _ :: l => last(l)
  }

  def nth(n: Int, list: List[Int]): Int = (n, list) match {
    case (0, e :: _) => e
    case (x, _ :: l) => nth(x - 1, l)
    case (_, Nil) => -1
  }

  def reverse(list: List[Int]): List[Int] = list match {
    case Nil => Nil
    case e :: l => reverse(l) ::: List(e)
  }

  def compress(list: List[Symbol]): List[Symbol] = list match {
    case Nil => Nil
    case a :: b :: l if a == b => compress(a::l)
    case a :: l => a :: compress(l)
  }

  def test(): Unit = {
    println(last(List(1, 1, 2, 3, 5, 8)))
    println(last(List(1, 2, 3)))

    println(nth(2, List(1, 1, 2, 3, 5, 8)))
    println(nth(10, List(1, 2, 3)))

    println(reverse(List(1, 1, 2, 3, 5, 8)))

    println(compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  }
}

object Main extends App {
  //Tp1.test()

  Tp2.test()
}
