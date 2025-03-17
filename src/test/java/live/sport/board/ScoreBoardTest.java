package live.sport.board;

import live.sport.board.impl.ScoreBoardImpl;
import live.sport.board.type.Match;
import live.sport.board.type.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


@ExtendWith(MockitoExtension.class)
public class ScoreBoardTest {

    private final ScoreBoard scoreBoard = new ScoreBoardImpl();

    @Test
    public void shouldAddNewMatch() {
        scoreBoard.startNewMatch(createMatch(new Team("Mexico", 0), new Team("Canada", 0)));
        assertEquals(1, scoreBoard.getSummary().size());
    }

    @Test
    public void shouldUpdateMatchScores() {
        scoreBoard.startNewMatch(createMatch(new Team("Mexico", 0), new Team("Canada", 0)));
        scoreBoard.updateScore(new Team("Mexico", 0), new Team("Canada", 1));
        assertThat(scoreBoard.getSummary().stream().findFirst().orElse(null))
                .extracting(match -> match.getAwayTeam().getScore())
                .contains(1);
    }

    @Test
    public void shouldRemoveMatch() {
        var match1 = scoreBoard.startNewMatch(new Match(new Team("Mexico", 0), new Team("Canada", 0)));
        scoreBoard.startNewMatch(new Match(new Team("Spain", 0), new Team("Brazil", 0)));
        scoreBoard.removeMatch(match1);
        assertEquals(1, scoreBoard.getSummary().size());
    }

    @Test
    public void shouldGetSummaryOfMatchesInRightOrder() {
        var match1 = scoreBoard.startNewMatch(createMatch(new Team("Mexico", 0), new Team("Canada", 0)));
        var match2 = scoreBoard.startNewMatch(createMatch(new Team("Spain", 0), new Team("Brazil", 0)));
        var match3 = scoreBoard.startNewMatch(createMatch(new Team("Germany", 0), new Team("France", 0)));
        var match4 = scoreBoard.startNewMatch(createMatch(new Team("Uruguay", 0), new Team("Italy", 0)));
        var match5 = scoreBoard.startNewMatch(createMatch(new Team("Argentina", 0), new Team("Australia", 0)));

        var result = List.of(match4, match2, match1, match5, match3);

        scoreBoard.updateScore(new Team("Uruguay", 6), new Team("Italy", 6));
        scoreBoard.updateScore(new Team("Mexico", 0), new Team("Canada", 5));
        scoreBoard.updateScore(new Team("Germany", 2), new Team("France", 2));
        scoreBoard.updateScore(new Team("Spain", 10), new Team("Brazil", 2));
        scoreBoard.updateScore(new Team("Argentina", 3), new Team("Australia", 1));

        assertThat(scoreBoard.getSummary()).containsExactlyElementsOf(result);
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWithInitialScoresMoreThan0() {
        assertThrowsExactly(IllegalArgumentException.class, () -> scoreBoard.startNewMatch(createMatch(new Team("Mexico", 1), new Team("Canada", 0))), "The match should starts only with 0 scores.");
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWhenMatchIsAlreadyInProgressForTeam() {
        scoreBoard.startNewMatch(createMatch(new Team("Mexico", 0), new Team("Canada", 0)));
        assertThrowsExactly(IllegalArgumentException.class, () -> scoreBoard.startNewMatch(createMatch(new Team("Canada", 0), new Team("Mexico", 0))), "The match already in progress.");
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWhenMatchIsAlreadyInProgress() {
        scoreBoard.startNewMatch(createMatch(new Team("Mexico", 0), new Team("Canada", 0)));
        assertThrowsExactly(IllegalArgumentException.class, () -> scoreBoard.startNewMatch(createMatch(new Team("Mexico", 0), new Team("Canada", 0))), "The match already in progress.");
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWithInitialScoresLessThan0() {
        assertThrowsExactly(IllegalArgumentException.class, () -> scoreBoard.startNewMatch(createMatch(new Team("Mexico", -1), new Team("Canada", 0))), "The match should starts only with 0 scores.");
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWhenOneOfTeamIsNull() {
        assertThrowsExactly(IllegalArgumentException.class, () -> scoreBoard.startNewMatch(createMatch(null, new Team("Canada", 0))));
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWhenOneOfTeamNameNull() {
        assertThrowsExactly(IllegalArgumentException.class, () -> scoreBoard.startNewMatch(createMatch(new Team(null, 0), new Team("Canada", 0))), "Team name cannot be null or empty");
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWhenOneOfTeamNameEmptyString() {
        assertThrowsExactly(IllegalArgumentException.class, () -> scoreBoard.startNewMatch(createMatch(new Team("", 0), new Team("Canada", 0))),"Team name cannot be null or empty");
    }

    private Match createMatch(Team homeTeam, Team awayTeam) {
        return new Match(homeTeam, awayTeam);
    }
}
