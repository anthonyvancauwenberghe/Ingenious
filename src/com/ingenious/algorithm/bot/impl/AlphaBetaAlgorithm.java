package com.ingenious.algorithm.bot.impl;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import javax.swing.tree.TreeNode;

public class AlphaBetaAlgorithm extends BotAlgorithm
{
        public Move generate() {
            return null;
        }

    public Move execute(Game game)
    {
        //create tree
        //traverse tree and find goal node


        return null;
    }

    /*
    public int run(TreeNode node, int depth, int alpha, int beta, Boolean maximizing) {
            if (depth == 0 || node.hasChildren()) {
                return node.getData();
            }
            if (maximizing) {
                int v = -100;
                for (TreeNode childNode : node.getChildren()) {
                    v = Math.max(v, run(childNode, depth - 1, alpha, beta, false));
                    alpha = Math.max(alpha, v);
                    if (beta <= alpha)
                        break;
                }
                return v;
            } else {
                int v = 100;
                for (TreeNode childNode : node.getChildren()) {
                    v = Math.min(v, run(childNode, depth - 1, alpha, beta, true));
                    beta = Math.min(beta, v);
                    if (beta <= alpha)
                        break;
                }
                return v;
            }
        }
        */
    }
