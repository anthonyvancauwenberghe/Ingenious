package com.ingenious.algorithm.bot.impl.expectiminimax;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.algorithm.support.AllBaseMovesGenerator;
import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.model.Rack;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExpectiMiniMaxAlgorithm extends BotAlgorithm
{
        public Move generate() {
            return null;
        }

    public Move execute(Game game)
    {
        int treeDepth = Configuration.MINIMAX_TREE_DEPTH;
        Tree expectiTree = generateTree(game, treeDepth, true);

        Object[] returned = new Object[2];
        Object[] results;

        if(Configuration.MINIMAX_BASE_VERSION)
             results =  runMiniMax(expectiTree.getRootAsTreeNode(),treeDepth,true, returned);
        else
            results =  runAlphaBeta(expectiTree.getRootAsTreeNode(),treeDepth,-10000, 10000, true, returned);

        return (Move) results[1];
    }

    public Tree generateTree(Game game, int layerLimit, boolean heuristics)
    {
        int countdown = layerLimit;
        Tree tree = new Tree(game);

        ArrayList<TreeNode> newChildren = generateFirstSetOfChildren(game, tree);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(TreeNode newChild : newChildren)
        {
            executorService.submit(new NodeGenerator(tree.getNodeState(newChild), tree, newChild, countdown-1, true));
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return tree;
    }

    private ArrayList<TreeNode> generateFirstSetOfChildren(Game game, Tree tree)
    {
        AllBaseMovesGenerator generator = new AllBaseMovesGenerator(game, game.getTracker().getRandomRack(game.getCurrentPlayer().getRack()));
        Set<Move> baseMoves = generator.generate();
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

    public Object[] runAlphaBeta(TreeNode node, int depth, int alpha, int beta, Boolean maximizing, Object[] returned)
    {
            if (depth == 0 || !node.hasChildren())
            {
                if(!node.isRoot() && node.getRootMove() != null)
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
                    v = Math.max(v, (int)runAlphaBeta(childNode, depth - 1, alpha, beta, false, returned)[0]);
                    alpha = Math.max(alpha, v);
                    if (beta <= alpha)
                        break;
                }
                returned[0]=v;
                if(!node.isRoot() && node.getRootMove() != null)
                    returned[1]=node.getRootMove();
                return returned;
            }
            else
            {
                int v = 10000;
                for (TreeNode childNode : node.getChildren())
                {
                    v = Math.min(v, (int)runAlphaBeta(childNode, depth - 1, alpha, beta, true, returned)[0]);
                    beta = Math.min(beta, v);
                    if (beta <= alpha)
                        break;
                }
                returned[0]=v;
                if(!node.isRoot() && node.getRootMove() != null)
                    returned[1]=node.getRootMove();
                return returned;
            }
    }

    public Object[] runMiniMax(TreeNode node, int depth, Boolean maximizing, Object[] returned)
    {
        if (depth == 0 || !node.hasChildren())
        {
            if(!node.isRoot() && node.getRootMove() != null)
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
                v = Math.max(v, (int) runMiniMax(childNode, depth - 1,false, returned)[0]);
            }
            returned[0]=v;
            if(!node.isRoot() && node.getRootMove() != null)
                returned[1]=node.getRootMove();
            return returned;
        }
        else
        {
            int v = 10000;
            for (TreeNode childNode : node.getChildren())
            {
                v = Math.min(v, (int) runMiniMax(childNode, depth - 1, true, returned)[0]);
            }
            returned[0]=v;
            if(!node.isRoot() && node.getRootMove() != null)
                returned[1]=node.getRootMove();
            return returned;
        }
    }

}
