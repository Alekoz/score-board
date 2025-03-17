package live.sport.board.impl;

import live.sport.board.ScoreBoard;
import live.sport.board.type.Match;
import live.sport.board.type.Team;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListSet;

public class ScoreBoardImpl implements ScoreBoard {

    private final Comparator<Match> matchComparator = Comparator.comparingInt(Match::getTotalMatchScores)
            .thenComparing(Match::getTimestamp)
            .reversed();

    private final ConcurrentSkipListSet<Match> matches = new ConcurrentSkipListSet<>(matchComparator);

    @Override
    public Match startNewMatch(Match match) {
        if(Objects.isNull(match.getHomeTeam()) || Objects.isNull(match.getAwayTeam())) {
            throw new IllegalArgumentException("The match can't be null.");
        }
        if (match.getHomeTeam().getScore() != 0 || match.getAwayTeam().getScore() != 0) {
            throw new IllegalArgumentException("The match should starts only with 0 scores.");
        }
        if (matches.stream().anyMatch(m -> m.matchName().equals(match.matchName()))){
            throw new IllegalArgumentException("The match already in progress.");
        }


        return matches.add(match) ? match : null;
    }

    @Override
    public Match updateScore(Team homeTeam, Team awayTeam) {
        var existedMatch = matches.stream()
                .filter(m -> m.matchName().equals(homeTeam.getTeamName() + " " + awayTeam.getTeamName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Match not found!"));
        matches.remove(existedMatch);
        existedMatch.getHomeTeam().setScore(homeTeam.getScore());
        existedMatch.getAwayTeam().setScore(awayTeam.getScore());
        matches.add(existedMatch);
        return existedMatch;
    }

    @Override
    public void removeMatch(Match match) {
        matches.remove(match);
    }

    @Override
    public Collection<Match> getSummary() {
        return matches;
    }
}
