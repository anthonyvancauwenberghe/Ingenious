package com.ingenious.algorithm.bot.impl.montecarlo;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.concurrent.Callable;

public class MCSSimulation implements Callable {

    private Move move;
    private int winAmount = 0;
    private Game game;
    private final int index;
    private final int[] totalwins;
    private final int simulations;

    public MCSSimulation(Game game, Move move, int index, int[] totalwins, int simulations) {
        this.move = move;
        this.game = game;
        this.index = index;
        this.totalwins = totalwins;
        this.simulations = simulations;
    }

    @Override
    public Object call() {
        Game firstMoveGame = game.getClone();
        firstMoveGame.simulateMove(this.move);

        int simulationRound = 0;
        Move randomMove;
        BotAlgorithm algorithm = Configuration.MCTS_SIMULATION_ALGORITHM;
        while (simulationRound < this.simulations) {
            Game aGame = firstMoveGame.getClone();

            boolean gameOver = false;
            while (!gameOver) {
                randomMove = algorithm.execute(aGame);
                if (randomMove == null) {
                    System.out.println("probably gameover but unsure");
                    break;
                }

                gameOver = aGame.simulateMove(randomMove);
            }

            if (!game.firstPlayerWon()) {
                this.winAmount++;
            }

            simulationRound++;
            aGame = null;
        }
        if(index % 10 == 0)
        System.out.print(".");
        this.totalwins[this.index] = this.winAmount;

        return null;
    }
}
