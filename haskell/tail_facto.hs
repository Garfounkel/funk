facto_rec n goal acc = case n of
 n -> if (n >= goal + 1)
      then acc
      else facto_rec (n + 1) goal (n * acc)

facto n = facto_rec 1 n 1

main =
  do
    print (facto 0)
    print (facto 1)
    print (facto 2)
    print (facto 3)
    print (facto 4)
    print (facto 5)
    print (facto 6)
    print (facto 7)
    print (facto 8)
    print (facto 9)
    print (facto 10)
    print (facto 11)
    print (facto 12)
    print (facto 13)
    print (facto 14)
    print (facto 15)
    print (facto 16)
    print (facto 17)
    print (facto 18)
    print (facto 19)
    print (facto 20)
