Outline for a half-hour talk at HMS Catalyst

# Scala
## What?
### Scalable programming language http://www.scala-lang.org/what-is-scala.html
#### Scales in scope -- scripting, javascript, desktop, servers, distributed
#### Scales for differences in experience 
##### Has deep academic roots, especially Category Theory
#### Scales for different coding styles
##### First-class functions and higher-order functions allows for custom control flow
##### Style support is built primarily from language primitives, not language changes
##### Strong support for Procedural, OO, and Functional styles
#### Scales through time?  
## Pros
### Gentle learning curve from Java -- Use scala as a better Java
#### Out Javas Java in all but community and uptake
#### Case class example
### Thinking about a problem results in readable, concise code
#### Dijkstra's algorithm with Semirings Java vs Scala
#### Immutable structures and functional programming help make code understandable
#### Even makes DSLs practical (Spray routes and URLs)
#### Let the pattern book collect dust
### Best match for the next 15 years of hardware dev
#### Immutable, functional programming lets you use all the cores
#### Distributed, large scale. Spark sped up Hadoop by 100X. Akka and Storm are both Scala projects.
#### Can be compiled to GPU and to Javascript
#### Spray rewrite of underlying code to use Akka streams will not change existing spray routes
### Fun
## Cons
### Very tall learning curve
#### And no barrier between the kiddie pool and the diving well
### Tiny tight-knit community
#### Academic instead of pragmatic biases
### Uptake in Islands
#### Locally that means Kendall Square, Harbor Innovation District, Waltham reservoirs, and HMS
### Extremely poor theatre for hourly consulting because you read and think instead of hammer the keyboard

# Slick
## A database library that takes advantage of case classes, tuples, and functional programming
## Plain SQL API available, too
## 3.0 supports nonblocking databases
## Compact, clean code
### Show table definitions
## Composable
### Show case classes


# Spray
## Spray scale-up lets a web server potentially use all IO and all CPU.
### Asyncrhonous, actor-based, fast, lightweight, modular and testable
## Next version will be Akka-Http, based on Akka Streams
## Show URL DSL
## Show Route DSL
## Show custom directive and case class

[Start](Start.md)