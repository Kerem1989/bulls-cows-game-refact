package se.kerem.moo.logic;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.io.IO;
import se.kerem.moo.model.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BullsCowsGameLogic implements GuessingGame {
    public String makeGoal() {
        String goal = "";
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * 10);
            String randomDigit = "" + random;
            while (goal.contains(randomDigit)) {
                random = (int) (Math.random() * 10);
                randomDigit = "" + random;
            }
            goal = goal + randomDigit;
        }
        return goal;
    }

    public String generateFeedback(String goal, String guess) {
        guess += "    ";
        int cows = 0, bulls = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (goal.charAt(i) == guess.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        String result = "";
        for (int i = 0; i < bulls; i++) {
            result = result + "B";
        }
        result = result + ",";
        for (int i = 0; i < cows; i++) {
            result = result + "C";
        }
        return result;

    }

    public void showTopTen(PlayerDAO pdao, ResultDAO rdao, IO io) throws SQLException {
        ArrayList<Player> topList = new ArrayList<>();
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
            for (Player templist : topList) {
                System.out.println(templist);
            }
        }
        // refact
        io.addString("Top Ten List\n Player Average\n");
        int pos = 1;
        topList.sort((p1, p2) -> (Double.compare(p1.getAverage(), p2.getAverage())));
        for (Player p : topList) {
            io.addString(String.format("%3d %-10s%5.2f%n", pos, p.getName(), p.getAverage()));
            if (pos++ == 10) break;
        }
    }

    public int continueGameRound(IO io, String storeFeedback, String guess, GuessingGame guessingGame, String goal){
        int nGuess = 1;
        while (!storeFeedback.equals("BBBB,")) {
            nGuess++;
            guess = io.getString();
            io.addString(guess +": ");
            storeFeedback = guessingGame.generateFeedback(goal, guess);
            io.addString(storeFeedback + "\n");
        }
        return nGuess;
    }
}
