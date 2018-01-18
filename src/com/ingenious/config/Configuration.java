package com.ingenious.config;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.algorithm.bot.impl.expectiminimax.ExpectiMiniMaxAlgorithm;
import com.ingenious.algorithm.bot.impl.greedy.GreedyAlgorithm;
import com.ingenious.algorithm.bot.impl.mcts.MCTSAlgorithm;

import java.awt.*;

public class Configuration {

    /* Game Settings */
    public final static int BOARD_WIDTH = 6;

    /* Debug Settings */
    public final static boolean DEBUG_MODE = false;
    public final static boolean SHOW_COORDINATES = true;
    public final static BotAlgorithm BOT_ALGORITHM = new ExpectiMiniMaxAlgorithm();

    /* MCTS Configuration Settings */
    public final static int MCTS_SIMULATIONS = 5;
    public final static BotAlgorithm MCTS_SIMULATION_ALGORITHM = new GreedyAlgorithm();

    /* AlphaBeta Configuration Settings */
    public final static int MINIMAX_TREE_DEPTH = 3;
    public final static boolean USE_BASE_MINIMAX = false;

    /* GUI Settings */
    public final static int HEXAGON_SIZE = 30;

    public final static Color LINE_COLOR = new Color(0, 0, 0);

    /* Tile Colors */
    public final static Color OUTSIDE_TILE_COLOR = new Color(11, 255, 233);
    public final static Color EMPTY_TILE_COLOR = new Color(255, 255, 255);
    public final static Color OCCUPIED_TILE_COLOR = new Color(0, 0, 0);
    public final static Color RED_TILE_COLOR = new Color(255, 0, 0);
    public final static Color BLUE_TILE_COLOR = new Color(0, 0, 255);
    public final static Color GREEN_TILE_COLOR = new Color(0, 255, 0);
    public final static Color ORANGE_TILE_COLOR = new Color(255, 150, 0);
    public final static Color YELLOW_TILE_COLOR = new Color(255, 255, 0);
    public final static Color PURPLE_TILE_COLOR = new Color(160, 32, 240);

    /* Tile Identification Numbers
    * WARNING DO NOT CHANGE.
    * CALCULATIONS DEPEND ON THESE NUMBERS
    */
    public final static int EMPTY_TILE_ID = 0;
    public final static int OCCUPIED_TILE_ID = 1;
    public final static int RED_TILE_ID = 3;
    public final static int BLUE_TILE_ID = 7;
    public final static int GREEN_TILE_ID = 15;
    public final static int ORANGE_TILE_ID = 31;
    public final static int YELLOW_TILE_ID = 62;
    public final static int PURPLE_TILE_ID = 123;
}
