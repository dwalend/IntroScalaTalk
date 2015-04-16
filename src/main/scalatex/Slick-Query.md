# [Slick Lifted Query](#### [Compact, clean code](https://open.med.harvard.edu/vvc/viewvc.cgi/shrine/trunk/code/steward/src/main/scala/net/shrine/steward/db/StewardDatabase.scala?view=markup)

## Compose Queries based on QueryParameters' fields

```Scala
  private def topicCountQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
    val allTopics:Query[TopicTable, TopicTable#TableElementType, Seq] = allTopicQuery
    val userFilter = queryParameters.userIdOption.fold(allTopics)(userId => allTopics.filter(_.userId === userId))
    val stateFilter = queryParameters.stateOption.fold(userFilter)(state => userFilter.filter(_.state === state.name))

    stateFilter
  }

  private def topicSelectQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
    val countFilter = topicCountQuery(queryParameters)
    val skipFilter = queryParameters.skipOption.fold(countFilter)(skip => countFilter.drop(skip))
    val limitFilter = queryParameters.limitOption.fold(skipFilter)(limit => skipFilter.take(limit))

    //todo some way to have sortBy(table.column.get(..).(asc or desc)
    val orderByQuery = queryParameters.sortByOption.fold(limitFilter)(
      columnName => limitFilter.sortBy(x => queryParameters.sortOrder.orderForColumn(columnName match {
        case "id" => x.id
        case "userId" => x.userId
        case "name" => x.name
        case "description" => x.description
        case "state" => x.state
        case "date" => x.date
      })))

    orderByQuery
  }

  def selectTopicsForResearcher(parameters:QueryParameters):ResearchersTopics = {
    require(parameters.userIdOption.isDefined,"A researcher's parameters must supply a user id")
    database.withSession { implicit session: Session =>
      ResearchersTopics(parameters.userIdOption.get,
                        topicCountQuery(parameters).length.run,
                        parameters.skipOption.getOrElse(0),
                        topicSelectQuery(parameters).list)
    }
  }

```
[Prev](Slick-Table.md) [Next](Spray.md)
