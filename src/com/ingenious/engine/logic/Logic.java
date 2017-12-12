package com.ingenious.engine.logic;

import com.ingenious.engine.Game;

abstract public class Logic<T> {
    private Game game;

    public Logic(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public abstract T execute();
}
