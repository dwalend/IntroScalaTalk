# Spray's Route DSL

### Think of a Route as a function that takes in and produces (HttpRequest,HttpResponse)
### Think of a Directive as a function that takes in some parameters and produces a Route
### [The Data Steward's Route](https://open.med.harvard.edu/vvc/viewvc.cgi/shrine/trunk/code/steward/src/main/scala/net/shrine/steward/StewardService.scala?view=markup)

```Scala
...
  lazy val route:Route = logRequestResponse("route",Logging.DebugLevel) {
    staticResources ~ authenticatedUser
  }

  lazy val authenticatedUser:Route = authenticate(UserAuthenticator.basicUserAuthenticator) { user =>
    pathPrefix("qep"){qepRoute(user)} ~
      pathPrefix("researcher"){researcherRoute(user)} ~
      pathPrefix("steward"){stewardRoute(user)}
  }

  def researcherRoute(user:User):Route = authorize(UserAuthenticator.authorizeResearcher(user)) {
    pathPrefix("topics") {getUserTopics(user.username)} ~
      pathPrefix("queryHistory") {getUserQueryHistory(Some(user.username))} ~
      pathPrefix("requestTopicAccess") {requestTopicAccess(user) }
  }

  def getUserTopics(userId:UserId):Route = get {
    matchQueryParameters(Some(userId)) {queryParameters:QueryParameters =>
      val researchersTopics = blocking {
        StewardDatabase.db.selectTopicsForResearcher(queryParameters)
      }
      complete(researchersTopics)
    }
  }
...
```

[Prev](Spray-Url.md) [Next](Spray-Directive.md)