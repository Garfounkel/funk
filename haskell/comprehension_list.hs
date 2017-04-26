threes_not_fives = [ x | x <- [0..], x `mod` 3 == 0, x `mod` 5 /= 0 ]
num_and_square = [ (i, j) | i <- [0..], i == i, j <- i * i ]
--one_neg_one = [ x | x <- [0..], x - (x - 1) ]


print_10 = print . take 10

main :: IO ()
main =
  do
    print_10 threes_not_fives
    print_10 num_and_square
