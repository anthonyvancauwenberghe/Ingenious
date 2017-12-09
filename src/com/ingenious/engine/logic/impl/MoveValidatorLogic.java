package com.ingenious.engine.logic.impl;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;

public class MoveValidatorLogic extends Logic<Boolean> {

    public MoveValidatorLogic(Game game) {
        super(game);
    }

    public Boolean execute() {
        return true;
    }
}
