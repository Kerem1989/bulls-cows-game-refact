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
