/*
 * Class: PlayerDAOImplTest
 * Description: A testclass for testing database interactions.
 * Created by: Kerem Bjälvenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-01
 */
package se.kerem.moo.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.PlayerDAOImpl;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.database.ResultDAOImpl;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.io.Window;
import se.kerem.moo.model.Player;
import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResultDAOImplTest {
    ResultDAO resultDAO;
    GeneralIO generalIO;
    MockDB mockDB;
    PlayerDAO playerDAO;

    @BeforeEach
    void setUp() {
        mockDB = new MockDB();
        resultDAO = mockDB;
        playerDAO = new PlayerDAOImpl();
        generalIO = new Window("Moo");
    }

    @Test
    void insertUserChoiceMethod() {
        resultDAO.insertUserChoice(5, 1);
        resultDAO.insertUserChoice(2, 2);
        resultDAO.insertUserChoice(4, 3);
        resultDAO.insertUserChoice(4, 6);
        assertEquals(4, mockDB.nCallsUserChoice);
        assertNotEquals(5, mockDB.nCallsUserChoice);
    }

    @Test
    void insertUserChoiceMethodRealDB(){
        resultDAO = new ResultDAOImpl();
        assertThrows(RuntimeException.class, () -> resultDAO.insertUserChoice(5, 5));
    }

    @Test
    void selectResultByPlayerId() {
        resultDAO.selectResultByPlayerId(1);
        resultDAO.selectResultByPlayerId(2);
        assertEquals(2, mockDB.nCallsSelectResultByPlayerId);
        assertNotEquals(3,  mockDB.nCallsSelectResultByPlayerId );
    }
    class MockDB implements ResultDAO {
        int nCallsUserChoice;
        int nCallsSelectResultByPlayerId;

        @Override
        public int insertUserChoice(int nGuess, int id) {
            nCallsUserChoice++;
            return nGuess;
        }

        @Override
        public ResultSet selectResultByPlayerId(int id) {
            nCallsSelectResultByPlayerId++;
            return null;
        }

        @Override
        public ArrayList<Player> storeTopTen(PlayerDAO pdao, ResultDAO rdao, GeneralIO generalIo) {
            return null;
        }

        @Override
        public void displayTopTen(ArrayList<Player> topList, GeneralIO generalIo) {

        }
    }
}