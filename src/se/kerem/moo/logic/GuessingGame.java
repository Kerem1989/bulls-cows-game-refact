/*
 * Class: GuessingGame
 * Description: A interface for constructing a guessing game.
 * Created by: Kerem Bjälvenös Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-01-25
 */
package se.kerem.moo.logic;
import se.kerem.moo.io.GeneralIO;

public interface GuessingGame {
    String returnResultFromGuess(String goal, String guess);
    String generateRandomNumbers();
    public int continueGameRound(GeneralIO generalIo, String storeFeedback, String guess, GuessingGame guessingGame, String goal);
}
