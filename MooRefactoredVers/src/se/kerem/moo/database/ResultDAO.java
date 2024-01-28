package se.kerem.moo.database;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultDAO {
    void insertUserChoice (int nGuess, int id) throws SQLException;
    ResultSet selectResultByPlayerId (int id) throws SQLException;
}
