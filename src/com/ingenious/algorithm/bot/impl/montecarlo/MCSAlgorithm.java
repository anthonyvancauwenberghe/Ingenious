package com.ingenious.algorithm.bot.impl.montecarlo;


import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.algorithm.support.nodegenerators.GreedySmartBaseMovesGenerator;
import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MCSAlgorithm extends BotAlgorithm {

    /* Amount of simulations per basemove */
    private final int simulations = Configuration.MCTS_SIMULATIONS;

    public MCSAlgorithm() {
        super();
    }

    public Move execute(Game game) {
        Game.simulating = true;
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        GreedySmartBaseMovesGenerator generator = new GreedySmartBaseMovesGenerator(game);
        Set<Move> baseMovesSet = generator.generate();
        List<Move> baseMoves = new ArrayList<>(baseMovesSet);

        if(Configuration.DEBUG_MODE){
            System.out.println("Executing Algorithm on "+ Runtime.getRuntime().availableProcessors() + " threads");
            System.out.println("amount of moves: " + baseMoves.size());
            System.out.println("simulations per move: " + this.simulations);
            System.out.println("total amount of moveSimulations: " + baseMoves.size() * this.simulations);
        }
        System.out.print("Executing MCS");

        int[] totalWins = new int[baseMoves.size()];

        int index = 0;

        for (Move baseMove : baseMoves) {
            if (Configuration.LIMITED_SIMULATION) {
                executorService.submit(new MCSLimitedSimulation(game, baseMove, index, totalWins, this.simulations));
            } else {
                executorService.submit(new MCSSimulation(game, baseMove, index, totalWins, this.simulations));
            }

            index++;
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(25, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int maxValueIndex = 0;
        for (int i = 0; i < totalWins.length; i++) {
            if (totalWins[i] > totalWins[maxValueIndex]) {
                maxValueIndex = i;
            }
        }
        System.out.println("");
        Game.simulating = false;
        return baseMoves.get(maxValueIndex);
    }
}
