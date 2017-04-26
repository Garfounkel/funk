facto_rec n goal acc = case n of
 n -> if (n >= goal + 1)
      then acc
      else facto_rec (n + 1) goal (n * acc)

facto n = facto_rec 1 n 1


in_fact s = do putStrLn(s)
               num <- getLine
               let n = read num in
                 if n < 0 then
                   putStrLn "exit"
                 else
                   in_fact . show . facto $ n

main = in_fact "please enter a positive number"
