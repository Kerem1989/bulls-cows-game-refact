/*
 * Class: GeneralIO
 * Description: An interface defining general input/output operations.
 * Created by: Kerem Bjälvenös Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-01-25
 */
package se.kerem.moo.io;

public interface GeneralIO {
    boolean yesNo(String prompt);
    String getString();
    void addString(String s);
    void clear();
    void exit();
}
