#Scala Enables Different Coding Styles
##Custom Control Flow
###First-class Functions and Higher-Order Functions
```Scala
  def configForTest[T](key:String,value:String)(block: => T):T = {
                |    val originalValue = System.getProperty(key)
                |    System.setProperty(key,value)
                |    try{
                |      block
                |    }
                |    finally {
                |      System.setProperty(key,originalValue)
                |    }
                |  }
```
##Strongly Typed - the Most Complete Type System in Use
##Solid Platform for Domain-Specific Languages
##Style Support is Built From Language Primitives, Not Language Changes
##Extremely Clear Code
###Use the Style That Best Matches the Task at Hand
###[Prev](ScalaWhat.md) [Next](FuncOption.md)
