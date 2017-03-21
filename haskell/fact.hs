fact1:: Int -> Int
fact1 n = if (n == 1) then 1
          else n * fact1(n - 1)

fact2:: Int -> Int
fact2 n
      | (n == 1) = 1
      | otherwise = n * fact2(n - 1)

fact3:: Int -> Int
fact3 1 = 1
fact3 n = n * fact3(n - 1)

fact4:: Int -> Int
fact4 n = case (n) of
          1 -> 1
          _ -> n * fact4(n - 1)

main =
     do
        print "fact1:"
        print (fact1 5)
        print (fact1 3)

        print "fact2:"
        print (fact2 5)
        print (fact2 3)

        print "fact3:"
        print (fact3 5)
        print (fact3 3)

        print "fact4:"
        print (fact4 5)
        print (fact4 3)
