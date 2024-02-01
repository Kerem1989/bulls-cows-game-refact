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
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.io.Window;
import se.kerem.moo.model.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDAOImplTest {
    PlayerDAO playerDAO;
    GeneralIO generalIO;
    MockDB mockDB;

    @BeforeEach
    void setUp() {
        mockDB = new MockDB();
        playerDAO = mockDB;
        generalIO = new Window("Moo");

    }

    @Test
    void getByNameMockDB() {
        playerDAO.getByName("Kerem");
        playerDAO.getByName("Kerem");
        playerDAO.getByName("Kerem");
        assertEquals(3, mockDB.nCallsGetByName);
        assertNotEquals(4, mockDB.nCallsGetByName);

    }

    @Test
    void getByNameRealDB() {
        playerDAO = new PlayerDAOImpl();
        assertEquals(null, playerDAO.getByName("Wrong name"));
        assertEquals("id: 1 name: Kerem average: 0,0", playerDAO.getByName("Kerem").toString());
        assertNotEquals("id: 1 name: Kerem average: 0,0", playerDAO.getByName("Kere"));
    }

    @Test
    void getAll() {
        playerDAO = new PlayerDAOImpl();
        assertEquals(4, playerDAO.getAll().size());
        assertNotEquals(5, playerDAO.getAll().size());

    }

    @Test
    void extractPlayerFromResultSet() throws SQLException {
        playerDAO = new PlayerDAOImpl();
        ResultSet rs = playerDAO.extractPlayerFromResultSet();
        if (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            assertEquals(1, id);
            assertEquals("Kerem", name);
            int row = rs.getRow();
            assertNotEquals(2, row);
        }


    }

    class MockDB implements PlayerDAO{

        int nCallsGetByName;

        @Override
        public Player getByName(String name) {
            nCallsGetByName++;
            return null;
        }

        @Override
        public List<Player> getAll() {
            return null;
        }

        @Override
        public ResultSet extractPlayerFromResultSet() {
            return null;
        }

        @Override
        public void existByName(String name, GeneralIO generalIo) {

        }
    }

}

