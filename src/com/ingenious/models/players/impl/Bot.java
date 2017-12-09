package com.ingenious.models.players.impl;

import com.ingenious.algorithms.Algorithm;
import com.ingenious.algorithms.Executeable;
import com.ingenious.engine.Game;
import com.ingenious.models.Move;
import com.ingenious.models.Rack;
import com.ingenious.models.players.Player;

public class Bot extends Player {

    private Algorithm algorithm;

    public Bot(Rack rack, Executeable algorithm) {
        super("Bot",rack, false);
    }

    public Move executeMove(Game game) {
        return algorithm.execute(game);
    }
}
