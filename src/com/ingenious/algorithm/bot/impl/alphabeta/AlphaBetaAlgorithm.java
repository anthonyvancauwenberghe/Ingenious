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
        Tree alphabetaTree = generateTree(game, 1, true);

        return  (Move)runAlphaBeta(alphabetaTree.getRootAsTreeNode(),1,-10000, 10000, true)[1];
    }

    public Tree generateTree(Game game, int layerLimit, boolean heuristics)
    {
        int countdown = layerLimit;
        Tree tree = new Tree(game);

        ArrayList<TreeNode> newChildren = generateFirstSetOfChildren(game, tree, heuristics);

        for(TreeNode newChild : newChildren)
        {
            generateNewChildren(tree.getNodeState(newChild), tree, newChild, countdown-1, heuristics);
        }

        return tree;
    }

    public void generateNewChildren(Game state, Tree tree, TreeNode parent, int countdown, boolean heuristics)
    {
        if(countdown > 0)
        {
            ArrayList<Move> baseMoves = this.generateBaseMoves(state,heuristics);

            for (Move move : baseMoves)
            {
                TreeNode newChild = new TreeNode(move, parent.getFullPath());
                newChild.addEvaluation(tree.getParentState(newChild),tree.getNodeState(newChild));

                parent.addChild(newChild);
                generateNewChildren(tree.getNodeState(newChild), tree, newChild, countdown-1, heuristics);
            }
        }
    }
    private ArrayList<TreeNode> generateFirstSetOfChildren(Game game, Tree tree, boolean heuristics)
    {
        ArrayList<Move> baseMoves = this.generateBaseMoves(game, heuristics);
        ArrayList<TreeNode> outputNodes = new ArrayList<TreeNode>();

        for (Move move : baseMoves)
        {
            TreeNode tempChild = new TreeNode(move, new ArrayList<Move>());
            tempChild.addEvaluation(tree.getRootState(),tree.getNodeState(tempChild));
            tree.addDirectChildNode(tempChild);
            outputNodes.add(tempChild);
        }
        return outputNodes;
    }

    public Object[] runAlphaBeta(TreeNode node, int depth, int alpha, int beta, Boolean maximizing)
    {	
    	Object[] returned = new Object[2];
            if (depth == 0 || !node.hasChildren())
            {
            	if(!node.isRoot())
                {
                    returned[0]=node.evaluationScore;
                    returned[1]=node.getRootMove();
                }
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
                returned[1]=node.getRootMove();
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
                returned[1]=node.getRootMove();
                return returned;
            }
      }

}
