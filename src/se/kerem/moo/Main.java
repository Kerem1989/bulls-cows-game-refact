// Kerem Tazedal, kerem.tazedal@iths.se
package se.kerem.moo;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.PlayerDAOImpl;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.database.ResultDAOImpl;
import se.kerem.moo.io.IO;
import se.kerem.moo.io.WindowIO;
import se.kerem.moo.logic.BullsCowsGameLogic;
import se.kerem.moo.logic.Controller;
import se.kerem.moo.logic.GuessingGame;
import se.kerem.moo.logic.NumberGuessingGame;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        IO io = new WindowIO("Moo");
        GuessingGame bullsCowsGame = new BullsCowsGameLogic();
        PlayerDAO playerDAO = new PlayerDAOImpl();
        ResultDAO resultDAO = new ResultDAOImpl();
        Controller controller = new Controller(bullsCowsGame, playerDAO, resultDAO, io);
        controller.run();
    }
}