package com.ingenious.engine.logic.calculation;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;

public abstract class CalculateLogic extends Logic implements Calculateable {
    public CalculateLogic(Game game) {
        super(game);
    }
}
