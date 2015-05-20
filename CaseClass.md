#Case Classes
##Case Classes Are Scala's Common Currency
###[Fulfills Eight Items From Josh Bloch's _Effective Java_](http://grahamhackingscala.blogspot.com/2011/01/does-scala-equal-effective-java-bloch.html)
###[From One of Shrine's Microservices](https://open.med.harvard.edu/vvc/viewvc.cgi/shrine/trunk/code/steward/src/main/scala/net/shrine/steward/db/StewardDatabase.scala?view=markup)
```Scala
case class QueryParameters(researcherIdOption:Option[UserId] = None,
                            stateOption:Option[TopicState] =  None,
                            skipOption:Option[Int] =  None,
                            limitOption:Option[Int] = None,
                            sortByOption:Option[String] = None,
                            sortOrder:SortOrder = SortOrder.ascending
                          )

val everything = QueryParameters()
val justDave = QueryParameters(researcherIdOption = Some("dave"))
val justDaveByDate = justDave.copy(sortByOption = Some("date"))

      
```
##Decompile via javap
```Bash

> javap -p -cp .../target/classes net.shrine.steward.db.QueryParameters
      
```
```Java

public class QueryParameters implements Product,Serializable {

//A simple constructor

  public QueryParameters(Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder);

//Immutable member variables

  private final Option<String> researcherIdOption;
  private final Option<TopicState> stateOption;
  private final Option<Object> skipOption;
  private final Option<Object> limitOption;
  private final Option<String> sortByOption;
  private final SortOrder sortOrder;

//public accessors

  public Option<String> researcherIdOption();
  public Option<TopicState> stateOption();
  public Option<Object> skipOption();
  public Option<Object> limitOption();
  public Option<String> sortByOption();
  public SortOrder sortOrder();

//correct equals(), hashcode(), and a toString() that can be cut and pasted

  public boolean equals(Object);
  public boolean canEqual(Object);
  public int hashCode();
  public String toString();

//Default values for the constructor

  public static SortOrder $lessinit$greater$default$6();
  public static Option<String> $lessinit$greater$default$5();
  public static Option<Object> $lessinit$greater$default$4();
  public static Option<Object> $lessinit$greater$default$3();
  public static Option<TopicState> $lessinit$greater$default$2();
  public static Option<String> $lessinit$greater$default$1();
  public static SortOrder apply$default$6();
  public static Option<String> apply$default$5();
  public static Option<Object> apply$default$4();
  public static Option<Object> apply$default$3();
  public static Option<TopicState> apply$default$2();
  public static Option<String> apply$default$1();

//Standard creation and decomposition via apply(), unapply(), tupled(), and copy() constructors

  public static Option<Tuple6<Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder>> unapply(QueryParameters);
  public static QueryParameters apply(Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder);
  public static Function1<Tuple6<Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder>, QueryParameters> tupled();
  public static Function1<Option<String>, Function1<Option<TopicState>, Function1<Option<Object>, Function1<Option<Object>, Function1<Option<String>, Function1<SortOrder, QueryParameters>>>>>> curried();
  public QueryParameters copy(Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder);
  public Option<String> copy$default$1();
  public Option<TopicState> copy$default$2();
  public Option<Object> copy$default$3();
  public Option<Object> copy$default$4();
  public Option<String> copy$default$5();
  public SortOrder copy$default$6();

//Scala Product support

  public String productPrefix();
  public int productArity();
  public Object productElement(int);
  public collection.Iterator<Object> productIterator();

}

//A companion object with more support for apply() and unapply()

public final class QueryParameters$ extends runtime.AbstractFunction6<Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder, QueryParameters> implements Serializable {

  //apply() and unapply() for construction and deconstruction

  public QueryParameters apply(Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder);
  public Object apply(Object, Object, Object, Object, Object, Object);
  public Option<Tuple6<Option<String>, Option<TopicState>, Option<Object>, Option<Object>, Option<String>, SortOrder>> unapply(QueryParameters);
  public Option<String> apply$default$1();
  public Option<TopicState> apply$default$2();
  public Option<Object> apply$default$3();
  public Option<Object> apply$default$4();
  public Option<String> apply$default$5();
  public SortOrder apply$default$6();
  public Option<String> $lessinit$greater$default$1();
  public Option<TopicState> $lessinit$greater$default$2();
  public Option<Object> $lessinit$greater$default$3();
  public Option<Object> $lessinit$greater$default$4();
  public Option<String> $lessinit$greater$default$5();
  public SortOrder $lessinit$greater$default$6();

//correct readResolve() for a singleton and another useful toString()

  public final String toString();
  private Object readResolve();

  public static final QueryParameters$ MODULE$;
  public static {};

  private QueryParameters$();
}

      
```
###[Prev](Pros.md) [Next](MorePros.md)
