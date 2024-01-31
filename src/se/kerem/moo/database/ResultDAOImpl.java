package se.kerem.moo.database;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.model.Player;
import java.sql.*;
import java.util.ArrayList;

public class ResultDAOImpl implements ResultDAO {
    private Connection connection;
    private PreparedStatement insertResultPS, selectResultByIDPS;

    public ResultDAOImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/moo", "root", "Juventus89.");
            insertResultPS = connection.prepareStatement("insert into results(result, playerid) values (?, ?)");
            selectResultByIDPS = connection.prepareStatement("select * from results where playerid = ?");
        } catch (SQLException e) {
            throw new RuntimeException("Connection to database failed:" + e);
        }
    }

    @Override
    public void insertUserChoice(int nGuess, int id){
        try {
            insertResultPS.setInt(1,  nGuess);
            insertResultPS.setInt(2,id);
            insertResultPS.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert result data:" + e);
        }
    }

    public ResultSet selectResultByPlayerId(int id){
        try {
            selectResultByIDPS.setInt(1, id);
            return selectResultByIDPS.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrive result data:" + e);
        }
    }

    public ArrayList<Player> storeTopTen(PlayerDAO pdao, ResultDAO rdao, GeneralIO generalIo){
        ArrayList<Player> topList = new ArrayList<>();
        try {
            ResultSet rs = pdao.extractPlayerFromResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                ResultSet rs2 = rdao.selectResultByPlayerId(id);
                int nGames = 0;
                int totalGuesses = 0;
                while (rs2.next()) {
                    nGames++;
                    totalGuesses += rs2.getInt("result");
                }
                if (nGames > 0) {
                    topList.add(new Player(name, (double) totalGuesses / nGames));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to store result data:" + e);
        }
        return topList;
    }

    public void displayTopTen(ArrayList <Player> topList, GeneralIO generalIo){
        generalIo.addString("Top Ten List\n Player Average\n");
        int pos = 1;
        topList.sort((p1,p2)->(Double.compare(p1.getAverage(), p2.getAverage())));
        for (Player p : topList) {
            generalIo.addString(String.format("%3d %-10s%5.2f%n", pos, p.getName(), p.getAverage()));
            if (pos++ == 10) break;
        }
    }
}


