package com.ingenious.algorithm.bot.impl.expectiminimax;

import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.ArrayList;

public class TreeNode {
    private ArrayList<TreeNode> children;
    private TreeNode parent;
    private Game state;
    public int evaluationScore;
    private Move move;

    public TreeNode(Game game, TreeNode parent, Move move)
    {
        this.children = new ArrayList<TreeNode>();
        this.state = game.getClone();
        this.parent = parent;
        this.move = move;
    }

    public Move getMove()
    {
        return this.move;
    }

    public boolean isRoot()
    {
        if(this.parent == null)
            return true;
        else
            return false;
    }

    public TreeNode getRoot()
    {
        if(this.isRoot())
            return this;
        else
            return parent.getRoot();
    }

    public Game getRootState()
    {
        return this.getRoot().getState();
    }

    public TreeNode getImmediateChildOfRoot()
    {
        if (this.isRoot())
            return null;
        else if (this.getParent().isRoot())
            return this;
        else
            return parent.getImmediateChildOfRoot();
    }

    public Move getRootMove()
    {
        return getImmediateChildOfRoot().getMove();
    }

    public Game getState() {
        return this.state;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode newChild) {
        this.children.add(newChild);
    }

    public TreeNode getChild(int index)
    {
        return this.children.get(index);
    }

    public void addEvaluation(Game parentState, Game currentState) {
        this.evaluationScore = ScoreEvaluationFunction.evaluateScores(parentState, currentState);
    }

    public boolean hasChildren()
    {
        if (children.size() > 0)
            return true;
        else
            return false;
    }

    public TreeNode getParent()
    {
        return this.parent;
    }

}