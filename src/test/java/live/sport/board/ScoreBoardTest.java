package live.sport.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
        var match1 = scoreBoard.startNewMatch(createMatch(new Team("Mexico", 0), new Team("Canada", 0)));
        var match2 = scoreBoard.startNewMatch(createMatch(new Team("Spain", 0), new Team("Brazil", 0)));
        scoreBoard.startNewMatch(match1);
        scoreBoard.startNewMatch(match2);
        match1.getAwayTeam().updateScore(1);
        scoreBoard.updateScore(match);
        assertTrue(scoreBoard.getSummary().contains(match1));
    }

    @Test
    public void shouldRemoveMatch() {
        var match1 = scoreBoard.startNewMatch(createMatch(new Team("Mexico", 0), new Team("Canada", 0)));
        var match2 = scoreBoard.startNewMatch(createMatch(new Team("Spain", 0), new Team("Brazil", 0)));
        scoreBoard.removeMatch(match1);
        assertTrue(scoreBoard.getSummary().contains(match2));
        assertEquals(1, scoreBoard.getSummary().size);
    }

    @Test
    public void shouldGetSummaryOfMatchesInRightOrder() {
        var match1 = scoreBoard.startNewMatch(createMatch(new Team("Mexico", 0), new Team("Canada", 0)));
        var match2 = scoreBoard.startNewMatch(createMatch(new Team("Spain", 0), new Team("Brazil", 0)));
        var match3 = scoreBoard.startNewMatch(createMatch(new Team("Germany", 0), new Team("France", 0)));
        var match4 = scoreBoard.startNewMatch(createMatch(new Team("Uruguay", 0), new Team("Italy", 0)));
        var match5 = scoreBoard.startNewMatch(createMatch(new Team("Argentina", 0), new Team("Australia", 0)));
        scoreBoard.startNewMatch(match1);
        scoreBoard.startNewMatch(match2);
        scoreBoard.startNewMatch(match3);
        scoreBoard.startNewMatch(match4);
        scoreBoard.startNewMatch(match5);
        match1.getAwayTeam().setScore(5);
        scoreBoard.updateScore(match1);
        match2.getHomeTeam().setScore(10);
        match2.getAwayTeam().setScore(2);
        scoreBoard.updateScore(match2);
        match3.getHomeTeam().setScore(2);
        match3.getAwayTeam().setScore(2);
        scoreBoard.updateScore(match3);
        match4.getHomeTeam().setScore(6);
        match4.getAwayTeam().setScore(6);
        scoreBoard.updateScore(match4);
        match5.getHomeTeam().setScore(3);
        match5.getAwayTeam().setScore(1);
        scoreBoard.updateScore(match5);
        assertThat(scoreBoard.getSummary()).containsExactlyElementsOf(List.of(match4, match2, match1, match5, match3));
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWithInitialScoresMoreThan0() {
        var match = scoreBoard.startNewMatch(createMatch(new Team("Mexico", 1), new Team("Canada", 0)));
        assertThrowsExactly(IllegalArgumentException.class, scoreBoard.startNewMatch(match), "The match should starts only with 0 scores.");
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWithInitialScoresLessThan0() {
        var match = scoreBoard.startNewMatch(createMatch(new Team("Mexico", -1), new Team("Canada", 0)));
        assertThrowsExactly(IllegalArgumentException.class, scoreBoard.startNewMatch(match), "The match should starts only with 0 scores.");
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWhenOneOfTeamIsNull() {
        var match = scoreBoard.startNewMatch(createMatch(null, new Team("Canada", 0)));
        assertThrowsExactly(IllegalArgumentException.class, scoreBoard.startNewMatch(match));
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWhenOneOfTeamNameNull() {
        var match = scoreBoard.startNewMatch(createMatch(new Team(null, 0), new Team("Canada", 0)));
        assertThrowsExactly(IllegalArgumentException.class, scoreBoard.startNewMatch(match));
    }

    @Test
    public void shouldThrowExceptionOnAddNewMatchWhenOneOfTeamNameEmptyString() {
        var match = scoreBoard.startNewMatch(createMatch(new Team("", 0), new Team("Canada", 0)));
        assertThrowsExactly(IllegalArgumentException.class, scoreBoard.startNewMatch(match));
    }

    private Match createMatch(Team homeTeam, Team awayTeam) {
        return new Match(homeTeam, awayTeam);
    }
}
