#Functional Programming with Option[T]
##Handle Empty Fields With Container
###Replace null checks, Cure for Sir Tony Hoare's 
###["billion-dollar mistake ... the invention of the null reference in 1965"](http://en.wikipedia.org/wiki/Tony_Hoare#Apologies_and_retractions)

##Option[T] is an abstract class with two subclasses
###Some - When a Value is Present
###None - When No Value is Present

###Takes a type parameter T

```Scala

val someString:Option[String] = Some("some string")
val noString:Option[String] = None
              
```
###[Prev](CodStyle.md) [Next](FuncFold.md)
