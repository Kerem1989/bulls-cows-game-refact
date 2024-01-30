package se.kerem.moo.logic;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.io.GameIO;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.model.Player;
import java.sql.SQLException;

public class Controller {
    GuessingGame guessingGame;
    PlayerDAO playerDAO;
    ResultDAO resultDAO;
    GeneralIO generalIo;
    GameIO gameIo;

    public Controller(GuessingGame guessingGame, PlayerDAO playerDAO, ResultDAO resultDAO, GeneralIO generalIo, GameIO gameIo) {
        this.guessingGame = guessingGame;
        this.playerDAO = playerDAO;
        this.resultDAO = resultDAO;
        this.generalIo = generalIo;
        this.gameIo = gameIo;
    }

    public void run() throws SQLException, InterruptedException {
        String name = gameIo.promptLogin(generalIo);
        Player gotName = playerDAO.getByName(name);
        playerDAO.existByName(name, generalIo);
        boolean answer = true;
        do {
            String goal = guessingGame.makeGoal();
            generalIo.clear();
            gameIo.promptIntroMessage(goal, generalIo);
            String guess = gameIo.inputGuess(generalIo);
            String storeFeedback = guessingGame.generateFeedback(goal, guess);
            generalIo.addString(storeFeedback + "\n");
            int nGuess = guessingGame.continueGameRound(generalIo, storeFeedback, guess, guessingGame, goal);
            resultDAO.insertUserChoice(nGuess, gotName.getId());
            guessingGame.showTopTen(playerDAO, resultDAO, generalIo);
            answer = gameIo.displayGuessAndContGame(nGuess, generalIo);

        } while (answer);
        generalIo.exit();
    }
}

