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

        int result = runAlphaBeta(alphabetaTree.getRootAsTreeNode(),1,-10000, 10000, true);




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


    public int runAlphaBeta(TreeNode node, int depth, int alpha, int beta, Boolean maximizing)
    {
            if (depth == 0 || !node.hasChildren())
            {
                return node.evaluationScore;
            }
            if (maximizing)
            {
                int v = -10000;
                for (TreeNode childNode : node.getChildren())
                {
                    v = Math.max(v, runAlphaBeta(childNode, depth - 1, alpha, beta, false));
                    alpha = Math.max(alpha, v);
                    if (beta <= alpha)
                        break;
                }
                return v;
            }
            else
            {
                int v = 10000;
                for (TreeNode childNode : node.getChildren())
                {
                    v = Math.min(v, runAlphaBeta(childNode, depth - 1, alpha, beta, true));
                    beta = Math.min(beta, v);
                    if (beta <= alpha)
                        break;
                }
                return v;
            }
        }

    }
