package com.ingenious.algorithm.bot.impl.experiments;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.concurrent.Callable;

public class ExperimentSimulation implements Callable {

    private int winAmount = 0;
    private Game game;
    private final int index;
    private final int[] totalwins;

    public ExperimentSimulation(Game game, int index, int[] totalwins) {
        this.game = game;
        this.index = index;
        this.totalwins = totalwins;
    }

    @Override
    public Object call() {
        if(index % 10 == 0)
            System.out.print(".");
        Game game = this.game.getClone();


        BotAlgorithm firstPlayerAlgorithm = Configuration.EXPERIMENT_FIRST_PLAYER;
        BotAlgorithm secondPlayerAlgorithm = Configuration.EXPERIMENT_SECOND_PLAYER;

        Move randomMove = null;
        boolean gameOver = false;
        while (!gameOver) {
            if (game.currentPlayerIndex == 0) {
                randomMove = firstPlayerAlgorithm.execute(game);
            } else if (game.currentPlayerIndex == 1) {
                randomMove = secondPlayerAlgorithm.execute(game);
            }

            if (randomMove == null) {
                System.out.println("probably gameover but unsure");
                break;
            }

            gameOver = game.simulateMove(randomMove);

            if (game.firstPlayerWon()) {
                this.totalwins[this.index] = 1;
                //System.out.println("first player won in experiment");
                return null;
            }
            if (game.secondPlayerWon()) {
                this.totalwins[this.index] = 0;
              //  System.out.println("second player won in experiment");
                return null;
            }
        }

        return null;
    }
}
