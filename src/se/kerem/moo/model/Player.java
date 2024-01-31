package se.kerem.moo.model;

public class Player {
    private int id;
    private String name;
    private double average;

    public Player(String name, double average) {
        this.name = name;
        this.average = average;
    }
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
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

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", average=" + average +
                '}';
    }
}
