package tests.impl;

import com.ingenious.algorithm.bot.impl.montecarlo.MCSAlgorithm;
import com.ingenious.engine.Game;
import tests.Test;

public class MCTSAlgorithmTest extends Test {
    public MCTSAlgorithmTest(Game game) {
        super(game);
    }

    @Override
    protected void executeTest() {
        MCSAlgorithm algorithm = new MCSAlgorithm();
        game.doSimulationMove(algorithm.execute(this.game));
    }
}
