package com.ingenious.algorithm.bot;

import com.ingenious.algorithm.support.AllBaseMovesGenerator;
import com.ingenious.algorithm.support.StraightLineMoveGenerator;
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

    protected ArrayList<Move> generateBaseMoves(Game game, boolean heuristics) {
        StraightLineMoveGenerator movesFactory = new StraightLineMoveGenerator(game);
        return new ArrayList<>(movesFactory.generate());
    }

    protected ArrayList<Move> generateAllBaseMoves(Game game) {
        AllBaseMovesGenerator movesFactory = new AllBaseMovesGenerator(game);
        return new ArrayList<>(movesFactory.generate());
    }

    public abstract Move execute(Game game);

}
