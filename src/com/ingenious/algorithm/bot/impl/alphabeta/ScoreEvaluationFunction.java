package com.ingenious.algorithm.bot.impl.alphabeta;

import com.ingenious.engine.Game;

public class ScoreEvaluationFunction
{
    public static int evaluateScores(Game parentNodeState, Game currentNodeState)
    {
        int[] playerScoreCurrent = currentNodeState.getCurrentPlayer().getScore().getScores();

        int[] playerScorePast = parentNodeState.getCurrentPlayer().getScore().getScores();


        int[] playerDiff = new int[6];

        for (int i = 0; i < 6; i++)
        {
            playerDiff[i]  = playerScoreCurrent[i] - playerScorePast[i];
        }

        //multipliers
        int maxWeight = 1;
        for (int j: playerScorePast)
        {
            if(playerScorePast[j] <= 8)
                playerDiff[j] *= maxWeight - playerScorePast[j];
            else if (playerScorePast[j] >= 9)
                playerDiff[j] *= maxWeight - (17 - playerScorePast[j]);
            else  if (playerScorePast[j] == 18)
                playerDiff[j] *= 0;
        }

        int totalGain = 0;
        for(int i : playerDiff)
        {
            totalGain += playerDiff[i];
        }

        if(totalGain > 0)
            return totalGain;
        else
            return 0;
    }
}

