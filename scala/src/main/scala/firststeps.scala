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
  def last(list: List[Any]): Any = list match {
    case Nil => ???
    case e :: Nil => e
    case _ :: l => last(l)
  }

  def nth(n: Int, list: List[Any]): Any= (n, list) match {
    case (0, e :: _) => e
    case (x, _ :: l) => nth(x - 1, l)
    case (_, Nil) => throw new IndexOutOfBoundsException
  }

  def reverse[A](list: List[A]): List[A] = list match {
    case Nil => Nil
    case e :: l => reverse(l) ::: List(e)
  }

  def compress(list: List[Any]): List[Any] = list match {
    case Nil => Nil
    case a :: b :: l if a == b => compress(a::l)
    case a :: l => a :: compress(l)
  }

  def compress_foldLeft1(list: List[Any]): List[Any] = {
    list.tail.foldLeft(List(list.head)) { (acc, e) => if (acc.head != e) e :: acc else acc}.reverse
  }

  def encode(list: List[Any]): List[(Int, Any)] = list.foldLeft(List[(Int, Any)]()) {
    case (Nil, elt) => List((1, elt))
    case ((n, e)::tail, elt) if e == elt => (n + 1, elt) :: tail
    case (acc, elt) => (1, elt) :: acc
  }.reverse

  def decode(list: List[(Int, Any)]): List[Any] = list.flatMap(e => List.fill(e._1)(e._2))

  def decode2(list: List[(Int, Any)]): List[Any] = list.flatMap(e => (1 to e._1).map(_ => e._2))

  def test(): Unit = {
    println(last(List(1, 1, 2, 3, 5, 8)))
    println(last(List(1, 2, 3)))
    println(List(1, 1, 2, 3, 7, 2, 8).takeRight(1).head)
    println(List(1, 1, 2, 3, 7, 2, 8).last)

    println(nth(2, List(1, 1, 2, 3, 5, 8)))
    println(List(1, 2, 3)(2))

    println(reverse(List(1, 1, 2, 3, 5, 8)))
    println(List(1, 1, 2, 3, 5, 8).reverse)

    println(compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
    val l = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    println(compress_foldLeft1(l))

    println(encode(l))

    println(decode(encode(l)))
    println(decode2(encode(l)))
  }
}

object Main extends App {
  //Tp1.test()

  Tp2.test()
}
