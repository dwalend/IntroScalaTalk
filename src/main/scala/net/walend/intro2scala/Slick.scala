package net.walend.intro2scala

import net.walend.present.{CodeBlock, TextLine, LinkTextLine, LinkFragment, TextFragment, FragLine, Style, SimpleSlide}

/**
 *
 *
 * @author dwalend
 * @since v0.1.2
 */
object Slick {

  val SlickIntro = SimpleSlide("Slick",Seq(
    LinkTextLine("Slick","http://slick.typesafe.com/doc/2.1.0/introduction.html", Style.Title),  //todo 3.0 link
    TextLine("A Database Library For Scala", Style.HeadLine),
    TextLine("Uses Scala's Common Currencies In Its API",Style.SupportLine),
    TextLine("Case Classes and Tuples",Style.SupportLine),
    TextLine("Lifted API For Functional-Style Queries",Style.HeadLine),
    LinkTextLine("Compact, Clean Code","https://open.med.harvard.edu/vvc/viewvc.cgi/shrine/trunk/code/steward/src/main/scala/net/shrine/steward/db/StewardDatabase.scala?view=markup",Style.SupportLine),
    LinkTextLine("Composable Queries","https://open.med.harvard.edu/vvc/viewvc.cgi/shrine/trunk/code/steward/src/main/scala/net/shrine/steward/db/StewardDatabase.scala?view=markup",Style.SupportLine),
    TextLine("Plain SQL API Gives Full Control Over SQL",Style.HeadLine),
    TextLine("Isolates SQL's Complexity",Style.SupportLine)
  ))

  val SlickLiftedTable = SimpleSlide("SlickLiftedTable",Seq(
    TextLine("Slick Lifted Table",Style.Title),
    CodeBlock("""case class TopicRecord(id:Option[TopicId] = None,
                |                        name:String,
                |                        description:String,
                |                        createdBy:UserName,
                |                        createDate:Date,
                |                        state:TopicState,
                |                        changedBy:UserName,
                |                        changeDate:Date) { ... }}
                |
                |  class TopicTable(tag:Tag) extends Table[TopicRecord](tag,"topics") {
                |    def id = column[TopicId]("id",O.PrimaryKey,O.AutoInc)
                |    def name = column[String]("name")
                |    def description = column[String]("description")
                |    def createdBy = column[UserName]("createdBy")
                |    def createDate = column[Date]("createDate")
                |    def state = column[TopicStateName]("state")
                |    def changedBy = column[UserName]("changedBy")
                |    def changeDate = column[Date]("changeDate")
                |
                |    def * = (id.?, name, description, createdBy, createDate, state, changedBy, changeDate) <> (fromRow, toRow) //(TopicRecord.tupled,TopicRecord.unapply)
                |
                |    def fromRow = (fromParams _).tupled
                |
                |    def fromParams(id:Option[TopicId] = None,
                |                   name:String,
                |                   description:String,
                |                   createdBy:UserName,
                |                   createDate:Date,
                |                   stateName:String,
                |                   changedBy:UserName,
                |                   changeDate:Date): TopicRecord = {
                |      TopicRecord(id, name, description, createdBy, createDate, TopicState.namesToStates(stateName), changedBy, changeDate)
                |    }
                |
                |    def toRow(topicRecord: TopicRecord):Option[(Option[TopicId],String,String,UserName,Date,String,UserName,Date)] =
                |      Some((topicRecord.id,
                |        topicRecord.name,
                |        topicRecord.description,
                |        topicRecord.createdBy,
                |        topicRecord.createDate,
                |        topicRecord.state.name,
                |        topicRecord.changedBy,
                |        topicRecord.changeDate
                |        ))
                |  }
                |
                |  val allTopicQuery = TableQuery[TopicTable]
                |""".stripMargin)
  ))

