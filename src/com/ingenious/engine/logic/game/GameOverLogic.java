package com.ingenious.engine.logic.game;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;
import com.ingenious.model.BoardNode;
import com.ingenious.model.Score;
import com.ingenious.model.Tile;
import com.ingenious.model.players.Player;

public class GameOverLogic extends Logic {

    public GameOverLogic(Game game) {
        super(game);
    }

    public boolean calculate() {
        return playerHasMaxScore() || noMovesLeft();
    }

    public boolean playerHasMaxScore() {
        for (int i = 0; i < 6; i++) {
            for (Player player : this.getGame().getPlayers()) {
                if (player.getScore().toArray()[i] >= 18) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean playerHasMaxScore(int playerIndex) {
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

    public Boolean firstPlayerWinsWithBestScore(Score score1, Score score2)
    {
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
