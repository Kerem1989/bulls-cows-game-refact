package se.kerem.moo.logic;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.PlayerDAOImpl;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.io.IO;
import se.kerem.moo.model.Player;

import java.sql.SQLException;

public class Controller {
    PuzzleGame puzzleGame;
    PlayerDAO playerDAO;
    ResultDAO resultDAO;
    IO io;

    public Controller(PuzzleGame puzzleGame, PlayerDAO playerDAO, ResultDAO resultDAO, IO io) {
        this.puzzleGame = puzzleGame;
        this.playerDAO = playerDAO;
        this.resultDAO = resultDAO;
        this.io = io;
    }

    public void run() throws SQLException, InterruptedException {
        String name = puzzleGame.promptLogin(io);
        Player gotName = playerDAO.getByName(name);
        playerDAO.existByName(name, io);
        boolean answer = true;
        do {
            String goal = puzzleGame.makeGoal();
            io.clear();
            puzzleGame.promptIntroMessage(goal, io);
            String guess = puzzleGame.inputGuess(io);
            String storeFeedback = puzzleGame.generateFeedback(goal, guess);
            io.addString(storeFeedback + "\n");
            int nGuess = puzzleGame.continueGameRound(io, storeFeedback, guess, puzzleGame, goal);
            resultDAO.insertUserChoice(nGuess, gotName.getId());
            puzzleGame.showTopTen(playerDAO, resultDAO, io);
            answer = puzzleGame.displayGuessAndContGame(nGuess, io);

        } while (answer);
        io.exit();
    }
}

