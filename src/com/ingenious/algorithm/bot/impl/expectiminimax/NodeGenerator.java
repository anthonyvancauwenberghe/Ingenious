package com.ingenious.algorithm.bot.impl.expectiminimax;

import com.ingenious.algorithm.support.nodegenerators.StraightLineMoveGenerator;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.model.Rack;

import java.util.Set;
import java.util.concurrent.Callable;

public class NodeGenerator implements Callable {

    private Game game;
    private TreeNode treeNode;
    private int countDown;
    private boolean maximising;

    public NodeGenerator(Game state, TreeNode parent, int countdown, boolean maximising)
    {
        this.game = state;
        this.treeNode = parent;
        this.countDown = countdown;
        this.maximising = maximising;
    }

    @Override
    public Object call() {
        this.doAlgorithm(this.game, this.treeNode, this.countDown, this.maximising);
        return null;
    }

    private void doAlgorithm(Game parentState,  TreeNode parentNode, int countDown, boolean maximising)
    {
        if (countDown > 0)
        {
            StraightLineMoveGenerator generator;
            if(maximising)
                generator = new StraightLineMoveGenerator(parentState);
            else
            {
                Rack minimisingEstimatedRack = parentState.getTracker().getRandomRack(game.getOtherPlayer().getRack());
                generator = new StraightLineMoveGenerator(parentState, minimisingEstimatedRack);
            }

            Set<Move> baseMoves = generator.generate();

            for (Move move : baseMoves)
            {
                Game childState = parentState.getClone();
                childState.doSimulationMove(move);
                TreeNode newChild = new TreeNode(childState, parentNode, move);
                newChild.addEvaluation(parentState, childState);
                parentNode.addChild(newChild);

                if(!childState.thereIsAWinner())
                    doAlgorithm(childState, newChild, countDown - 1, !maximising);
            }
        }
    }
}
