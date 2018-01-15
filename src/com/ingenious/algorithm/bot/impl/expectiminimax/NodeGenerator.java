package com.ingenious.algorithm.bot.impl.expectiminimax;

import com.ingenious.algorithm.support.AllBaseMovesGenerator;
import com.ingenious.algorithm.support.StraightLineMoveGenerator;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.model.PieceTracker;

import java.util.Set;
import java.util.concurrent.Callable;

public class NodeGenerator implements Callable {

    private Game game;
    private Tree tree;
    private TreeNode treeNode;
    private int countDown;
    private boolean maximizing;

    public NodeGenerator(Game state, Tree tree, TreeNode parent, int countdown, boolean maximizing) {
        this.tree = tree;
        this.game = state;
        this.treeNode = parent;
        this.countDown = countdown;
        this.maximizing = maximizing;
    }

    @Override
    public Object call() {
        this.doAlgorithm(this.game, this.tree, this.treeNode, this.countDown, this.maximizing);
        return null;
    }

    private void doAlgorithm(Game state, Tree tree, TreeNode treeNode, int countDown, boolean maximizing) {
        if (countDown > 0)
        {
            Set<Move> baseMoves;
            if(maximizing)
            {
                AllBaseMovesGenerator generator = new AllBaseMovesGenerator(state);
                baseMoves = generator.generate();
            }
            else
            {
                AllBaseMovesGenerator generator = new AllBaseMovesGenerator(state, state.getTracker().getRandomRack(state.getCurrentPlayer().getRack()));
                baseMoves = generator.generate();
            }

            for (Move move : baseMoves) {
                TreeNode newChild = new TreeNode(move, treeNode.getFullPath());
                newChild.addEvaluation(tree.getParentState(newChild), tree.getNodeState(newChild));

                treeNode.addChild(newChild);
                doAlgorithm(tree.getNodeState(newChild), tree, newChild, countDown - 1, !maximizing);
            }
        }
    }
}
