##A Spray Directive
###Tried the parameters() Directive to Create New QueryParameters
```Scala
    def getUserTopics(userId:UserId):Route = get {
      parameters(('userName.?,'state.?,'skip.as[Int].?,'limit.as[Int].?,'sortBy.as[String].?,'sortDirection.as[String].?).as[QueryParameters]) {
        queryParameters =>
          val researchersTopics = StewardDatabase.db.selectTopicsForResearcher(queryParameters)
          complete(researchersTopics)
      }
    }

```
###But It Didn't Quite Fit
###User Name Can Come In Through Authentication (Researchers) or Prefix (Steward)
###TopicState is an Enumeration, Needs Error Checking
###Didn't Want to Cut-Paste That Much Code
```Scala
  import shapeless.{:: => shapelessConcat}
  case class matchQueryParameters(researcherId:Option[UserId] = None) extends Directive[QueryParameters shapelessConcat HNil] {
    import spray.routing.directives.ParameterDirectives._
    import spray.routing.directives.RouteDirectives.complete
    import spray.routing.directives.RespondWithDirectives.respondWithStatus

    override def happly(f: (shapelessConcat[QueryParameters, HNil]) => Route): Route = {
      //todo name safety for column names and sort direction
      parameters('state.?,'skip.as[Int].?,'limit.as[Int].?,'sortBy.as[String].?,'sortDirection.as[String].?) { (stateStringOption,skipOption,limitOption,sortByOption,sortOption) =>

        val stateTry = TopicState.stateForStringOption(stateStringOption)
        stateTry match {
          case Success(stateOption) => {
              val qp = QueryParameters(researcherId,
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
###[Prev](SprayRouteDsl.md) [Next](SprayNoDirective.md)
