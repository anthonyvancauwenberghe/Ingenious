package tests.impl;

import com.ingenious.algorithm.bot.impl.expectiminimax.ExpectiMiniMaxAlgorithm;
import com.ingenious.engine.Game;
import tests.Test;

public class ExpectiMiniMaxAlgorithmTest extends Test
{
    public ExpectiMiniMaxAlgorithmTest(Game game)
    {
        super(game);
    }

    @Override
    protected void executeTest()
    {
        ExpectiMiniMaxAlgorithm algorithm = new ExpectiMiniMaxAlgorithm();
        game.doSimulationMove(algorithm.execute(this.game));
    }
}
