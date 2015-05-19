##Spray Routing Provides a DSL
###(Conceptually) Converts an HttpRequest Into an HttpResponse
```Scala
https://datasteward.example.edu/researcher/topics?skip=10&limit=5&sortBy=name&sortDirection=ascending
```
```Scala
  lazy val route:Route =
    staticResources ~
      logRequestResponse("route",Logging.DebugLevel) { authenticatedUser }
  }

  lazy val authenticatedUser:Route = authenticate(UserAuthenticator.basicUserAuthenticator) { user =>
    pathPrefix("qep"){qepRoute(user)} ~
      pathPrefix("researcher"){researcherRoute(user)} ~
      pathPrefix("steward"){stewardRoute(user)}
  }

  def researcherRoute(user:User):Route = authorize(UserAuthenticator.authorizeResearcher(user)) {
    pathPrefix("topics") {getUserTopics(user)} ~
      pathPrefix("queryHistory") {getUserQueryHistory(Some(user))} ~
      pathPrefix("requestTopicAccess") {requestTopicAccess(user) }
  }

  def getUserTopics(user:User):Route = get {
    matchQueryParameters(Some(user)) {queryParameters:QueryParameters =>
      val researchersTopics =
        StewardDatabase.db.selectTopicsForResearcher(queryParameters)

      complete(researchersTopics)
    }
  }

```
###[Prev](SprayRoute.md) [Next](SprayDirective.md)
