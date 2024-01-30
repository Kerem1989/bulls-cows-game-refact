package se.kerem.moo.io;

public interface GameIO {
    void promptIntroMessage(String goal, GeneralIO generalIo);
    String promptLogin(GeneralIO generalIo);
    String inputGuess(GeneralIO generalIo);
    boolean displayGuessAndContGame(int nGuess, GeneralIO generalIo);
}
