package com.example.aircraftwar2024.record;

import java.io.Serializable;

public class UserRecord implements Serializable {
    private int rank;
    private String name;
    private int score;
    private String date;

    public int getScore() {
        return score;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getRank() {
        return rank;
    }

    public UserRecord(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

}
