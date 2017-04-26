data Operator = Plus
              | Minus
              | Times

data Node = Value Integer
          | Operation (Node, Operator, Node)

value :: Node -> Integer
value (Value i) = i
value (Operation (l, Plus, r)) = value l + value r
value (Operation (l, Minus, r)) = value l - value r
value (Operation (l, Times, r)) = value l * value r

pretty_printer :: Node -> String
pretty_printer n = case n of
  (Value i) -> show i
  (Operation (l, Plus, r))  -> pretty_printer l ++ " + " ++ pretty_printer r
  (Operation (l, Minus, r)) -> pretty_printer l ++ " - " ++ pretty_printer r
  (Operation (l, Times, r)) -> pretty_printer l ++ " * " ++ pretty_printer r

pretty_printer_visit :: Node -> String
pretty_printer_visit n = pretty_printer n ++ " = " ++ (show . value) n

ast:: Node
ast = (Operation ((Value 1), (Plus), Operation ((Value 2), (Times), (Value 3))))

main =
  do
    print (value ast)
    print (pretty_printer_visit ast)
