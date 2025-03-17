# score-board

Simple library to show football matches based on ConcurrentHashMap.

> [!NOTE]
> According to the assumption that data should be updated as faster as can be.
During the implementation was changed data structure according to assumption that dara should be updated fast 
and according to some overhead with getting and adding records to the ConcurrentSkipListSet.

The main interface for interaction is live.sport.board.ScoreBoard.
```
   MatchAbstract startNewMatch(MatchAbstract match);
   MatchAbstract updateScore(TeamAbstract homeTeamAbstract, TeamAbstract awayTeamAbstract);
   void removeMatch(MatchAbstract match);
   List<MatchAbstract> getSummary();
```

Also added match abstraction in case for someone will be interested to change sorting logic.

Build with tests :
```mvn clean install```