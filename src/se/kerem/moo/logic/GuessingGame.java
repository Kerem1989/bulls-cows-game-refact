package se.kerem.moo.logic;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.io.IO;
import java.sql.SQLException;

public interface GuessingGame {
    String generateFeedback(String goal, String guess);
    String makeGoal();
    void showTopTen(PlayerDAO pdao, ResultDAO rdao, IO io) throws SQLException;
    public int continueGameRound(IO io, String storeFeedback, String guess, GuessingGame guessingGame, String goal);
}
