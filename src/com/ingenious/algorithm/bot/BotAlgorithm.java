package com.ingenious.algorithm.bot;

import com.ingenious.algorithm.support.BaseMovesAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.ArrayList;

abstract public class BotAlgorithm {

    public Move generateMove(Game game, boolean output) {
        double startTime, endTime, timeDifference;

        startTime = System.nanoTime();
        if (output) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Executing Algorithm: " + this.getClass().getSimpleName());
            System.out.println("-------------------------------------------------------");
            startTime = System.nanoTime();
        }

        Move move = execute(game);
        endTime = System.nanoTime();
        timeDifference = (endTime - startTime) / 1000000;
        if (output) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Took " + timeDifference + " ms to execute algorithm " + this.getClass().getSimpleName());
            System.out.println("-------------------------------------------------------");
            System.out.println();
        }

        return move;
    }

    public ArrayList<Move> generateBaseMoves(Game game, boolean heuristics) {
        BaseMovesAlgorithm movesFactory = new BaseMovesAlgorithm(game, heuristics);
        return new ArrayList<>(movesFactory.generate());
    }

    protected abstract Move execute(Game game);

}
