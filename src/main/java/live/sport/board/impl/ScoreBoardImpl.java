package live.sport.board.impl;

import live.sport.board.ScoreBoard;
import live.sport.board.type.Match;
import live.sport.board.type.MatchAbstract;
import live.sport.board.type.TeamAbstract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoardImpl implements ScoreBoard {

    private final ConcurrentHashMap<MatchAbstract, MatchAbstract> matches = new ConcurrentHashMap<>();

    @Override
    public MatchAbstract startNewMatch(MatchAbstract match) {
        if (Objects.isNull(match.getHomeTeam()) || Objects.isNull(match.getAwayTeam())) {
            throw new IllegalArgumentException("The match can't be null.");
        }
        if (match.getHomeTeam().getScore() != 0 || match.getAwayTeam().getScore() != 0) {
            throw new IllegalArgumentException("The match should starts only with 0 scores.");
        }
        if ((Objects.isNull(match.getHomeTeam().getTeamName()) || match.getHomeTeam().getTeamName().isEmpty())
                || (Objects.isNull(match.getAwayTeam().getTeamName()) || match.getAwayTeam().getTeamName().isEmpty())) {
            throw new IllegalArgumentException("Team name cannot be null or empty");
        }
        if (Objects.nonNull(matches.get(match))) {
            throw new IllegalArgumentException("The match already in progress.");
        }

        var result = matches.put(match, match);
        return Objects.isNull(result) ? match : result;
    }

    @Override
    public MatchAbstract updateScore(TeamAbstract homeTeamAbstract, TeamAbstract awayTeamAbstract) {
        if (!matches.containsKey(new Match(homeTeamAbstract, awayTeamAbstract))) {
            throw new IllegalArgumentException("Match not found!");
        }
        var match = matches.get(new Match(homeTeamAbstract, awayTeamAbstract));
        match.setHomeTeam(homeTeamAbstract);
        match.setAwayTeam(awayTeamAbstract);

        return matches.put(match, match);
    }

    @Override
    public void removeMatch(MatchAbstract match) {
        matches.remove(match);
    }

    @Override
    public List<MatchAbstract> getSummary() {
        var c = new ArrayList<>(matches.values());
        Collections.sort(c);
        return c;
    }
}
