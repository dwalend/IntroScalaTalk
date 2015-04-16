# Spray's Request DSL

##[From test code](https://open.med.harvard.edu/vvc/viewvc.cgi/shrine/trunk/code/steward/src/test/scala/net/shrine/steward/StewardServiceSpec.scala?view=markup)

```Scala
  "DateStewardService" should "return the list of a researcher's Topics as Json for various states" in {
    StewardDatabase.db.insertRequestForTopicAccess(StewardUser.someResearcher,InboundTopicRequest(OutboundTopic.uncontroversialTopic.name,OutboundTopic.uncontroversialTopic.description))

    Get(s"/researcher/topics?state=Pending") ~> addCredentials(researcherCredentials) ~>
      route ~> check {
        assertResult(OK)(status)
...

```

```Scala
  "DateStewardService" should "injest a researcher's request to study a topic" in {
    Post(s"/researcher/requestTopicAccess",InboundTopicRequest(OutboundTopic.uncontroversialTopic.name,OutboundTopic.uncontroversialTopic.description)) ~>
      addCredentials(researcherCredentials) ~>
      route ~> check {
        assertResult(Accepted)(status)
```

[Prev](Spray.md) [Next](Spray-Route.md)