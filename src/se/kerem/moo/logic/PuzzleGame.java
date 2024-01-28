package se.kerem.moo.logic;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.io.IO;
import java.sql.SQLException;

public interface PuzzleGame {
    String generateFeedback(String goal, String guess);
    String makeGoal();
    void showTopTen(PlayerDAO pdao, ResultDAO rdao, IO io) throws SQLException;
    void promptIntroMessage(String goal, IO io);
    String promptLogin(IO io);
    String inputGuess(IO io);
    boolean displayGuessAndContGame(int nGuess, IO io);
    public int continueGameRound(IO io, String storeFeedback, String guess, PuzzleGame puzzleGame, String goal);
}
