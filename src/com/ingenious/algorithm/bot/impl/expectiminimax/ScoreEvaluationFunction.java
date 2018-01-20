package com.ingenious.algorithm.bot.impl.expectiminimax;

import com.ingenious.engine.Game;
import com.ingenious.model.players.Player;
import com.ingenious.model.players.impl.Bot;

public class ScoreEvaluationFunction
{
    public static int evaluateScores(Game parentNodeState, Game currentNodeState)
    {
        int[] playerScoreCurrent = currentNodeState.getCurrentPlayer().getScore().getScores();
        int[] playerScorePast = parentNodeState.getCurrentPlayer().getScore().getScores();


        //calculate differences between past and present scores
        int[] playerDiff = new int[6];
        for (int i = 0; i < 6; i++)
        {
            playerDiff[i]  = playerScoreCurrent[i] - playerScorePast[i];
        }

        //add multipliers
        int maxWeight = 10;
        for(int j = 0; j < 6; j++)
        {
            if(playerScorePast[j] <= 8)
                playerDiff[j] *= (maxWeight - playerScorePast[j]);
            else if (playerScorePast[j] >= 9)
                //playerDiff[j] *= (maxWeight - (17 - playerScorePast[j]));
                playerDiff[j] *= 0;
        }

        //award one-time bonus to lowest color
        for(int x = 0; x < 18; x++)
        {
            for(int y = 0; y < 6; y++)
            {
                if (playerDiff[y] == x)
                {
                    playerDiff[y] += 300;
                    break;
                }
            }
        }

        int totalGain = 0;
        for(int i = 0; i < 6; i++)
        {
            totalGain += playerDiff[i];
        }

        //if game is won, award a large bonus
        if(currentNodeState.gameOver() && currentNodeState.getWinner().isBot())
        {
            totalGain += 1000;
        }

        if(totalGain > 0)
            return totalGain;
        else
            return 0;
    }
}

