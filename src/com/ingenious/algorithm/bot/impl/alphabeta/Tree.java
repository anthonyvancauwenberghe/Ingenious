package com.ingenious.algorithm.bot.impl.alphabeta;

import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.util.ArrayList;

public class Tree
{
    private Game root;
    private ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();

    public Tree(Game root)
    {
        this.root = root;
    }

    public TreeNode getRootAsTreeNode()
    {
        if(nodes.size() > 0)
        {
            TreeNode rootNode = new TreeNode(true);
            rootNode.children = this.nodes;

            return rootNode;
        }
        return null;
    }

    public ArrayList<TreeNode> getFirstLayerChilden()
    {
        return nodes;
    }

    public void addDirectChildNode(TreeNode node)
    {
        this.nodes.add(node);
    }

    public void addDirectChildNodes(ArrayList<TreeNode> nodes)
    {
        this.nodes = nodes;
    }

    public Game getRootState() {
        return root;
    }

    public Game getNodeState(TreeNode node)
    {
        Game state = this.root.getClone();

        for (Move move : node.parentMoves)
        {
            state.doSimulationMove(move);
        }
        state.doSimulationMove(node.move);

        return state;
    }

    public Game getParentState(TreeNode node)
    {
        Game state = this.root.getClone();

        for (Move move : node.parentMoves)
        {
            state.doSimulationMove(node.move);
        }

        return state;
    }

    public ArrayList<TreeNode> getFirstLayerChildren()
    {
        return nodes;
    }
}
