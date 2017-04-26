fibo:: Int -> Int
fibo 0 = 0
fibo 1 = 1
fibo n = fibo (n - 1) + fibo (n - 2)

printfibo n = (print . fibo) n

--

fiboPaternMaching:: Int -> Int
fiboPaternMaching n = case n of
  0 -> 0
  1 -> 1
  n -> fiboPaternMaching (n - 1) + fiboPaternMaching (n - 2)

printfiboPM n = print . fiboPaternMaching $ n

main =
  do
    printfibo 8
    printfibo 5
    print "--"
    printfiboPM 8
    printfiboPM 5
