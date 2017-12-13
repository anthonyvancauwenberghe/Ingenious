package com.ingenious.engine.logic.game;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;
import com.ingenious.model.Move;

public class BoardMovePlacementGameLogic extends Logic<Void> {
    private Move move;

    public BoardMovePlacementGameLogic(Game game, Move move) {
        super(game);
        this.move = move;
    }

    public Void execute() {
        /* Place Piece on board */
        this.getGame().getBoard().setTile(move.getHeadNode().getX(), move.getHeadNode().getY(), move.getPiece().getHead());
        this.getGame().getBoard().setTile(move.getTailNode().getX(), move.getTailNode().getY(), move.getPiece().getTail());
        this.getGame().getCurrentPlayer().getRack().resetSelectedPiece();
        return null;
    }
}
