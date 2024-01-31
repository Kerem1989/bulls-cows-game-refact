package se.kerem.moo.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kerem.moo.database.PlayerDAO;
import se.kerem.moo.database.PlayerDAOImpl;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.io.Window;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDAOImplTest {
    PlayerDAO playerDAO;
    GeneralIO generalIO;

    @BeforeEach
    void setUp() {
        playerDAO = new PlayerDAOImpl();
        generalIO = new Window("Moo");
    }

    @Test
    void getByName() {
        assertEquals(null, playerDAO.getByName("Wrong name"));
        assertEquals("id: 1 name: Kerem average: 0,0", playerDAO.getByName("Kerem").toString());
    }
    @Test
    void getAll() {
        assertNotEquals(null, playerDAO.getAll());
    }

    @Test
    void extractPlayerFromResultSet() {
        assertNotEquals(null, playerDAO.extractPlayerFromResultSet());
    }
}