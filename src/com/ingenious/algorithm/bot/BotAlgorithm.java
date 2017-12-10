package com.ingenious.algorithm.bot;

import com.ingenious.algorithm.support.BaseMovesAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.ArrayList;

abstract public class BotAlgorithm {

    public Move generateMove(Game game) {
        System.out.println("-------------------------------------------------------");
        System.out.println("Executing Algorithm: " + this.getClass().getSimpleName());
        System.out.println("-------------------------------------------------------");
        long startTime = System.nanoTime();
        Move move = execute(game);
        long endTime = System.nanoTime();
        double timeDifference = ((double) endTime - (double) startTime) / 1000000;
        System.out.println("-------------------------------------------------------");
        System.out.println("Took " + timeDifference + " ms to execute algorithm " + this.getClass().getSimpleName());
        System.out.println("-------------------------------------------------------");
        System.out.println();
        return move;
    }


    public Move generateMove(Game game, boolean output) {
        return execute(game);
    }

    public ArrayList<Move> generateBaseMoves(Game game, boolean heuristics) {
        BaseMovesAlgorithm movesFactory = new BaseMovesAlgorithm(game, heuristics);
        return movesFactory.generate();
    }

    protected abstract Move execute(Game game);

}