  val SlickLiftedQuery = SimpleSlide("SlickLiftedQuery",Seq(
    TextLine("Slick Composible Lifted Query",Style.Title),
    CodeBlock("""  private def topicCountQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
                |    val allTopics:Query[TopicTable, TopicTable#TableElementType, Seq] = allTopicQuery
                |    val researcherFilter = queryParameters.researcherIdOption.fold(allTopics)(userId => allTopics.filter(_.createdBy === userId))
                |    val stateFilter = queryParameters.stateOption.fold(researcherFilter)(state => researcherFilter.filter(_.state === state.name))
                |    val minDateFilter = queryParameters.minDate.fold(stateFilter)(minDate => stateFilter.filter(_.changeDate >= minDate))
                |    val maxDateFilter = queryParameters.maxDate.fold(minDateFilter)(maxDate => minDateFilter.filter(_.changeDate <= maxDate))
                |
                |    maxDateFilter
                |  }
                |
                |  private def topicSelectQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
                |    val countFilter = topicCountQuery(queryParameters)
                |
                |    //todo is there some way to do something with a map from column names to columns that I don't have to update? I couldn't find one.
                |    val orderByQuery = queryParameters.sortByOption.fold(countFilter)(
                |      columnName => countFilter.sortBy(x => queryParameters.sortOrder.orderForColumn(columnName match {
                |        case "id" => x.id
                |        case "name" => x.name
                |        case "description" => x.description
                |        case "createdBy" => x.createdBy
                |        case "createDate" => x.createDate
                |        case "state" => x.state
                |        case "changedBy" => x.changedBy
                |        case "changeDate" => x.changeDate
                |      })))
                |
                |    val skipFilter = queryParameters.skipOption.fold(orderByQuery)(skip => orderByQuery.drop(skip))
                |    val limitFilter = queryParameters.limitOption.fold(skipFilter)(limit => skipFilter.take(limit))
                |
                |    limitFilter
                |  }
                |
                |  def selectTopicsForSteward(queryParameters: QueryParameters):StewardsTopics = {
                |    database.withSession { implicit session: Session =>
                |      createStewardsTopics(topicCountQuery(queryParameters).length.run,
                |                            queryParameters.skipOption.getOrElse(0), //List starts at this record number
                |                            topicSelectQuery(queryParameters).list)
                |    }
                |  }
                |""")

  ))
    //todo show what SQL this produces

  // todo SQL statements for this query, too
  val SlickComposableQuery = SimpleSlide("SlickLiftedQuery",Seq(
    TextLine("New Requirement -- Keep Change History for Topics",Style.Title),
    TextLine("Composible Queries Payed Off",Style.HeadLine),
    CodeBlock("""  val mostRecentTopicQuery: Query[TopicTable, TopicRecord, Seq] = for(
                |    topic <- allTopicQuery if !allTopicQuery.filter(_.id === topic.id).filter(_.changeDate > topic.changeDate).exists
                |  ) yield topic
                |
                |  private def topicCountQuery(queryParameters: QueryParameters):Query[TopicTable, TopicTable#TableElementType, Seq] = {
                |    val allTopics:Query[TopicTable, TopicTable#TableElementType, Seq] = mostRecentTopicQuery
                |...""".stripMargin)
  ))

  val SlickPlainSql = SimpleSlide("SlickPlainSql",Seq(
    TextLine("Slick Plain SQL",Style.Title),
    CodeBlock("""      val tables = MTable.getTables(None, None, None, None)
                |      val table = tables.list.filter(_.name.name == nodeTableName)
                |      val columns: List[MColumn] = table.head.getColumns.list
                |
                |      val columnTypeMap: Map[String, String] = columns.map(column => (column.name, column.sqlTypeName.get)).toMap
                |      val columnMapAndProblems: ColumnMap = ColumnMap(columnTypeMap, Seq.empty, 0, nodeTableName)
                |
                |      val columnsToSelect = columns.filterNot(_.name.equalsIgnoreCase(idColumnName))
                |        .filterNot(_.name.equalsIgnoreCase(regionColumnName))
                |        .filterNot(_.name.equalsIgnoreCase(originColumnName))
                |        .filterNot(_.name.equalsIgnoreCase(lineColumnName))
                |
                |      val columnNamesToSelect = columnsToSelect.map(_.name)
                |
                |      //Get distinct researcher ids                                                                                               |      val distinctResearchersIdQuery = sql"select distinct(#$aniIdColumnName) from #$nodeTableName where #$roleColumnName = ${NodeType.RESEARCHER.abbreviation} or #$roleColumnName = ${NodeType.PHYSRES.abbreviation}".as[String]
                |      val researcherIds = distinctResearchersIdQuery.iterator
                |
                |      //Get all of the data for all of the distinct researchers, except the id (which won't be distinct)                          |      val researchersQuery: StaticQuery[String, List[String]] = StaticQuery.query(s"select ${columnNamesToSelect.mkString(",")} from $nodeTableName where $aniIdColumnName = ?")
                |
                |      //Query for a list for each id
                |      val researchers = researcherIds.map(id => researchersQuery(id).first)
                |      //For each id, make a Map from column names to values
                |      val researcherMaps = researchers.map(researcher => columnNamesToSelect.zip(researcher).toMap)
                |
                |      //remove any null values from the map -- a bit of a hack                                                                    |      val researcherMapsWithoutNulls = researcherMaps.map(researcher => researcher.filter(x => x._2 != null))
                |""")
  ))




  //todo Slick 3.0

  val slides = Seq(SlickIntro,SlickLiftedTable,SlickLiftedQuery,SlickComposableQuery,SlickPlainSql)
}