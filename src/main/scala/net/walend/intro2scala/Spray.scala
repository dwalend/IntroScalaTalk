package net.walend.intro2scala

import net.walend.present.{TextLine, CodeBlock, LinkTextLine, SimpleSlide, Style}

/**
 *
 *
 * @author dwalend
 * @since v0.1.2
 */
object Spray {

  val SprayIntro = SimpleSlide("SprayIntro",
    LinkTextLine("Spray.io","http://spray.io/introduction/what-is-spray/",Style.Title),
    TextLine("A Library For Micro Web Services and Clients",Style.HeadLine),
    LinkTextLine("Asyncrhonous, Actor-Based, NIO-Based, Fast, Lightweight, Modular, and Testable","http://spray.io/introduction/what-is-spray/",Style.SupportLine),
    TextLine("A Spray HttpService Builds an HttpResponse for an HttpRequest Using a Route",Style.TertiaryLine),
    TextLine("A Spray Service Might Use All IO or All Cores With a Gentle Degradation",Style.SupportLine),
    TextLine("Timeout 500 Status Without Systemic Failure",Style.TertiaryLine),
    TextLine("Spray Provides Domain-Specific Languages",Style.HeadLine),
    TextLine("Make HttpRequests in Clients",Style.SupportLine),
    TextLine("Respond to HttpRequests in Micro Servers",Style.SupportLine),
    LinkTextLine("Next Version to be Renamed Akka-Http, Built on Akka Streams","http://typesafe.com/blog/typesafe-gets-sprayed",Style.HeadLine),
    LinkTextLine("Play2's Internals to be Rewritten to Use Akka-Http","http://typesafe.com/blog/typesafe-gets-sprayed",Style.SupportLine)
  )

  val SprayRoute = SimpleSlide("SprayRoute",
    TextLine("A Spray Route is a Function That Takes a RequestContext",Style.HeadLine),
    TextLine("A RequestConext is an HttpRequest Plus Odd Bits",Style.SupportLine),
    TextLine("Route Does Not Return Anything, But Can Send an HttpResponse to an Akka Actor",Style.SupportLine),
    TextLine("Routes Are Made From Directives",Style.TertiaryLine),
    CodeBlock("""abstract class Directive[L <: shapeless.HList] { self =>
                |    def happly(f: L => Route): Route""".stripMargin),
    TextLine("A Shapeless HList is ...",Style.TertiaryLine),
    TextLine("... Try the Route DSL and See What Happens",Style.TertiaryLine)
  )

  val SprayRouteDsl = SimpleSlide("SprayRouteDsl",
    TextLine("Spray Routing Provides a DSL",Style.HeadLine),
    TextLine("(Conceptually) Converts an HttpRequest Into an HttpResponse",Style.SupportLine),
    CodeBlock("https://datasteward.example.edu/researcher/topics?skip=10&limit=5&sortBy=name&sortDirection=ascending"),
    CodeBlock("""  lazy val route:Route =
                |    staticResources ~
                |      logRequestResponse("route",Logging.DebugLevel) { authenticatedUser }
                |  }
                |
                |  lazy val authenticatedUser:Route = authenticate(UserAuthenticator.basicUserAuthenticator) { user =>
                |    pathPrefix("qep"){qepRoute(user)} ~
                |      pathPrefix("researcher"){researcherRoute(user)} ~
                |      pathPrefix("steward"){stewardRoute(user)}
                |  }
                |
                |  def researcherRoute(user:User):Route = authorize(UserAuthenticator.authorizeResearcher(user)) {
                |    pathPrefix("topics") {getUserTopics(user)} ~
                |      pathPrefix("queryHistory") {getUserQueryHistory(Some(user))} ~
                |      pathPrefix("requestTopicAccess") {requestTopicAccess(user) }
                |  }
                |
                |  def getUserTopics(user:User):Route = get {
                |    matchQueryParameters(Some(user)) {queryParameters:QueryParameters =>
                |      val researchersTopics =
                |        StewardDatabase.db.selectTopicsForResearcher(queryParameters)
                |
                |      complete(researchersTopics)
                |    }
                |  }
                |""".stripMargin)

  )

