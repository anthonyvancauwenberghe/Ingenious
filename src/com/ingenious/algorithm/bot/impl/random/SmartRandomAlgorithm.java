package com.ingenious.algorithm.bot.impl.random;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SmartRandomAlgorithm extends BotAlgorithm {
    public Move execute(Game game) {

        List<Move> moves = smartBaseMoveGenerator(game);

        if (moves.size() != 0) {
            int i = ThreadLocalRandom.current().nextInt(0, moves.size());
            return moves.get(i);
        }

        return null;
    }
}
