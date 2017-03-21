sq :: Int -> Int
sq i = i * i

sum_square :: [Int] -> Int
sum_square l = (foldr1 (+) (map sq l))

main :: IO ()
main =
     do
        print (sum_square [1 .. 5])
