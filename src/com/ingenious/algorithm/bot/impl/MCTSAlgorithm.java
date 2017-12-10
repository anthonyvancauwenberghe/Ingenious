package com.ingenious.algorithm.bot.impl;


import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.ArrayList;

public class MCTSAlgorithm extends BotAlgorithm {

    /* Amount of simulations per basemove */
    private final int simulations = 5;


    public Move execute(Game game) {
        ArrayList<Move> baseMoves = this.generateBaseMoves(game, true);
        System.out.println("amount of moves: " + baseMoves.size());
        System.out.println("simulations per move: " + this.simulations);
        int[] totalWins = new int[baseMoves.size()];

        int wins;
        int index = 0;

        for (Move baseMove : baseMoves) {
            wins = 0;

            Game firstMoveGame = game.getClone();
            firstMoveGame.doSimulationMove(baseMove);

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
                    wins++;
                }

                simulationRound++;
                aGame = null;
            }

            totalWins[index] = wins;
           // System.out.println("Basemove " + index + " won " + wins + " games");
            index++;
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
