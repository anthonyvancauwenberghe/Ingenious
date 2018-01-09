package com.ingenious.algorithm.bot.impl.mcts;


import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MCTSAlgorithm extends BotAlgorithm {

    /* Amount of simulations per basemove */
    private final int simulations = Configuration.MCTS_SIMULATIONS;

    public MCTSAlgorithm() {
        super();
    }

    public Move execute(Game game) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        ArrayList<Move> baseMoves = this.generateBaseMoves(game, true);
        System.out.println("Executing Algorithm on "+ Runtime.getRuntime().availableProcessors() + " threads");
        System.out.println("amount of moves: " + baseMoves.size());
        System.out.println("simulations per move: " + this.simulations);
        System.out.println("total amount of moveSimulations: " + baseMoves.size() * this.simulations);
        int[] totalWins = new int[baseMoves.size()];

        int index = 0;
        for (Move baseMove : baseMoves) {
            executorService.submit(new MCTSSimulation(game, baseMove, index, totalWins, this.simulations));
            index++;
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int maxValueIndex = 0;
        for (int i = 0; i < totalWins.length; i++) {
            if (totalWins[i] > totalWins[maxValueIndex]) {
                maxValueIndex = i;
            }
        }
        return baseMoves.get(maxValueIndex);
    }
}
