##A Spray Route is a Function That Takes a RequestContext
###A RequestConext is an HttpRequest Plus Odd Bits
###Route Does Not Return Anything, But Can Send an HttpResponse to an Akka Actor
###Routes Are Made From Directives
```Scala
abstract class Directive[L <: shapeless.HList] { self =>
    def happly(f: L => Route): Route
```
###A Shapeless HList is ...
###... Try the Route DSL and See What Happens
###[Prev](SprayIntro.md) [Next](SprayRouteDsl.md)
