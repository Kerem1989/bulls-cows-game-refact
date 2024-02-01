/*
 * Class: BullsCowsGameLogicTest
 * Description: A testclass for testing the logic of the game.
 * Created by: Kerem Bjälvenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-01
 */
package se.kerem.moo.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kerem.moo.logic.BullsCowsGameLogic;
import se.kerem.moo.logic.GuessingGame;

import static org.junit.jupiter.api.Assertions.*;

class BullsCowsGameLogicTest {

    GuessingGame game;

    @BeforeEach
    void setUp() {
        game = new BullsCowsGameLogic();
    }

    @Test
    void generateRandomNumbers() {;
        String randomNumbers = game.generateRandomNumbers();
        assertEquals(4, randomNumbers.length());
        assertNotEquals(3, randomNumbers.length());
        assertTrue(randomNumbers.matches("[0-9]+"));
        assertFalse(randomNumbers.matches("^[A-Za-z]+$\n"));
    }

    @Test
    void returnResultFromGuess() {
        String result = game.returnResultFromGuess("4564", "4577");
        assertEquals("BB,C", result);
        assertNotEquals("BB, CC", result);
        String resultTwo = game.returnResultFromGuess("4564", "4563");
        assertEquals("BBB,C", resultTwo);
        String resultThree = game.returnResultFromGuess("4564", "4564");
        assertEquals("BBBB,CC", resultThree);


    }

}