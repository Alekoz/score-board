package live.sport.board.type;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Team {
    private String teamName;
    private int score;

    public Team(String teamName, int score) {
        setTeamName(teamName);
        this.score = score;
    }

    private void setTeamName(String teamName) {
        if(Objects.isNull(teamName) || teamName.trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be empty");
        }
        this.teamName = teamName;
    }
}
