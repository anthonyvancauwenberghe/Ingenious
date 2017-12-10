package com.ingenious.engine.logic.game;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;
import com.ingenious.model.BoardNode;
import com.ingenious.model.players.Player;

public class GameOverLogic extends Logic {

    public GameOverLogic(Game game) {
        super(game);
    }

    public boolean calculate() {
        return playerHasMaxScore() || noMovesLeft();
    }

    private boolean playerHasMaxScore() {
        for (int i = 0; i < 6; i++) {
            for (Player player : this.getGame().getPlayers()) {
                if (player.getScore().toArray()[i] >= 18) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean noMovesLeft() {
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
}
