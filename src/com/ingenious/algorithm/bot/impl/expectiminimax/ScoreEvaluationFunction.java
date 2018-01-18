package com.ingenious.algorithm.bot.impl.expectiminimax;

import com.ingenious.engine.Game;

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
                playerDiff[j] *= (maxWeight - (17 - playerScorePast[j]));
            else  if (playerScorePast[j] == 18)
                playerDiff[j] *= 0;
        }

        int totalGain = 0;
        for(int i = 0; i < 6; i++)
        {
            totalGain += playerDiff[i];
        }

        if(totalGain > 0)
            return totalGain;
        else
            return 0;
    }
}

