package com.ingenious.model.players.impl;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.model.Rack;
import com.ingenious.model.players.Player;

public class Bot extends Player {

    private BotAlgorithm algorithm;

    public Bot(Rack rack, BotAlgorithm algorithm) {
        super("Bot",rack, false);
        this.algorithm = algorithm;
    }

    public Move getMove(Game game) {
        return algorithm.generateMove(game,true);
    }

    public Bot getClone() {
        return new Bot(this.getRack().getClone(), this.algorithm);
    }
}
