package com.ingenious.algorithm.bot.impl.expectiminimax;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.model.Rack;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExpectiMiniMaxAlgorithm extends BotAlgorithm
{
    public Move execute(Game game)
    {
        TreeNode expectiTree = createTree(game);
        Object[] returned = new Object[2];
        Object[] results;
        if(Configuration.USE_BASE_MINIMAX)
            results = runMiniMax(expectiTree,true, returned);
        else
            results =  runAlphaBeta(expectiTree,-10000, 10000, true, returned);
        return (Move) results[1];
    }


    public TreeNode createTree(Game rootState)
    {
        TreeNode tree = new TreeNode(rootState,null, null);
        int countdown = Configuration.MINIMAX_TREE_DEPTH;

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        System.out.println("Executing Algorithm on "+ Runtime.getRuntime().availableProcessors() + " threads");

        //Rack minimisingEstimatedRack = rootState.getTracker().getRandomRack(rootState.getOtherPlayer().getRack());
        List<Move> baseMoves = smartBaseMoveGenerator(rootState/*, minimisingEstimatedRack*/);

        for (Move move : baseMoves)
        {
            Game childState = tree.getRootState().getClone();
            childState.doSimulationMove(move);
            TreeNode newChild = new TreeNode(childState, tree, move);
            newChild.addEvaluation(tree.getRootState(), childState);
            tree.addChild(newChild);

            executorService.submit(new NodeGenerator(childState, newChild, countdown - 1, true));
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tree;
    }

    public Object[] runAlphaBeta(TreeNode node, int alpha, int beta, Boolean maximizing, Object[] returned)
    {
        if (maximizing)
        {
            int v = -10000;
            for (TreeNode childNode : node.getChildren())
            {
                v = Math.max(v, (int)runAlphaBeta(childNode, alpha, beta, false, returned)[0]);
                alpha = Math.max(alpha, v);
                if (beta <= alpha)
                    break;
            }
            returned[0]=v;
            if(!node.isRoot())
                returned[1]=node.getRootMove();
            return returned;
        }
        else
        {
            int v = 10000;
            for (TreeNode childNode : node.getChildren())
            {
                v = Math.min(v, (int)runAlphaBeta(childNode, alpha, beta, true, returned)[0]);
                beta = Math.min(beta, v);
                if (beta <= alpha)
                    break;
            }
            returned[0]=v;
            if(!node.isRoot())
                returned[1]=node.getRootMove();
            return returned;
        }
    }

    public Object[] runMiniMax(TreeNode node, Boolean maximizing, Object[] returned)
    {
        if (maximizing)
        {
            int v = -10000;
            for (TreeNode childNode : node.getChildren())
            {
                v = Math.max(v, (int)runMiniMax(childNode, false, returned)[0]);
            }
            returned[0]=v;
            if(!node.isRoot())
                returned[1]=node.getRootMove();
            return returned;
        }
        else
        {
            int v = 10000;
            for (TreeNode childNode : node.getChildren())
            {
                v = Math.min(v, (int)runMiniMax(childNode, true, returned)[0]);
            }
            returned[0]=v;
            if(!node.isRoot())
                returned[1]=node.getRootMove();
            return returned;
        }
    }

}