/*
 * Class: InMessagesGame
 * Description: A class that implements GameIO that handles messages to user.
 * while running the game
 * Created by: Kerem Bjälvenös Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-01-25
 */
package se.kerem.moo.io;

public class InMessagesGame implements GameIO{

    public void promptIntroMessage(String goal, GeneralIO generalIo) {
        generalIo.addString("New game:\n");
        generalIo.addString("For practice, number is: " + goal + "\n");
    }

    public String promptLogin(GeneralIO generalIo){
        generalIo.addString("Enter your user name:\n");
        String name = generalIo.getString();
        return name;
    }

    public String inputGuess(GeneralIO generalIo) {
        boolean correctGuess = false;
        String guess;
        do {
            guess = generalIo.getString();
            if (guess.matches("[0-9]+")){
                generalIo.addString(guess + "\n");
                correctGuess = true;
            } else {
                generalIo.addString("Invalid input, please enter a number.\n");
            }
        } while (!correctGuess);
        return guess;
    }

    public boolean displayGuessAndContGame(int nGuess, GeneralIO generalIo) {
        return generalIo.yesNo("Correct, it took " + nGuess
                + " guesses\nContinue?");
    }

}
