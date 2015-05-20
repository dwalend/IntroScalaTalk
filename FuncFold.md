##Functional Programming with Option's fold()()

##Option[T]'s fold()() method takes two functions
```Scala
def fold[Out](ifEmpty: => Out)(f: (T) => Out): Out
```
###Some[T].fold()() evaluates the right function with the value as an argument
###None[T].fold()() evaluates the left (no-argument) function
```Scala

val yourOption:Option[String] = ...
val stringToPrint = yourOption.fold(".")(string => s"!$string!")

```
###[Read more about fold()()](https://coderwall.com/p/4l73-a/scala-fold-foldleft-and-foldright)
###[Prev](FuncOption.md) [Next](Pros.md)
