package tests.impl;

import com.ingenious.algorithm.bot.impl.alphabeta.AlphaBetaAlgorithm;
import com.ingenious.engine.Game;
import tests.Test;

public class AlphaBetaAlgorithmTest extends Test
{
    public AlphaBetaAlgorithmTest(Game game)
    {
        super(game);
    }

    @Override
    protected void executeTest()
    {
        AlphaBetaAlgorithm algorithm = new AlphaBetaAlgorithm();
        game.doSimulationMove(algorithm.execute(this.game));
    }
}
