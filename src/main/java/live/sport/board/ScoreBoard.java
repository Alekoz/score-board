package live.sport.board;

import live.sport.board.type.MatchAbstract;
import live.sport.board.type.TeamAbstract;

import java.util.List;

public interface ScoreBoard {
   MatchAbstract startNewMatch(MatchAbstract match);
   MatchAbstract updateScore(TeamAbstract homeTeamAbstract, TeamAbstract awayTeamAbstract);
   void removeMatch(MatchAbstract match);
   List<MatchAbstract> getSummary();
}
