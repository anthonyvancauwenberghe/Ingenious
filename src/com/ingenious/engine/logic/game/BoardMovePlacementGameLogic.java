package com.ingenious.engine.logic.game;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;
import com.ingenious.engine.logic.validation.Validateable;
import com.ingenious.engine.logic.validation.impl.MoveValidatorLogic;
import com.ingenious.model.Move;

public class BoardMovePlacementGameLogic extends Logic {
    private Move move;

    public BoardMovePlacementGameLogic(Game game, Move move) {
        super(game);
        this.move = move;
    }

    public boolean execute() {

        /* Check if move is valid */
        //Validateable moveValidator = new MoveValidatorLogic(this.getGame(), move);
        //if (!moveValidator.validate()) {
         //   System.out.println("ERROR BOT INVALID MOVE!!!!");
          //  return true;
        //}


        /* Place Piece on board */
        this.getGame().getBoard().setTile(move.getHeadNode().getX(), move.getHeadNode().getY(), move.getPiece().getHead());
        this.getGame().getBoard().setTile(move.getTailNode().getX(), move.getTailNode().getY(), move.getPiece().getTail());

        return true;
    }
}
