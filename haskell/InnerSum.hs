inner_sum:: [(Int, Int)] -> [Int]
inner_sum l = case l of
  [] -> []
  (a, b):t -> (a + b):(inner_sum t)

main = (print . inner_sum) [ (1, 2), (3, 5) ]

