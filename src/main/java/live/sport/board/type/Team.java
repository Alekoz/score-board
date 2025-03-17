package live.sport.board.type;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Team extends TeamAbstract{
    public Team(String teamName, int score) {
        super(teamName, score);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeamAbstract teamAbstract = (TeamAbstract) o;
        return Objects.equals(teamName, teamAbstract.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(teamName);
    }
}
