package live.sport.board;

import live.sport.board.type.Match;
import live.sport.board.type.Team;

import java.util.Collection;

public interface ScoreBoard {
   Match startNewMatch(Match match);
   Match updateScore(Team homeTeam, Team awayTeam);
   void removeMatch(Match match);
   Collection<Match> getSummary();
}
