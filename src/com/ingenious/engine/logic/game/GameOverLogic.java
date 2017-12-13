package com.ingenious.engine.logic.game;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;
import com.ingenious.model.BoardNode;
import com.ingenious.model.Score;
import com.ingenious.model.Tile;
import com.ingenious.model.players.Player;

import javax.swing.*;

public class GameOverLogic extends Logic<Boolean> {

    public GameOverLogic(Game game) {
        super(game);
    }

    public Boolean execute() {
        return playerHasMaxScoreAcrossAllColors(1) || playerHasMaxScoreAcrossAllColors(2) || noMovesLeft();
    }

    public boolean playerHasMaxScoreInAtLeastOneColor() {
        for (int i = 0; i < 6; i++) {
            for (Player player : this.getGame().getPlayers()) {
                if (player.getScore().toArray()[i] < 18) {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean playerHasMaxScoreInAtLeastOneColor(int playerIndex) {
        for (int i = 0; i < 6; i++) {
            if (getGame().getPlayers().get(playerIndex).getScore().toArray()[i] >= 18) {
                return true;
            }

        }
        return false;
    }


    public boolean noMovesLeft() {
        for (BoardNode boardNode : this.getGame().getBoard().getBoardNodes()) {
            if (boardNode.isAvailable()) {
                for (BoardNode neighbour : this.getGame().getBoard().getNeighboursOfNode(boardNode)) {
                    if (neighbour.isAvailable())
                        return false;
                }
            }

        }
        return true;
    }

    public boolean playerHasMaxScoreAcrossAllColors(int playerIndex)
    {
        for (int i = 0; i < 6; i++)
        {
            if (getGame().getPlayers().get(playerIndex).getScore().toArray()[i] < 18)
                return false;
        }
        return true;
    }

    public Boolean firstPlayerWinsWithBestScore()
    {
        Score score1 = getGame().getPlayers().get(0).getScore();
        Score score2 = getGame().getPlayers().get(1).getScore();
        Tile[] s1 = score1.sort();
        Tile[] s2 = score2.sort();

        for(int i = 0; i < 6; i++)
        {
            if(score1.getScore(s1[i]) > score2.getScore(s2[i]))
            {
                return true;
            }
            else if (score1.getScore(s1[i]) < score2.getScore(s2[i]))
            {
                return false;
            }
        }

        return null;
    }
}
