package se.kerem.moo.database;
import se.kerem.moo.io.IO;
import se.kerem.moo.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {
    private Connection connection;
    private PreparedStatement allPS, byNamePS, existByNamePS;

    public PlayerDAOImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/moo", "root", "Juventus89.");
            allPS = connection.prepareStatement("select * from players");
            byNamePS = connection.prepareStatement("select * from players where name = ?");
            existByNamePS = connection.prepareStatement("select id,name from players where name = ?");
        } catch (SQLException e) {
            throw new RuntimeException("fail in se.kerem.moo.database.PlayerDAO ctor:" + e);
        }
    }


    public Player getByName(String name) {
        try {
            byNamePS.setString(1, name);
            ResultSet rs = byNamePS.executeQuery();
            if (rs.next()) {
                return new Player(rs.getInt("id"), rs.getString("name"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("EmployeeDAO.byid fail:" + e);
        }

    }

    public String existByName(String name, IO io) throws SQLException, InterruptedException {
        int id = 0;
        existByNamePS.setString(1, name);
        ResultSet rs = existByNamePS.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        } else {
            io.addString("User not in database, please register with admin");
            Thread.sleep(5000);
            io.exit();
        }
        return null;
    }

    @Override
    public List<Player> getAll() {
        ArrayList<Player> allPlayers = new ArrayList<>();
        try {
            ResultSet rs = allPS.executeQuery();
            while(rs.next()){
                allPlayers.add(new Player(rs.getInt("id"),rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("EmployeeDAO.getAll fail:" + e);
        }
        return allPlayers;
    }

    public ResultSet extractPlayerFromResultSet() throws SQLException {
        ResultSet rs = allPS.executeQuery();
        return rs;
    }
}




