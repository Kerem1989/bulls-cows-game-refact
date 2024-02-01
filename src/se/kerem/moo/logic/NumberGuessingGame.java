/*
 * Class: NumberGuessingGame
 * Description: A AI generated guessing game for testing purposes.
 * Created by: Chat GPT
 * Email: kerem.tazedal@iths.se
 * Date: 2024-01-25
 */
package se.kerem.moo.logic;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.model.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NumberGuessingGame implements GuessingGame {
    private int targetNumber;
    private int nGuess;

    public NumberGuessingGame() {
        this.targetNumber = generateTargetNumber();
        this.nGuess = 0;
    }

    private int generateTargetNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    @Override
    public String returnResultFromGuess(String goal, String guess) {
        int userGuess = Integer.parseInt(guess);
        nGuess++;

        if (userGuess == targetNumber) {
            return "Correct! It took " + nGuess + " guesses.";
        } else if (userGuess < targetNumber) {
            return "Too low. Try again.";
        } else {
            return "Too high. Try again.";
        }
    }

    @Override
    public String generateRandomNumbers() {
        return Integer.toString(targetNumber);
    }

    public void showTopTen(PlayerDAO pdao, ResultDAO rdao, GeneralIO generalIo) throws SQLException {
        List<Player> topList = new ArrayList<>();
        ResultSet rs = pdao.extractPlayerFromResultSet();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            ResultSet rs2 = rdao.selectResultByPlayerId(id);
            int nGames = 0;
            int totalGuesses = 0;
            while (rs2.next()) {
                nGames++;
                totalGuesses += rs2.getInt("result");
            }
            if (nGames > 0) {
                topList.add(new Player(name, (double) totalGuesses / nGames));
            }
        }
        generalIo.addString("Top Ten List\n    Player     Average\n");
        int pos = 1;
        topList.sort((p1, p2) -> Double.compare(p1.getAverage(), p2.getAverage()));
        for (Player p : topList) {
            generalIo.addString(String.format("%3d %-10s%5.2f%n", pos, p.getName(), p.getAverage()));
            if (pos++ == 10) break;
        }
    }

    public int continueGameRound(GeneralIO generalIo, String storeFeedback, String guess, GuessingGame guessingGame, String goal) {
        nGuess = 0;
        do {
            nGuess++;
            guess = generalIo.getString();
            generalIo.addString(guess + ": ");
            storeFeedback = guessingGame.returnResultFromGuess(goal, guess);
            generalIo.addString(storeFeedback + "\n");
        } while (!storeFeedback.equals("Correct! It took " + nGuess + " guesses."));

        return nGuess;
    }

}
