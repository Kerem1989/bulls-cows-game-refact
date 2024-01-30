package se.kerem.moo.logic;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.io.IO;
import se.kerem.moo.model.Player;

import java.sql.SQLException;

public class Controller {
    GuessingGame guessingGame;
    PlayerDAO playerDAO;
    ResultDAO resultDAO;
    IO io;

    public Controller(GuessingGame guessingGame, PlayerDAO playerDAO, ResultDAO resultDAO, IO io) {
        this.guessingGame = guessingGame;
        this.playerDAO = playerDAO;
        this.resultDAO = resultDAO;
        this.io = io;
    }

    public void run() throws SQLException, InterruptedException {
        String name = io.promptLogin(io);
        Player gotName = playerDAO.getByName(name);
        playerDAO.existByName(name, io);
        boolean answer = true;
        do {
            String goal = guessingGame.makeGoal();
            io.clear();
            io.promptIntroMessage(goal, io);
            String guess = io.inputGuess(io);
            String storeFeedback = guessingGame.generateFeedback(goal, guess);
            io.addString(storeFeedback + "\n");
            int nGuess = guessingGame.continueGameRound(io, storeFeedback, guess, guessingGame, goal);
            resultDAO.insertUserChoice(nGuess, gotName.getId());
            guessingGame.showTopTen(playerDAO, resultDAO, io);
            answer = io.displayGuessAndContGame(nGuess, io);

        } while (answer);
        io.exit();
    }
}

