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
        String guess = generalIo.getString();
        generalIo.addString(guess + "\n");
        return guess;
    }

    public boolean displayGuessAndContGame(int nGuess, GeneralIO generalIo) {
        return generalIo.yesNo("Correct, it took " + nGuess
                + " guesses\nContinue?");
    }

}
