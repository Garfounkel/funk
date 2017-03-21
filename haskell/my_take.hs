my_take :: Int -> [Int] -> [Int]
my_take a l
  | (a == 0) = []
  | (l == []) = []
  | otherwise = (head l):(my_take (a - 1) (tail l))

main :: IO ()
main =
     do
        print (my_take 3 [1, 2, 3, 4, 5])
        print (my_take 3 [])
