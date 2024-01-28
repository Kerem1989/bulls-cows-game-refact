package se.kerem.moo.database;
import java.sql.*;

public class ResultDAOImpl implements ResultDAO {
    private Connection connection;
    private PreparedStatement insertResultPS, selectResultByIDPS;

    public ResultDAOImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/moo", "root", "Juventus89.");
            insertResultPS = connection.prepareStatement("insert into results(result, playerid) values (?, ?)");
            selectResultByIDPS = connection.prepareStatement("select * from results where playerid = ?");
        } catch (SQLException e) {
            throw new RuntimeException("fail in se.kerem.moo.database.PlayerDAO ctor:" + e);
        }
    }

    @Override
    public void insertUserChoice(int nGuess, int id) throws SQLException {
        insertResultPS.setInt(1,  nGuess);
        insertResultPS.setInt(2,id);
        insertResultPS.executeUpdate();
    }

    public ResultSet selectResultByPlayerId(int id) throws SQLException {
        selectResultByIDPS.setInt(1, id);
        return selectResultByIDPS.executeQuery();
    }
}


