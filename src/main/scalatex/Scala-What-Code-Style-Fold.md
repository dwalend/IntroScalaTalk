# FP with Option.fold()()

## Option's fold()() method takes two functions

```Scala
def fold[Out](ifEmpty: ⇒ Out)(f: (T) ⇒ Out): Out
```

### Some[T].fold()() evaluates the right function with the value as an argument
### None[T].fold()() evaluates the left (no-argument) function

```Scala
val yourOption:Option[String] = ...
val stringToPrint = yourOption.fold(".")(string => s"!$string!")
```


## Show in the REPL
## [Read more about fold()()](https://coderwall.com/p/4l73-a/scala-fold-foldleft-and-foldright)

[Prev](Scala-What-Option.md) [Next](Scala-Pros1.md)
