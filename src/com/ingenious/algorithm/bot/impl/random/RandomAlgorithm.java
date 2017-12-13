package com.ingenious.algorithm.bot.impl.random;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomAlgorithm extends BotAlgorithm {
    public Move execute(Game game) {

        ArrayList<Move> moves = generateAllBaseMoves(game);

        if (moves.size() != 0) {
            int i = ThreadLocalRandom.current().nextInt(0, moves.size());
            return moves.get(i);
        }

        return null;
    }
}
