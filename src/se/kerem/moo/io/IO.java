package se.kerem.moo.io;

public interface IO {
    boolean yesNo(String prompt);
    String getString();
    void addString(String s);
    void clear();
    void exit();
    void promptIntroMessage(String goal, IO io);
    String promptLogin(IO io);
    String inputGuess(IO io);
    boolean displayGuessAndContGame(int nGuess, IO io);
}
