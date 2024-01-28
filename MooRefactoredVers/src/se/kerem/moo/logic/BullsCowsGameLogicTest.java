package se.kerem.moo.logic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kerem.moo.io.IO;
import se.kerem.moo.io.WindowIO;

import static org.junit.jupiter.api.Assertions.*;

class BullsCowsGameLogicTest {

    BullsCowsGameLogic bullsCowsGameLogic;
    IO io = new WindowIO("Moo");

    @BeforeEach
    void setUp() {
        bullsCowsGameLogic = new BullsCowsGameLogic();
    }

    @Test
    void makeGoal() {
        String result = bullsCowsGameLogic.makeGoal();
        assertNotEquals(3, result.length());
    }

}