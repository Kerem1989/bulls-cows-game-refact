package se.kerem.moo.database;
import se.kerem.moo.io.GeneralIO;
import se.kerem.moo.model.Player;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface ResultDAO {
    void insertUserChoice (int nGuess, int id);
    ResultSet selectResultByPlayerId (int id);
    ArrayList<Player> storeTopTen(PlayerDAO pdao, ResultDAO rdao, GeneralIO generalIo);
    void displayTopTen(ArrayList <Player> topList, GeneralIO generalIo);
}
