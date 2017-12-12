package com.ingenious.algorithm.bot.impl.alphabeta;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.algorithm.bot.impl.alphabeta.TreeNode;

import java.util.ArrayList;

public class AlphaBetaAlgorithm extends BotAlgorithm
{
        public Move generate() {
            return null;
        }

    public Move execute(Game game)
    {
        Tree alphabetaTree = generateTree(game, 1);

        int result = (int)runAlphaBeta(alphabetaTree.getRootAsTreeNode(),1,-10000, 10000, true)[0];




        return null;
    }

    public Tree generateTree(Game game, int layerLimit)
    {
        int countdown = layerLimit;
        Tree tree = new Tree(game);

        ArrayList<TreeNode> newChildren = generateFirstSetOfChildren(game, tree);

        for(TreeNode newChild : newChildren)
        {
            generateNewChildren(tree.getNodeState(newChild), tree, newChild, countdown-1);
        }

        return tree;
    }

    public void generateNewChildren(Game state, Tree tree, TreeNode parent, int countdown)
    {
        if(countdown > 0)
        {
            ArrayList<Move> baseMoves = this.generateBaseMoves(state,false);

            for (Move move : baseMoves)
            {
                TreeNode newChild = new TreeNode(move, parent.getFullPath());
                newChild.addEvaluation(tree.getParentState(newChild),tree.getNodeState(newChild));

                parent.addChild(newChild);
                generateNewChildren(tree.getNodeState(newChild), tree, newChild, countdown-1);
            }
        }
    }

    private ArrayList<TreeNode> generateFirstSetOfChildren(Game game, Tree tree)
    {
        ArrayList<Move> baseMoves = this.generateBaseMoves(game, false);
        ArrayList<TreeNode> outputNodes = new ArrayList<TreeNode>();

        for (Move move : baseMoves)
        {
            TreeNode newChild = new TreeNode(move, new ArrayList<Move>());
            newChild.addEvaluation(tree.getRootState(),tree.getNodeState(newChild));
            tree.addDirectChildNode(newChild);
            outputNodes.add(newChild);
        }
        return outputNodes;
    }


    public Object[] runAlphaBeta(TreeNode node, int depth, int alpha, int beta, Boolean maximizing)
    {	
    	Object[] returned = new Object[2];
            if (depth == 0 || !node.hasChildren())
            {
            	returned[0]=node.evaluationScore;
                returned[1]=node.move;
                return returned;
            }
            if (maximizing)
            {
                int v = -10000;
                for (TreeNode childNode : node.getChildren())
                {
                    v = Math.max(v, (int)runAlphaBeta(childNode, depth - 1, alpha, beta, false)[0]);
                    alpha = Math.max(alpha, v);
                    if (beta <= alpha)
                        break;
                }
                returned[0]=v;
                returned[1]=node.move;
                return returned;
            }
            else
            {
                int v = 10000;
                for (TreeNode childNode : node.getChildren())
                {
                    v = Math.min(v, (int)runAlphaBeta(childNode, depth - 1, alpha, beta, true)[0]);
                    beta = Math.min(beta, v);
                    if (beta <= alpha)
                        break;
                }
                returned[0]=v;
                returned[1]=node.move;
                return returned;
            }
      }

}
