package se.kerem.moo.database;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.model.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public interface PlayerDAO {
    Player getByName (String name);
    List<Player> getAll();
    ResultSet extractPlayerFromResultSet() throws SQLException;
    String existByName(String name, GeneralIO generalIo) throws SQLException, InterruptedException;
}
