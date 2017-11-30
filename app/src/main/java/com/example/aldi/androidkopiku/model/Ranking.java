package com.example.aldi.androidkopiku.model;

/**
 * Created by aldi on 11/27/17.
 */

public class Ranking {
    private String Name;
    private long score;

    public Ranking() {
    }

    public Ranking(String name, long score) {
        Name = name;
        this.score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
