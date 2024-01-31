// Kerem Tazedal, kerem.tazedal@iths.se
package se.kerem.moo;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.PlayerDAOImpl;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.database.ResultDAOImpl;
import se.kerem.moo.io.GameIO;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.io.InMessagesGame;
import se.kerem.moo.io.Window;
import se.kerem.moo.logic.BullsCowsGameLogic;
import se.kerem.moo.logic.GameController;
import se.kerem.moo.logic.GuessingGame;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        GeneralIO generalIo = new Window("Moo");
        GameIO gameIo = new InMessagesGame();
        GuessingGame bullsCowsGame = new BullsCowsGameLogic();
        PlayerDAO playerDAO = new PlayerDAOImpl();
        ResultDAO resultDAO = new ResultDAOImpl();
        GameController gameController = new GameController(bullsCowsGame, playerDAO, resultDAO, generalIo, gameIo);
        gameController.run();
    }
}