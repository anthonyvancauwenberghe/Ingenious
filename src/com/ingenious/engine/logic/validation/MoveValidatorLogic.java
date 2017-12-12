package com.ingenious.engine.logic.validation;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;
import com.ingenious.model.Move;

public class MoveValidatorLogic extends Logic<Boolean> {

    private Move move;

    public MoveValidatorLogic(Game game, Move move) {
        super(game);
        this.move = move;
    }

    public Boolean execute() {
        System.out.println("head & tail coords for validation: ");
        System.out.println(this.move.getHeadNode().getX() +"," + this.move.getHeadNode().getY());
        System.out.println(this.move.getTailNode().getX() +"," + this.move.getTailNode().getY());
        boolean headNodeAvailable = this.getGame().getBoard().getNode(this.move.getHeadNode().getX(), this.move.getHeadNode().getY()).isEmpty();
        boolean bottomNodeAvailable = this.getGame().getBoard().getNode(this.move.getTailNode().getX(), this.move.getTailNode().getY()).isEmpty();

        if (!headNodeAvailable)
            System.out.println("headNode: " + this.move.getHeadNode().x + "," + this.move.getHeadNode().x + " is taken");
        if (!bottomNodeAvailable)
            System.out.println("tailNode: " + this.move.getTailNode().x + "," + this.move.getTailNode().x + " is taken");
        return headNodeAvailable && bottomNodeAvailable && this.getGame().getBoard().isNeighbour(this.move.getHeadNode(), this.move.getTailNode());
    }

}
