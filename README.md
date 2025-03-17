# score-board

Simple library to show football matches based on ConcurrentHashMap.

> [!NOTE]
> According to the assumption that data should be updated as faster as can be.
During the implementation was changed data structure according to assumption that dara should be updated fast 
and according to some overhead with getting and adding records to the ConcurrentSkipListSet.

The main interface for interaction is live.sport.board.ScoreBoard.
```
    Match startNewMatch(Match match);
    Match updateScore(Team homeTeam, Team awayTeam);
    void removeMatch(Match match);
    List<Match> getSummary();
```



Build with tests :
```mvn clean install```