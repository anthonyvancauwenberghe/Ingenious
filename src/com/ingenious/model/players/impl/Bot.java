package com.ingenious.model.players.impl;

import com.ingenious.algorithm.Algorithm;
import com.ingenious.algorithm.Executeable;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.model.Rack;
import com.ingenious.model.players.Player;

public class Bot extends Player {

    private Algorithm algorithm;

    public Bot(Rack rack, Executeable algorithm) {
        super("Bot",rack, false);
    }

    public Move executeMove(Game game) {
        return algorithm.execute(game);
    }
}
