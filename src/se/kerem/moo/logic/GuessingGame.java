package se.kerem.moo.logic;
import se.kerem.moo.io.GeneralIO;

public interface GuessingGame {
    String returnResultFromGuess(String goal, String guess);
    String generateRandomNumbers();
    public int continueGameRound(GeneralIO generalIo, String storeFeedback, String guess, GuessingGame guessingGame, String goal);
}
