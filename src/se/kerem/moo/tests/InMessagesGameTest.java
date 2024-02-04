package se.kerem.moo.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kerem.moo.io.GameIO;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.io.InMessagesGame;
import se.kerem.moo.io.Window;
import static org.junit.jupiter.api.Assertions.*;

class InMessagesGameTest {

    GameIO io;
    GeneralIO ioGeneral;

    @BeforeEach
    void setUp() {
        io = new InMessagesGame();
        ioGeneral = new Window("Moo");

    }

    @Test
    void displayGuessAndContGame() {
        io.displayGuessAndContGame(1, ioGeneral);
        assertTrue(ioGeneral.yesNo("Correct, it took 1 guesses\nContinue?"));
        assertFalse(ioGeneral.yesNo("Correct, it took 2 guesses\nContinue?"));
    }
}