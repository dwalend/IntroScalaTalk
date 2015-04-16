# A Spray Directive

### I'd originally made the QueryParameters using parameter()'s case class feature

```Scala
...
  def getUserTopics(userId:UserId):Route = get {
     parameters(('userName.?,'state.?,'skip.as[Int].?,'limit.as[Int].?,'sortBy.as[String].?,'sortDirection.as[String].?).as[QueryParameters]) {queryParameters =>
      val researchersTopics = blocking {
        StewardDatabase.db.selectTopicsForResearcher(queryParameters)
      }
      complete(researchersTopics)
    }
  }
...
```

### But it wasn't quite enough
#### The user names comes in via authentication (for a researcher) or a prefix (for the steward)
#### TopicState is a custom class, needs some error checking
#### Wanted to reuse the parameters => QueryParameters in many places in the route

```Scala
import shapeless.{:: => shapelessConcat}
case class matchQueryParameters(userId:Option[UserId] = None) extends Directive[QueryParameters shapelessConcat HNil] {
  import spray.routing.directives.ParameterDirectives._
  import spray.routing.directives.RouteDirectives.complete
  import spray.routing.directives.RespondWithDirectives.respondWithStatus

  override def happly(f: (shapelessConcat[QueryParameters, HNil]) => Route): Route = {
    //todo name safety for column names and sort direction
    parameters('state.?,'skip.as[Int].?,'limit.as[Int].?,'sortBy.as[String].?,'sortDirection.as[String].?) { (stateStringOption,skipOption,limitOption,sortByOption,sortOption) =>

      val stateTry = TopicState.stateForStringOption(stateStringOption)
      stateTry match {
        case Success(stateOption) => {
            val qp = QueryParameters(userId,
                                      stateOption,
                                      skipOption,
                                      limitOption,
                                      sortByOption,
                                      SortOrder.sortOrderForStringOption(sortOption))
            f(shapelessConcat(qp,HNil))
        }
        case Failure(ex) => badStateRoute(stateStringOption)
      }
    }
  }

  def badStateRoute(stateStringOption:Option[String]):Route = {
    respondWithStatus(StatusCodes.UnprocessableEntity) {
      complete(s"State ${stateStringOption.getOrElse(s"$stateStringOption (stateStringOption should never be None at this point)")} unknown. Please specify one of ${TopicState.namesToStates.keySet}")
    }
  }
}
```
### No barrier between the kiddie pool and the diving well

[Prev](Spray-Route.md) [Next](WrapUp.md)