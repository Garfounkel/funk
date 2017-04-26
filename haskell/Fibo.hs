fibo n =
  if n == 0 || n == 1 then
    n
  else
    fibo (n - 1) + fibo (n - 2)

main = print (fibo 5)
