# [Slick Lifted Table](#### [Compact, clean code](https://open.med.harvard.edu/vvc/viewvc.cgi/shrine/trunk/code/steward/src/main/scala/net/shrine/steward/db/StewardDatabase.scala?view=markup)

## TopicTable has all the parts to hold a DB table of TopicRecords

```Scala
case class TopicRecord(id:Option[TopicId] = None,
                        user:UserId,
                        name:String,
                        description:String,
                        state:TopicState,
                        date:Date)

class TopicTable(tag:Tag) extends Table[TopicRecord](tag,"topics") {
  def id = column[TopicId]("id",O.PrimaryKey,O.AutoInc)
  def userId = column[UserId]("userId") //todo index
  def name = column[String]("name")
  def description = column[String]("description")
  def state = column[String]("state")//todo index
  def date = column[Date]("date")

  def * = (id.?, userId, name, description, state, date) <> (fromRow, toRow) //(TopicRecord.tupled,TopicRecord.unapply)

  def fromRow = (fromParams _).tupled

  def fromParams(id: Option[TopicId],
                 user: UserId,
                 name: String,
                 description: String,
                 state: String,
                 date: Date): TopicRecord = {
    TopicRecord(id, user, name, description, TopicState.namesToStates(state), date)
  }
  def toRow(topicRecord: TopicRecord):Option[(Option[TopicId],UserId,String,String,String,Date)] =
    Some((topicRecord.id,
      topicRecord.user,
      topicRecord.name,
      topicRecord.description,
      topicRecord.state.name,
      topicRecord.date))

}

val allTopicQuery = TableQuery[TopicTable]
```
[Prev](Slick.md) [Next](Slick-Query.md)
