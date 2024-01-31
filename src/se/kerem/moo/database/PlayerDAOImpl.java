package se.kerem.moo.database;
import se.kerem.moo.io.GeneralIO;
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
            throw new RuntimeException("Connection to database failed:" + e);
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
            throw new RuntimeException("Failed to retrieve player data by name:" + e);
        }

    }

    public void existByName(String name, GeneralIO generalIo){
        int id = 0;
        try {
            existByNamePS.setString(1, name);
            ResultSet rs = existByNamePS.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            } else {
                generalIo.addString("User not in database, please register with admin");
                Thread.sleep(5000);
                generalIo.exit();
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
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
            throw new RuntimeException("Failed to retrive player data:" + e);
        }
        return allPlayers;
    }

    public ResultSet extractPlayerFromResultSet(){
        try {
            ResultSet rs = allPS.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




