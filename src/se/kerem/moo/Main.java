package se.kerem.moo;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.PlayerDAOImpl;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.database.ResultDAOImpl;
import se.kerem.moo.io.IO;
import se.kerem.moo.io.WindowIO;
import se.kerem.moo.logic.BullsCowsGameLogic;
import se.kerem.moo.logic.Controller;
import se.kerem.moo.logic.PuzzleGame;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
        IO io = new WindowIO("Moo");
        PuzzleGame bullsCowsGameLogic = new BullsCowsGameLogic();
        PlayerDAO playerDAO = new PlayerDAOImpl();
        ResultDAO resultDAO = new ResultDAOImpl();
        Controller controller = new Controller(bullsCowsGameLogic, playerDAO, resultDAO, io);
        controller.run();
    }
}