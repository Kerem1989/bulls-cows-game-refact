/*
 * Class: Result
 * Description: A model class for Result.
 * Created by: Kerem Bjälvenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-01-25
 */
package se.kerem.moo.model;

public class Result {
    private int id;
    private String name;
    private int playerId;

    public Result(int id, String name, int playerid) {
        this.id = id;
        this.name = name;
        this.playerId = playerid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " playerId: " + playerId;
    }
}
