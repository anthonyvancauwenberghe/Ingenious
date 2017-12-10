package tests.impl;

import com.ingenious.algorithm.bot.impl.MCTSAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.players.impl.Bot;
import tests.Test;

public class MCTSAlgorithmTest extends Test {
    public MCTSAlgorithmTest(Game game) {
        super(game);
    }

    @Override
    protected void executeTest() {
        MCTSAlgorithm algorithm = new MCTSAlgorithm();
        game.doSimulationMove(algorithm.execute(this.game));
    }
}
