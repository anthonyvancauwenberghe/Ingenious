package com.ingenious.algorithm.bot.impl.experiments;


import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Experiment extends BotAlgorithm {

    /* Amount of simulations per basemove */
    private final int simulations = Configuration.EXPERIMENT_SIMULATIONS;

    public Experiment() {
        super();
    }

    public Move execute(Game game) {
        Game.simulating = true;

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


        System.out.println("Executing Experiments On " + Runtime.getRuntime().availableProcessors() + " threads");
        System.out.println("Amount Of Simulations: " + this.simulations);
        System.out.println("-------------------------------------------------------");
        System.out.println(Configuration.EXPERIMENT_FIRST_PLAYER.getClass().getSimpleName() + " VS " + Configuration.EXPERIMENT_SECOND_PLAYER.getClass().getSimpleName());
        int[] totalWins = new int[this.simulations];

        int index = 0;

        double startTime, endTime, timeDifference;
        startTime = System.nanoTime();

        for (int i = 0; i < this.simulations; i++) {
            executorService.submit(new ExperimentSimulation(game, index, totalWins));
            index++;
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(24, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endTime = System.nanoTime();
        timeDifference = (endTime - startTime) / 1000000;

        int maxValueIndex = 0;
        for (int i = 0; i < totalWins.length; i++) {
            if (totalWins[i] == 1) {
                maxValueIndex++;
            }
        }
        double winrate = (100 * (double) maxValueIndex) / (double) simulations;
        DecimalFormat f = new DecimalFormat("##.00");
        System.out.println("");
        System.out.println("-------------------------------------------------------");
        System.out.println("First Player Won " + maxValueIndex + "/" + simulations + " games");
        System.out.println("First Player Winrate: " + f.format(winrate) + "%");
        System.out.println("Time to execute: " + f.format(timeDifference / ((double) 1000)) + " s");
        System.out.println("Average Time Per Experiment: " + f.format(timeDifference / ((double) this.simulations)) + " ms");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        Game.simulating = false;
        System.exit(0);
        return null;
    }
}
