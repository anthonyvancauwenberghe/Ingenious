package com.ingenious.algorithm.bot.impl.qlearning;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

/**
 * Created by carolley on 10-Jan-18.
 */
public class qlearning extends BotAlgorithm{
    @Override
    public Move execute(Game game) {
        return null;
    }
    /**
     * Load Qtable
     * Generate All State Actions
     * If epsilon then make random choice
     * Else choose highest q value from State Actions available
     * Edit visited of the chosen State Action
     * Edit Q-Value
     * Repeat
     * Game ends -> Edit Qtable and Save Qtable
     */
}
