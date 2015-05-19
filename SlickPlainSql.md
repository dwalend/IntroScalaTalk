#Slick Plain SQL
```Scala


      val columnNamesToSelect = columnsToSelect.map(_.name)

      //Get distinct researcher ids
      val distinctResearchersIdQuery = sql"select distinct(#$aniIdColumnName) from #$nodeTableName where #$roleColumnName = ${NodeType.RESEARCHER.abbreviation} or #$roleColumnName = ${NodeType.PHYSRES.abbreviation}".as[String]
      val researcherIds = distinctResearchersIdQuery.iterator

      //Get all of the data for all of the distinct researchers, except the id (not distinct)
      val researchersQuery: StaticQuery[String, List[String]] = StaticQuery.query(s"select ${columnNamesToSelect.mkString(",")} from $nodeTableName where $aniIdColumnName = ?")

      //Query for a list for each id
      val researchers = researcherIds.map(id => researchersQuery(id).first)
      //For each id, make a Map from column names to values
      val researcherMaps = researchers.map(researcher => columnNamesToSelect.zip(researcher).toMap)

      //remove any null values from the map -- a bit of a hack
      val researcherMapsWithoutNulls = researcherMaps.map(researcher => researcher.filter(x => x._2 != null))

```
###[Prev](SlickComposibleQuery.md) [Next](Slick3.md)
