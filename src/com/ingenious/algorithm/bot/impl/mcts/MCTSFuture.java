package com.ingenious.algorithm.bot.impl.mcts;

import com.ingenious.algorithm.bot.impl.RandomAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.concurrent.Callable;

public class MCTSFuture implements Callable {
    public static Object lock = new Object();

    private Move move;
    private int winAmount = 0;
    private Game game;
    private final int index;
    private final int[] totalwins;
    private final int simulations;


    public MCTSFuture(Game game, Move move, int index, int[] totalwins, int simulations) {
        this.move = move;
        this.game = game;
        this.index = index;
        this.totalwins = totalwins;
        this.simulations = simulations;
    }

    @Override
    public Object call() throws Exception {
        Game firstMoveGame = game.getClone();
        firstMoveGame.doSimulationMove(this.move);

        int simulationRound = 0;
        while (simulationRound < this.simulations) {

            //System.out.println("simulation round " + simulationRound);
            Game aGame = firstMoveGame.getClone();
            //boolean validMove = true;

            while (!aGame.isOver()) {
                RandomAlgorithm randomAlgorithm = new RandomAlgorithm();
                Move randomMove = randomAlgorithm.execute(aGame);
                if (randomMove == null)
                    break;
                aGame.doSimulationMove(randomMove);
            }

            if (aGame.isWinner(aGame.getPlayers().get(1))) {
                this.winAmount++;
            }

            simulationRound++;
            aGame = null;
        }

        this.totalwins[this.index] = this.winAmount;

        return null;
    }
}
