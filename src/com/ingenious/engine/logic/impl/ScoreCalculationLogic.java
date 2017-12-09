package com.ingenious.engine.logic.impl;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;
import com.ingenious.model.Score;

public class ScoreCalculationLogic extends Logic<Score> {
    public ScoreCalculationLogic(Game game) {
        super(game);
    }

    @Override
    public Score execute() {
        return new Score();
    }
}
