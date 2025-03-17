package live.sport.board.type;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class TeamAbstract {
    protected String teamName;
    protected int score;

    protected TeamAbstract(String teamName, int score) {
        setTeamName(teamName);
        this.score = score;
    }
}
