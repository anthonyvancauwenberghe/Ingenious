package com.ingenious.model.players.impl;

import com.ingenious.algorithm.Algorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.model.Rack;
import com.ingenious.model.players.Player;

public class Bot extends Player {

    private Algorithm algorithm;

    public Bot(Rack rack, Algorithm algorithm) {
        super("Bot",rack, false);
        this.algorithm = algorithm;
    }

    public Move getMove(Game game) {
        return algorithm.execute(game);
    }
}
