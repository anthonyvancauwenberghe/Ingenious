package com.ingenious.algorithm.bot.impl.alphabeta;

import com.ingenious.algorithm.support.StraightLineMoveGenerator;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.Set;
import java.util.concurrent.Callable;

public class NodeGenerator implements Callable {

    private Game game;
    private Tree tree;
    private TreeNode treeNode;
    private int countDown;

    public NodeGenerator(Game state, Tree tree, TreeNode parent, int countdown) {
        this.tree = tree;
        this.game = state;
        this.treeNode = parent;
        this.countDown = countdown;
    }

    @Override
    public Object call() {
        this.doAlgorithm(this.game, this.tree, this.treeNode, this.countDown);
        return null;
    }

    private void doAlgorithm(Game state, Tree tree, TreeNode treeNode, int countDown) {
        if (countDown > 0) {
            StraightLineMoveGenerator generator = new StraightLineMoveGenerator(state);
            Set<Move> baseMoves = generator.generate();

            for (Move move : baseMoves) {
                TreeNode newChild = new TreeNode(move, treeNode.getFullPath());
                newChild.addEvaluation(tree.getParentState(newChild), tree.getNodeState(newChild));

                treeNode.addChild(newChild);
                doAlgorithm(tree.getNodeState(newChild), tree, newChild, countDown - 1);
            }
        }
    }
}
