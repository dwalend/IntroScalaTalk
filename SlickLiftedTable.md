#Slick Lifted Table
```Scala
case class TopicRecord(id:Option[TopicId] = None,
                        name:String,
                        description:String,
                        createdBy:UserName,
                        createDate:Date,
                        state:TopicState,
                        changedBy:UserName,
                        changeDate:Date) { ... }}

  class TopicTable(tag:Tag) extends Table[TopicRecord](tag,"topics") {
    def id = column[TopicId]("id",O.PrimaryKey,O.AutoInc)
    def name = column[String]("name")
    def description = column[String]("description")
    def createdBy = column[UserName]("createdBy")
    def createDate = column[Date]("createDate")
    def state = column[TopicStateName]("state")
    def changedBy = column[UserName]("changedBy")
    def changeDate = column[Date]("changeDate")

    def * = (id.?, name, description, createdBy, createDate, state, changedBy, changeDate) <> (fromRow, toRow) //(TopicRecord.tupled,TopicRecord.unapply)

    def fromRow = (fromParams _).tupled

    def fromParams(id:Option[TopicId] = None,
                   name:String,
                   description:String,
                   createdBy:UserName,
                   createDate:Date,
                   stateName:String,
                   changedBy:UserName,
                   changeDate:Date): TopicRecord = {
      TopicRecord(id, name, description, createdBy, createDate, TopicState.namesToStates(stateName), changedBy, changeDate)
    }

    def toRow(topicRecord: TopicRecord) = Some((
        topicRecord.id,
        topicRecord.name,
        topicRecord.description,
        topicRecord.createdBy,
        topicRecord.createDate,
        topicRecord.state.name,
        topicRecord.changedBy,
        topicRecord.changeDate
        ))
  }

  val allTopicQuery = TableQuery[TopicTable]

```
###[Prev](Slick.md) [Next](SlickLiftedQuery.md)
