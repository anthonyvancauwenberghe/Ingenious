package com.ingenious.config;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.algorithm.bot.impl.alphabeta.AlphaBetaAlgorithm;
import com.ingenious.algorithm.bot.impl.greedy.GreedyAlgorithm;
import com.ingenious.algorithm.bot.impl.qlearning.qlearning;
import com.ingenious.algorithm.bot.impl.random.RandomAlgorithm;
import com.ingenious.algorithm.bot.impl.random.SmartRandomAlgorithm;

import java.awt.*;

public class Configuration {

    /* Game Settings */
    public final static int BOARD_WIDTH = 6;

    /* Debug Settings */
    public final static boolean DEBUG_MODE = false;
    public final static boolean SHOW_COORDINATES = true;
  
    public final static BotAlgorithm BOT_ALGORITHM = new GreedyAlgorithm();

    /* MCTS Configuration Settings */
    public final static int MCTS_SIMULATIONS = 50;
    public final static BotAlgorithm MCTS_SIMULATION_ALGORITHM = new SmartRandomAlgorithm();

    /* AlphaBeta Configuration Settings */
    public final static int ALPHABETA_TREE_DEPTH = 4;

    /* GUI Settings */
    public final static int HEXAGON_SIZE = 30;

    public final static Color LINE_COLOR = new Color(0, 0, 0);

    /* Tile Colors */
    public final static Color EMPTY_TILE_COLOR = new Color(255, 255, 255);
    public final static Color OCCUPIED_TILE_COLOR = new Color(0, 0, 0);
    public final static Color RED_TILE_COLOR = new Color(255, 0, 0);
    public final static Color BLUE_TILE_COLOR = new Color(0, 0, 255);
    public final static Color GREEN_TILE_COLOR = new Color(0, 255, 0);
    public final static Color ORANGE_TILE_COLOR = new Color(255, 150, 0);
    public final static Color YELLOW_TILE_COLOR = new Color(255, 255, 0);
    public final static Color PURPLE_TILE_COLOR = new Color(160, 32, 240);
}
