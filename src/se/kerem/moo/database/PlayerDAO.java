/*
 * Class: PlayerDAO
 * Description: A interface for PlayerDAO
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-01-25
 */
package se.kerem.moo.database;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.model.Player;
import java.sql.ResultSet;
import java.util.List;
public interface PlayerDAO {
    Player getByName (String name);
    List<Player> getAll();
    ResultSet extractPlayerFromResultSet();
    void existByName(String name, GeneralIO generalIo);
}
