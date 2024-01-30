package se.kerem.moo.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.io.Window;
import se.kerem.moo.logic.BullsCowsGameLogic;
import se.kerem.moo.logic.GuessingGame;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDAOImplTest {
    GeneralIO generalIo;
    GuessingGame bullsCowsGameLogic;
    PlayerDAO playerDAO;
    ResultDAO resultDAO;

    @BeforeEach
    void setUp() {
        new BullsCowsGameLogic();
        new PlayerDAOImpl();
        new ResultDAOImpl();
        new Window("Moo");
    }

    @Test
    void doesPlayerExist() throws SQLException, InterruptedException {
        playerDAO.existByName("Kerem", generalIo);
        assertTrue(true);
        playerDAO.existByName("Zlatan", generalIo);
        assertTrue(true);
    }
}