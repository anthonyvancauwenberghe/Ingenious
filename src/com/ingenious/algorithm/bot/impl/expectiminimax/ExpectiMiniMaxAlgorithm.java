package com.ingenious.algorithm.bot.impl.expectiminimax;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.engine.logic.game.GameOverLogic;
import com.ingenious.model.Move;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExpectiMiniMaxAlgorithm extends BotAlgorithm
{
    private int depthToUse = 0;
    public ExpectiMiniMaxAlgorithm(){}

    public ExpectiMiniMaxAlgorithm(int depthToUse)
    {
        this.depthToUse = depthToUse;
    }

    public Move execute(Game game)
    {
        if(depthToUse == 0)
            depthToUse = Configuration.MINIMAX_TREE_DEPTH;

        Game.simulating = true;
        GameOverLogic logic = new GameOverLogic(game);
        if(!logic.execute())
        {
            TreeNode expectiTree = createTree(game);
            Object[] results = new Object[2];
            if (Configuration.USE_BASE_MINIMAX)
                results = runMiniMax(expectiTree, depthToUse, true, results);
            else
                results = runAlphaBeta(expectiTree, depthToUse, -10000, 10000, true, results);

            //make sure a move is returned even if no optimal one is found
            if(results[1] == null)
                results[1] = fillWithMove(expectiTree);

            Game.simulating = false;
            return (Move) results[1];
        }
        System.out.println("Bot runs EMM when game is already won!");
        Game.simulating = false;
        return null;
    }

    private Move fillWithMove(TreeNode tree)
    {
        Random r = new Random();
        return tree.getChild(r.nextInt(tree.getChildren().size())).getMove();
    }


    public TreeNode createTree(Game rootState)
    {
        TreeNode tree = new TreeNode(rootState,null, null);
        int countdown = Configuration.MINIMAX_TREE_DEPTH;

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        if(!Configuration.ExperimentMode)
            System.out.println("Executing Algorithm on "+ Runtime.getRuntime().availableProcessors() + " threads");

        List<Move> baseMoves = smartBaseMoveGenerator(rootState);

        for (Move move : baseMoves)
        {
            Game childState = rootState.getClone();

            boolean endOfGame = childState.simulateMove(move);
            TreeNode newChild = new TreeNode(childState, tree, move);
            newChild.addEvaluation(rootState, childState);
            tree.addChild(newChild);

            if (!endOfGame)
                executorService.submit(new NodeGenerator(childState, newChild, countdown - 1));

        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tree;
    }

    public Object[] runAlphaBeta(TreeNode node, int depth, int alpha, int beta, Boolean maximizing, Object[] returned)
    {
        if(depth == 0 || !node.hasChildren())
        {
            returned[0] = node.evaluationScore;
            returned[1] = node.getRootMove();
            return returned;
        }

        if (maximizing)
        {
            int bestV = -10000;
            for (TreeNode childNode : node.getChildren())
            {
                int v = (int)runAlphaBeta(childNode, depth - 1, alpha, beta, false, returned)[0];
                alpha = Math.max(alpha, v);
                if (beta <= alpha)
                    break;

                if(alpha == v && !node.isRoot())
                    returned[1] = node.getRootMove();
            }
            returned[0]= bestV;
            if(!node.isRoot())
                returned[1]=node.getRootMove();
            return returned;
        }
        else
        {
            int bestV = 10000;
            for (TreeNode childNode : node.getChildren())
            {
                int v = (int)runAlphaBeta(childNode, depth - 1, alpha, beta, true, returned)[0];
                beta = Math.min(beta, v);
                if (beta <= alpha)
                    break;

                if(bestV == v && !node.isRoot())
                    returned[1] = node.getRootMove();
            }
            returned[0] = bestV;
            if(!node.isRoot())
                returned[1]=node.getRootMove();
            return returned;
        }
    }

    public Object[] runMiniMax(TreeNode node, int depth, Boolean maximizing, Object[] returned)
    {
        if(depth == 0 || !node.hasChildren())
        {
            returned[0] = node.evaluationScore;
            returned[1] = node.getRootMove();
            return returned;
        }

        if (maximizing)
        {
            int bestV = -10000;
            for (TreeNode childNode : node.getChildren())
            {
                int v = (int)runMiniMax(childNode, depth- 1, false, returned)[0];
                bestV = Math.max(bestV, v);
                if(bestV == v && !node.isRoot())
                    returned[1] = node.getRootMove();
            }
            returned[0] = bestV;
            return returned;
        }
        else
        {
            int bestV = 10000;
            for (TreeNode childNode : node.getChildren())
            {
                int v = (int)runMiniMax(childNode, depth- 1,true, returned)[0];
                bestV = Math.min(bestV, v);
                if(bestV == v && !node.isRoot())
                    returned[1] = node.getRootMove();
            }

            returned[0] = bestV;
            return returned;
        }
    }

}