package com.ingenious.algorithm.bot;

import com.ingenious.algorithm.support.nodegenerators.AllBaseMovesGenerator;
import com.ingenious.algorithm.support.nodegenerators.SmartBaseMovesGenerator;
import com.ingenious.algorithm.support.nodegenerators.StraightLineMoveGenerator;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.model.Rack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

abstract public class BotAlgorithm {

    public Move generateMove(Game game, boolean output) {
        double startTime, endTime, timeDifference;

        startTime = System.nanoTime();
        if (output) {
            System.out.println();
            System.out.println("-------------------------------------------------------");
            System.out.println("Executing Algorithm: " + this.getClass().getSimpleName());
            startTime = System.nanoTime();
            System.out.println("-------------------------------------------------------");
        }
        System.out.println();
        Move move = execute(game);
        endTime = System.nanoTime();
        timeDifference = (endTime - startTime) / 1000000;
        if (output) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Time to execute: " + timeDifference + " ms");
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

    protected List<Move> smartBaseMoveGenerator(Game game) {
        SmartBaseMovesGenerator generator = new SmartBaseMovesGenerator(game);
        Set<Move> baseMovesSet = generator.generate();
        return new ArrayList<>(baseMovesSet);
    }

    protected List<Move> smartBaseMoveGenerator(Game game, Rack rack) {
        SmartBaseMovesGenerator generator = new SmartBaseMovesGenerator(game, rack);
        Set<Move> baseMovesSet = generator.generate();
        return new ArrayList<>(baseMovesSet);
    }

    public abstract Move execute(Game game);

}
