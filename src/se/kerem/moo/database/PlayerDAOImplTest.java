package se.kerem.moo.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kerem.moo.io.IO;
import se.kerem.moo.io.WindowIO;
import se.kerem.moo.logic.BullsCowsGameLogic;
import se.kerem.moo.logic.GuessingGame;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDAOImplTest {
    IO io;
    GuessingGame bullsCowsGameLogic;
    PlayerDAO playerDAO;
    ResultDAO resultDAO;

    @BeforeEach
    void setUp() {
        new BullsCowsGameLogic();
        new PlayerDAOImpl();
        new ResultDAOImpl();
        new WindowIO("Moo");
    }

    @Test
    void doesPlayerExist() throws SQLException, InterruptedException {
        playerDAO.existByName("Kerem", io);
        assertTrue(true);
        playerDAO.existByName("Zlatan", io);
        assertTrue(true);
    }
}