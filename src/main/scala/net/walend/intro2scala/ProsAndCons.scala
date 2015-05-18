package net.walend.intro2scala

import net.walend.present.{CodeSyntax, CodeBlock, LinkTextLine, LinkFragment, TextFragment, FragLine, Style, TextLine, SimpleSlide}


/**
 * @author david
 */

object ProsAndCons {


  val Pros = SimpleSlide("Pros",Seq(
    TextLine("Scala - Pros", Style.Title),
    TextLine("Thinking About a Problem Results in Readable, Concise Code", Style.HeadLine),
    FragLine(Seq(TextFragment("See"),LinkFragment("Dijkstra's Algorithm","http://en.wikipedia.org/wiki/Dijkstra's_algorithm#Pseudocode"),TextFragment("With"),LinkFragment("Semirings: Java vs Scala","http://dwalend.github.io/blog/2014/10/05/Semirings/"))),
    TextLine("FP Helps Make Code Comprehendable", Style.HeadLine),
    LinkTextLine("Let Your Pattern Book Collect Dust","http://alvinalexander.com/scala/how-scala-killed-oop-strategy-design-pattern#What_about_those_other_OOP_design_patterns", Style.SupportLine),
    TextLine("Gentle Learning Curve From Java",Style.HeadLine),
    TextLine("Runs on the Java VM",Style.SupportLine),
    TextLine("Use Scala as a Better Java on the First Day",Style.SupportLine),
    TextLine("Knowledge of Java Libraries Carries Forward",Style.SupportLine),
    TextLine("Out \"Javas\" Java in all but Community and Uptake"),
    FragLine(Seq(TextFragment("Compare"),LinkFragment("Scala's Intents","http://www.scala-lang.org/what-is-html"),TextFragment("to"),LinkFragment("Java's","http://www.oracle.com/technetwork/java/intro-141325.html#334")))
  ))

  val CaseClass = SimpleSlide("CaseClass",Seq(
    TextLine("Case Classes", Style.Title),
    TextLine("Case Classes Are Scala's Common Currency", Style.HeadLine),
    LinkTextLine("Fulfills Eight Items From Josh Bloch's _Effective Java_","http://grahamhackingscala.blogspot.com/2011/01/does-scala-equal-effective-java-bloch.html",Style.SupportLine),
    LinkTextLine("From One of Shrine's Microservices","https://open.med.harvard.edu/vvc/viewvc.cgi/shrine/trunk/code/steward/src/main/scala/net/shrine/steward/db/StewardDatabase.scala?view=markup",Style.SupportLine),
    CodeBlock(
      """case class QueryParameters(userIdOption:Option[UserId] = None,
        |                            stateOption:Option[TopicState] =  None,
        |                            skipOption:Option[Int] =  None,
        |                            limitOption:Option[Int] = None,
        |                            sortByOption:Option[String] = None,
        |                            sortOrder:SortOrder = SortOrder.ascending
        |                          )
        |
        |val everything = QueryParameters()
        |val justDave = QueryParameters(userIdOption = Some("dave"))
        |val justDaveByDate = justDave.copy(sortByOption = Some("date"))
        |
      """.stripMargin),
    TextLine("Decompile via javap",Style.HeadLine),
    CodeBlock(
      """
        |> javap -p -cp .../target/classes net.shrine.steward.db.QueryParameters
      """.stripMargin,CodeSyntax.Bash),
    CodeBlock(
      """
        |public class QueryParameters implements Product,Serializable {
        |
        |//A simple constructor
        |
        |  public QueryParameters(Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder);
        |
        |//Immutable member variables
        |
        |  private final Option<String> userIdOption;
        |  private final Option<TopicState> stateOption;
        |  private final Option<Object> skipOption;
        |  private final Option<Object> limitOption;
        |  private final Option<String> sortByOption;
        |  private final SortOrder sortOrder;
        |
        |//public accessors
        |
        |  public Option<String> userIdOption();
        |  public Option<TopicState> stateOption();
        |  public Option<Object> skipOption();
        |  public Option<Object> limitOption();
        |  public Option<String> sortByOption();
        |  public SortOrder sortOrder();
        |
        |//correct equals(), hashcode(), and a toString() that can be cut and pasted
        |
        |  public boolean equals(Object);
        |  public boolean canEqual(Object);
        |  public int hashCode();
        |  public String toString();
        |
        |//Default values for the constructor
        |
        |  public static SortOrder $lessinit$greater$default$6();
        |  public static Option<String> $lessinit$greater$default$5();
        |  public static Option<Object> $lessinit$greater$default$4();
        |  public static Option<Object> $lessinit$greater$default$3();
        |  public static Option<TopicState> $lessinit$greater$default$2();
        |  public static Option<String> $lessinit$greater$default$1();
        |  public static SortOrder apply$default$6();
        |  public static Option<String> apply$default$5();
        |  public static Option<Object> apply$default$4();
        |  public static Option<Object> apply$default$3();
        |  public static Option<TopicState> apply$default$2();
        |  public static Option<String> apply$default$1();
        |
        |//Standard creation and decomposition via apply(), unapply(), tupled(), and copy() constructors
        |
        |  public static Option<Tuple6<Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder>> unapply(QueryParameters);
        |  public static QueryParameters apply(Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder);
        |  public static Function1<Tuple6<Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder>, QueryParameters> tupled();
        |  public static Function1<Option<String>, Function1<Option<TopicState>, Function1<Option<Object>, Function1<Option<Object>, Function1<Option<String>, Function1<SortOrder, QueryParameters>>>>>> curried();
        |  public QueryParameters copy(Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder);
        |  public Option<String> copy$default$1();
        |  public Option<TopicState> copy$default$2();
        |  public Option<Object> copy$default$3();
        |  public Option<Object> copy$default$4();
        |  public Option<String> copy$default$5();
        |  public SortOrder copy$default$6();
        |
        |//Scala Product support
        |
        |  public String productPrefix();
        |  public int productArity();
        |  public Object productElement(int);
        |  public collection.Iterator<Object> productIterator();
        |
        |}
        |
        |//A companion object with more support for apply() and unapply()
        |
        |public final class QueryParameters$ extends runtime.AbstractFunction6<Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder, QueryParameters> implements Serializable {
        |
        |  //apply() and unapply() for construction and deconstruction
        |
        |  public QueryParameters apply(Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder);
        |  public Object apply(Object, Object, Object, Object, Object, Object);
        |  public Option<Tuple6<Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder>> unapply(QueryParameters);
        |  public Option<String> apply$default$1();
        |  public Option<TopicState> apply$default$2();
        |  public Option<Object> apply$default$3();
        |  public Option<Object> apply$default$4();
        |  public Option<String> apply$default$5();
        |  public SortOrder apply$default$6();
        |  public Option<String> $lessinit$greater$default$1();
        |  public Option<TopicState> $lessinit$greater$default$2();
        |  public Option<Object> $lessinit$greater$default$3();
        |  public Option<Object> $lessinit$greater$default$4();
        |  public Option<String> $lessinit$greater$default$5();
        |  public SortOrder $lessinit$greater$default$6();
        |
        |//correct readResolve() for a singleton and another useful toString()
        |
        |  public final String toString();
        |  private Object readResolve();
        |
        |  public static final QueryParameters$ MODULE$;
        |  public static {};
        |
        |  private QueryParameters$();
        |}
        |
      """.stripMargin,CodeSyntax.Java)
  ))

