package com.ingenious.engine.logic.game;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;
import com.ingenious.model.Move;

public class BoardMoveExecutionGameLogic extends Logic {

    private Move move;

    public BoardMoveExecutionGameLogic(Game game, Move move) {
        super(game);
        this.move = move;
    }

    public void execute() {
        BoardMovePlacementGameLogic placeMove = new BoardMovePlacementGameLogic(this.getGame(), this.move);

        if (!placeMove.execute())
            return;
    }
}
