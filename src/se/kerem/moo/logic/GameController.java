/*
 * Class: GameController
 * Description: A class that connects the various classes of the program.
 * Created by: Kerem Bjälvenös Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-01-25
 */
package se.kerem.moo.logic;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.io.GameIO;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.model.Player;
import java.util.ArrayList;

public class GameController {
    GuessingGame guessingGame;
    PlayerDAO playerDAO;
    ResultDAO resultDAO;
    GeneralIO generalIo;
    GameIO gameIo;

    public GameController(GuessingGame guessingGame, PlayerDAO playerDAO, ResultDAO resultDAO, GeneralIO generalIo, GameIO gameIo) {
        this.guessingGame = guessingGame;
        this.playerDAO = playerDAO;
        this.resultDAO = resultDAO;
        this.generalIo = generalIo;
        this.gameIo = gameIo;
    }

    public void run()  {
        String name = gameIo.promptLogin(generalIo);
        Player gotName = playerDAO.getByName(name);
        playerDAO.existByName(name, generalIo);
        boolean answer = true;
        do {
            String goal = guessingGame.generateRandomNumbers();
            generalIo.clear();
            gameIo.promptIntroMessage(goal, generalIo);
            String guess = gameIo.inputGuess(generalIo);
            String storeFeedback = guessingGame.returnResultFromGuess(goal, guess);
            generalIo.addString(storeFeedback + "\n");
            int nGuess = guessingGame.continueGameRound(generalIo, storeFeedback, guess, guessingGame, goal);
            resultDAO.insertUserChoice(nGuess, gotName.getId());
            ArrayList storagePlayer = resultDAO.storeTopTen(playerDAO, resultDAO, generalIo);
            resultDAO.displayTopTen(storagePlayer, generalIo);
            answer = gameIo.displayGuessAndContGame(nGuess, generalIo);

        } while (answer);
        generalIo.exit();
    }
}

