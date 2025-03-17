package live.sport.board;

import live.sport.board.type.Match;
import live.sport.board.type.Team;

import java.util.List;

public interface ScoreBoard {
   Match startNewMatch(Match match);
   Match updateScore(Team homeTeam, Team awayTeam);
   void removeMatch(Match match);
   List<Match> getSummary();
}
