#New Requirement - Change History for Topics
##Composible Queries Work
```Scala
  val mostRecentTopicQuery: Query[TopicTable, TopicRecord, Seq] = for(
    topic <- allTopicQuery if !allTopicQuery.filter(_.id === topic.id).filter(_.changeDate > topic.changeDate).exists
  ) yield topic

  private def topicCountQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
    val allTopics:Query[TopicTable, TopicTable#TableElementType, Seq] = mostRecentTopicQuery
...
```
##Now SQL that looks like this: 
```Sql
select x2."id", x2."name", x2."description", x2."createdBy",
x2."createDate", x2."state", x2."changedBy", x2."changeDate" from "topics"
x2 where (not exists(select x3."createDate", x3."description", x3."state",
x3."changeDate", x3."changedBy", x3."id", x3."createdBy", x3."name" from
"topics" x3 where (x3."id" = x2."id")
and (x3."changeDate" > x2."changeDate"))) and (x2."state" = 'Pending')

```
###[Prev](SlickLiftedQuery.md) [Next](SlickPlainSql.md)
