package se.kerem.moo.io;

public interface GeneralIO {
    boolean yesNo(String prompt);
    String getString();
    void addString(String s);
    void clear();
    void exit();
}
