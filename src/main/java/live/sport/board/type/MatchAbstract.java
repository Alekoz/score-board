package live.sport.board.type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MatchAbstract implements Comparable<MatchAbstract> {
    protected TeamAbstract homeTeam;
    protected TeamAbstract awayTeam;
    public final long timestamp;


    protected MatchAbstract(TeamAbstract homeTeam, TeamAbstract awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.timestamp = System.nanoTime();
    }

    public abstract int compareTo(MatchAbstract o);
}
