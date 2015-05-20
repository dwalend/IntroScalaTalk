##Better With a Higher Order Function
```Scala
  def matchQueryParameters(userName: Option[UserName])(parameterRoute:QueryParameters => Route): Route =  {

    parameters('state.?,'skip.as[Int].?,'limit.as[Int].?,'sortBy.as[String].?,'sortDirection.as[String].?,'minDate.as[Date].?,'maxDate.as[Date].?) { (stateStringOption,skipOption,limitOption,sortByOption,sortOption,minDate,maxDate) =>

      val stateTry = TopicState.stateForStringOption(stateStringOption)
      stateTry match {
        case Success(stateOption) =>
          val qp = QueryParameters(userName,
            stateOption,
            skipOption,
            limitOption,
            sortByOption,
            SortOrder.sortOrderForStringOption(sortOption),
            minDate,
            maxDate
          )

          parameterRoute(qp)

        case Failure(ex) => badStateRoute(stateStringOption)
      }
    }
  }

```
```Scala
  def getUserTopics(researcherId:UserName):Route = get {
    //lookup topics for this user in the db
   matchQueryParameters(Some(researcherId)){queryParameters:QueryParameters =>
      val researchersTopics = StewardDatabase.db.selectTopicsForResearcher(queryParameters)
      complete(researchersTopics)
    }
  }

```
###[Prev](SprayDirective.md) [Next](WrapUp.md)
