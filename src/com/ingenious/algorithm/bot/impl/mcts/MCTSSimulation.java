package com.ingenious.algorithm.bot.impl.mcts;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.concurrent.Callable;

public class MCTSSimulation implements Callable {
    public static Object lock = new Object();

    private Move move;
    private int winAmount = 0;
    private Game game;
    private final int index;
    private final int[] totalwins;
    private final int simulations;

    public MCTSSimulation(Game game, Move move, int index, int[] totalwins, int simulations) {
        this.move = move;
        this.game = game;
        this.index = index;
        this.totalwins = totalwins;
        this.simulations = simulations;
    }

    @Override
    public Object call() {
        Game firstMoveGame = game.getClone();
        firstMoveGame.doSimulationMove(this.move);

        int simulationRound = 0;
        Move randomMove;
        BotAlgorithm randomAlgorithm = Configuration.MCTS_SIMULATION_ALGORITHM;
        while (simulationRound < this.simulations) {
            Game aGame = firstMoveGame.getClone();
            while (!game.isOver()) {
                randomMove = randomAlgorithm.execute(aGame);
                if (randomMove == null)
                    break;
                aGame.doSimulationMove(randomMove);
            }

            if (!game.firstPlayerWon()) {
                this.winAmount++;
            }

            simulationRound++;
            aGame = null;
        }

        this.totalwins[this.index] = this.winAmount;

        return null;
    }
}
