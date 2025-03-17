package live.sport.board.type;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Objects;

@Getter
@Setter
public class Match extends MatchAbstract {
    public Match(TeamAbstract homeTeamAbstract, TeamAbstract awayTeamAbstract) {
        super(homeTeamAbstract, awayTeamAbstract);
    }

    public int getTotalMatchScores(){
        return homeTeam.getScore() + awayTeam.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(homeTeam, match.homeTeam) && Objects.equals(awayTeam, match.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam);
    }

    @Override
    public int compareTo(MatchAbstract o) {
        return Comparator.comparingInt(Match::getTotalMatchScores)
                .thenComparingLong(Match::getTimestamp)
                .reversed()
                .compare(this, (Match) o);
    }
}
