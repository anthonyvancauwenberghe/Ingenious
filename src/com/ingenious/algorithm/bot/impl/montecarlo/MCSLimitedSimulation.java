package com.ingenious.algorithm.bot.impl.montecarlo;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.algorithm.support.nodegenerators.Eval;
import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.concurrent.Callable;

public class MCSLimitedSimulation implements Callable {

    private Move move;
    private int winScore = 0;
    private Game game;
    private final int index;
    private final int[] totalwins;
    private final int simulations;

    public MCSLimitedSimulation(Game game, Move move, int index, int[] totalwins, int simulations) {
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
        Eval evaluation = new Eval();

        int simulationRound = 0;
        Move randomMove;
        BotAlgorithm algorithm = Configuration.MCTS_SIMULATION_ALGORITHM;
        while (simulationRound < this.simulations) {
            Game aGame = firstMoveGame.getClone();

            boolean gameOver = false;
            int movesMade = 0;
            while (!gameOver && movesMade < Configuration.MOVE_LIMIT) {

                    randomMove = algorithm.execute(aGame);
                    if (randomMove == null) {
                        System.out.println("probably gameover but unsure");
                        break;
                    }

                    gameOver = aGame.simulateMove(randomMove);

                movesMade++;
            }
            if(game.currentPlayerIndex==0){
                if(game.firstPlayerWon()){
                    this.winScore += 1000;
                }
                this.winScore += evaluation.evaluate_pos(game.getCurrentPlayer());
            }

            if(game.currentPlayerIndex==1){
                if(game.secondPlayerWon()){
                    this.winScore += 1000;
                }
                this.winScore += evaluation.evaluate_pos(game.getCurrentPlayer());
            }

            simulationRound++;
            aGame = null;
        }

        this.totalwins[this.index] = this.winScore;

        return null;
    }
}