  val MorePros = SimpleSlide("MorePros",Seq(
    TextLine("Scala - More Pros", Style.Title),
    TextLine("Good Match for Contemporary and Next Generation Computing Environments", Style.HeadLine),
    TextLine("Immutable FP Can Use All Cores With Safety and Comfort",Style.SupportLine),
    FragLine(Seq(
      TextFragment("Distributed, Large Scale: "),
      LinkFragment("Akka","http://akka.io"),TextFragment(","),
      LinkFragment("Spark","https://spark.apache.org"),TextFragment(","),
      LinkFragment("Storm","https://storm.apache.org")
    ),Style.TertiaryLine),
    LinkTextLine("Spark sped up Hadoop by 100X","https://spark.apache.org",Style.TertiaryLine),
    FragLine(Seq(
      LinkFragment("Vectorized CPU","https://github.com/scalanlp/breeze/wiki/Quickstart"),TextFragment(" and "),
      LinkFragment("GPU","https://github.com/dlwh/puck"),TextFragment(" Libraries Being Developed")
    ),Style.TertiaryLine),
    FragLine(Seq(
      TextFragment("Compile Scala to Javascript via "),LinkFragment("scala.js","http://www.scala-js.org")),
      Style.HeadLine),
    TextLine("Run in a Browser or via node.js Instead of in the JVM",Style.TertiaryLine),
    LinkTextLine("Project TASTY to Target New Platforms More Easily","https://docs.google.com/document/d/1h3KUMxsSSjyze05VecJGQ5H2yh7fNADtIf3chD3_wr0/edit"),
    TextLine("Sometimes Future-Proof",Style.SupportLine),
    LinkTextLine("Spray to use Akka streams without changing existing Spray Routes","http://spray.io/msug/#/",Style.TertiaryLine),
    TextLine("Fun",Style.HeadLine)
  ))

  val Cons = SimpleSlide("Cons",Seq(
    TextLine("Scala - Cons",Style.Title),
    TextLine("Intimidating, Tall Learning Curve",Style.HeadLine),
    TextLine("Danger - Contains Monads, Category Theory, Programmable Type System",Style.SupportLine),
    TextLine("No Barrier or Marker Between Easy and Hard Concepts",Style.SupportLine),
    TextLine("'Read and Think' Instead of 'Panicked Keyboard Pounding'",Style.HeadLine),
    TextLine("Extremely Poor Theatre for Hourly Consulting",Style.SupportLine),
    TextLine("Does Not Help Figure Out What Problem to Solve",Style.HeadLine),
    TextLine("Using Scala Frees More Time for This Less Pleasant Task",Style.SupportLine)
  ))

  val MoreCons = SimpleSlide("MoreCons",Seq(
    TextLine("Scala - More Cons",Style.Title),
    TextLine("Small, Tight-Knit Community",Style.HeadLine),
    TextLine("Academic Instead of Pragmatic Biases",Style.SupportLine),
    TextLine("Local (Boston) Uptake in Islands",Style.HeadLine),
    TextLine("Kendall Square, South End, Harbor District, Longwood, Waltham",Style.TertiaryLine),
    TextLine("Rapidly Evolving Toolset and Libraries",Style.HeadLine),
    TextLine("Mild Disregard for Backwards Compatibility",Style.SupportLine)
  ))

  val slides = Seq(Pros,CaseClass,MorePros,Cons,MoreCons)
}