  val SprayDirective = SimpleSlide("SprayDirective",
    TextLine("A Spray Directive"),
    TextLine("Tried the parameters() Directive to Create New QueryParameters",Style.TertiaryLine),
    CodeBlock("""    def getUserTopics(userId:UserId):Route = get {
                |      parameters(('userName.?,'state.?,'skip.as[Int].?,'limit.as[Int].?,'sortBy.as[String].?,'sortDirection.as[String].?).as[QueryParameters]) {
                |        queryParameters =>
                |          val researchersTopics = StewardDatabase.db.selectTopicsForResearcher(queryParameters)
                |          complete(researchersTopics)
                |      }
                |    }
                |""".stripMargin),
    TextLine("But It Didn't Quite Fit",Style.TertiaryLine),
    TextLine("User Name Can Come In Through Authentication (Researchers) or Prefix (Steward)",Style.TertiaryLine),
    TextLine("TopicState is an Enumeration, Needs Error Checking",Style.TertiaryLine),
    TextLine("Didn't Want to Cut-Paste That Much Code",Style.TertiaryLine),
    CodeBlock("""  import shapeless.{:: => shapelessConcat}
                |  case class matchQueryParameters(researcherId:Option[UserId] = None) extends Directive[QueryParameters shapelessConcat HNil] {
                |    import spray.routing.directives.ParameterDirectives._
                |    import spray.routing.directives.RouteDirectives.complete
                |    import spray.routing.directives.RespondWithDirectives.respondWithStatus
                |
                |    override def happly(f: (shapelessConcat[QueryParameters, HNil]) => Route): Route = {
                |      //todo name safety for column names and sort direction
                |      parameters('state.?,'skip.as[Int].?,'limit.as[Int].?,'sortBy.as[String].?,'sortDirection.as[String].?) { (stateStringOption,skipOption,limitOption,sortByOption,sortOption) =>
                |
                |        val stateTry = TopicState.stateForStringOption(stateStringOption)
                |        stateTry match {
                |          case Success(stateOption) => {
                |              val qp = QueryParameters(researcherId,
                |                                        stateOption,
                |                                        skipOption,
                |                                        limitOption,
                |                                        sortByOption,
                |                                        SortOrder.sortOrderForStringOption(sortOption))
                |              f(shapelessConcat(qp,HNil))
                |          }
                |          case Failure(ex) => badStateRoute(stateStringOption)
                |        }
                |      }
                |    }
                |
                |    def badStateRoute(stateStringOption:Option[String]):Route = {
                |      respondWithStatus(StatusCodes.UnprocessableEntity) {
                |        complete(s"State ${stateStringOption.getOrElse(s"$stateStringOption (stateStringOption should never be None at this point)")} unknown. Please specify one of ${TopicState.namesToStates.keySet}")
                |      }
                |    }
                |  }""".stripMargin)

  )

  val SprayNoDirective = SimpleSlide("SprayNoDirective",
    TextLine("Better With a Higher Order Function"),
    CodeBlock("""  def matchQueryParameters(userName: Option[UserName])(parameterRoute:QueryParameters => Route): Route =  {
                |
                |    parameters('state.?,'skip.as[Int].?,'limit.as[Int].?,'sortBy.as[String].?,'sortDirection.as[String].?,'minDate.as[Date].?,'maxDate.as[Date].?) { (stateStringOption,skipOption,limitOption,sortByOption,sortOption,minDate,maxDate) =>
                |
                |      val stateTry = TopicState.stateForStringOption(stateStringOption)
                |      stateTry match {
                |        case Success(stateOption) =>
                |          val qp = QueryParameters(userName,
                |            stateOption,
                |            skipOption,
                |            limitOption,
                |            sortByOption,
                |            SortOrder.sortOrderForStringOption(sortOption),
                |            minDate,
                |            maxDate
                |          )
                |
                |          parameterRoute(qp)
                |
                |        case Failure(ex) => badStateRoute(stateStringOption)
                |      }
                |    }
                |  }
                |""".stripMargin),
    CodeBlock("""  def getUserTopics(researcherId:UserName):Route = get {
                |    //lookup topics for this user in the db
                |   matchQueryParameters(Some(researcherId)){queryParameters:QueryParameters =>
                |      val researchersTopics = StewardDatabase.db.selectTopicsForResearcher(queryParameters)
                |      complete(researchersTopics)
                |    }
                |  }
                |""".stripMargin)
  )

  val slides = Seq(SprayIntro,SprayRoute,SprayRouteDsl,SprayDirective,SprayNoDirective)
}
