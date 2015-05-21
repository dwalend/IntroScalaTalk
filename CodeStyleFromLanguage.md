#Scala Enables Different Coding Styles
##Strongly Typed - the Most Complete Type System in Use
##Custom Control Flow
###First-class Functions and Higher-Order Functions
```Scala
  def configForTest[T](key:String,value:String)(block: => T):T = {
    val originalValue = System.getProperty(key)
    System.setProperty(key,value)
    try { block }
    finally { System.setProperty(key,originalValue) }
  }

  def testAutoApprove() {
    configForTest("shrine.steward.mode","AutoApprove"){
      ... //test code
    }
  }

  
```
###[Prev](CodStyle.md) [Next](FuncOption.md)
