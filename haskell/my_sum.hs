my_sum :: [Int] -> Int
my_sum [] = 0
my_sum (x:xs) = x + my_sum(xs)

klist :: [a] -> Int -> a
klist (x:xs) 0 -> x
klist (x:xs) n -> klist xs (n - 1)

main =
     do
       print (my_sum [1, 2, 5, 3, 4])
       print $ my_sum $ [1, 2, 5, 3, 4]
       (print . my_sum) [1, 2, 5, 3, 4]
