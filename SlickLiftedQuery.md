#Slick Composible Lifted Query
```Scala
  private def topicCountQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
    val allTopics:Query[TopicTable, TopicTable#TableElementType, Seq] = allTopicQuery
    val researcherFilter = queryParameters.researcherIdOption.fold(allTopics)(userId => allTopics.filter(_.createdBy === userId))
    val stateFilter = queryParameters.stateOption.fold(researcherFilter)(state => researcherFilter.filter(_.state === state.name))
    val minDateFilter = queryParameters.minDate.fold(stateFilter)(minDate => stateFilter.filter(_.changeDate >= minDate))
    val maxDateFilter = queryParameters.maxDate.fold(minDateFilter)(maxDate => minDateFilter.filter(_.changeDate <= maxDate))

    maxDateFilter
  }

  private def topicSelectQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
    val countFilter = topicCountQuery(queryParameters)

    //todo is there some way to get a map from column names to columns that I don't have to update?
    val orderByQuery = queryParameters.sortByOption.fold(countFilter)(
      columnName => countFilter.sortBy(x => queryParameters.sortOrder.orderForColumn(columnName match {
        case "id" => x.id
        case "name" => x.name
        case "description" => x.description
        case "createdBy" => x.createdBy
        case "createDate" => x.createDate
        case "state" => x.state
        case "changedBy" => x.changedBy
        case "changeDate" => x.changeDate
      })))

    val skipFilter = queryParameters.skipOption.fold(orderByQuery)(skip => orderByQuery.drop(skip))
    val limitFilter = queryParameters.limitOption.fold(skipFilter)(limit => skipFilter.take(limit))

    limitFilter
  }

  def selectTopicsForSteward(queryParameters: QueryParameters):StewardsTopics = {
    blocking {
      database.withSession { implicit session: Session =>
        createStewardsTopics(topicCountQuery(queryParameters).length.run,
                              queryParameters.skipOption.getOrElse(0), //List starts at this record number
                              topicSelectQuery(queryParameters).list)
      }
    }
  }

```
##Produces SQL that looks like this: 
```Sql
select x2."id", x2."name", x2."description", x2."createdBy",
x2."createDate", x2."state", x2."changedBy", x2."changeDate" from "topics"
x2 where x2."state" = 'Pending'

```
###[Prev](SlickLiftedTable.md) [Next](SlickComposibleQuery.md)
