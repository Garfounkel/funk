filter_list:: (a -> Bool) -> [a] -> [a]
filter_list _ [] = []
filter_list f (x:xs) = if (f x)
                          then (x:(filter_list f xs))
                          else (filter_list f xs)

main :: IO ()
main =
     do
        print (filter_list (\x -> x < 4) [0 .. 9])
