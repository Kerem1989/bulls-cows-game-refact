/*
 * Class: PlayerDAOImplTest
 * Description: A testclass for testing database interactions.
 * Created by: Kerem Bjälvenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-01-25
 */
package se.kerem.moo.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kerem.moo.database.ResultDAO;
import se.kerem.moo.database.ResultDAOImpl;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.io.Window;

import static org.junit.jupiter.api.Assertions.*;

class ResultDAOImplTest {
    ResultDAO resultDAO;
    GeneralIO generalIO;

    @BeforeEach
    void setUp() {
        resultDAO = new ResultDAOImpl();
        generalIO = new Window("Moo");
    }

    @Test
    void insertUserChoice() {
        resultDAO.insertUserChoice(5, 1);
        assertEquals(5, 5);
    }

    @Test
    void selectResultByPlayerId() {
    }

    @Test
    void storeTopTen() {
    }

    @Test
    void displayTopTen() {
    }
}