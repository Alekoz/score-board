package live.sport.board.type;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Match {
    private final UUID id = UUID.randomUUID();
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

    public String matchName() {
        return homeTeam.getTeamName() + " " + awayTeam.getTeamName();
    }

}
