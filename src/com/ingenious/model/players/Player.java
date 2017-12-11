package com.ingenious.model.players;

import com.ingenious.model.Rack;
import com.ingenious.model.Score;

abstract public class Player<T> {
    private String name;

    public Score score;
    public Rack rack;

    private final boolean human;

    public Player(String name, Rack rack, boolean human) {
        this.name = name;
        this.score = new Score();
        this.rack = rack;
        this.human = human;
    }

    private Player(String name, Score score, Rack rack, boolean human) {
        this.name = name;
        this.score = score;
        this.rack = rack;
        this.human = human;
    }

    public String getName() {
        return name;
    }


    public Score getScore() {
        return score;
    }

    public Rack getRack() {
        return this.rack;
    }

    public boolean isBot() {
        return !this.human;
    }

    public boolean isHuman() {
        return this.human;
    }

    public abstract T getClone();

}
