package live.sport.board.type;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.Objects;

@Getter
@Setter
public class Match implements Comparable<Match>{
    private Team homeTeam;
    private Team awayTeam;
    private final long timestamp;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.timestamp = System.nanoTime();
    }

    public int getTotalMatchScores(){
        return homeTeam.getScore() + awayTeam.getScore();
    }

    @Override
    public int compareTo(Match o) {
        return Comparator.comparingInt(Match::getTotalMatchScores)
                .thenComparingLong(Match::getTimestamp)
                .reversed()
                .compare(this, o);
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
}
