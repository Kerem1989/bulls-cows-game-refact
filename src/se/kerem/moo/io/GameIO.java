/*
 * Class: GameIO
 * Description: A interface for contructing a GameIO.
 * Created by: Kerem Bjälvenös Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-01-25
 */
package se.kerem.moo.io;

public interface GameIO {
    void promptIntroMessage(String goal, GeneralIO generalIo);
    String promptLogin(GeneralIO generalIo);
    String inputGuess(GeneralIO generalIo);
    boolean displayGuessAndContGame(int nGuess, GeneralIO generalIo);
}
