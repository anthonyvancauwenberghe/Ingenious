package com.ingenious.config;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.algorithm.bot.impl.greedy.GreedyAlgorithm;
import com.ingenious.algorithm.bot.impl.mcts.MCTSAlgorithm;
import com.ingenious.algorithm.bot.impl.random.RandomAlgorithm;
import com.ingenious.algorithm.bot.impl.random.SmartRandomAlgorithm;

import java.awt.*;

public class Configuration {
    public final static int boardWidth = 6;
    public final static boolean showCoordinates = false;
    public final static int hexagonSize = 30;
    public final static boolean DEBUG_MODE = false;

//    public final static BotAlgorithm algorithm = new MCTSAlgorithm();
    public final static BotAlgorithm algorithm = new GreedyAlgorithm();

    public final static int MCTS_SIMULATIONS = 50;
    public final static BotAlgorithm MCTS_SIMULATION_ALGORITHM = new SmartRandomAlgorithm();

    public final static Color LineColor = new Color(0, 0, 0);

    public final static Color emptyTileColor = new Color(255, 255, 255);
    public final static Color occupiedTileColor = new Color(0, 0, 0);

    public final static Color redTileColor = new Color(255, 0, 0);
    public final static Color blueTileColor = new Color(0, 0, 255);
    public final static Color greenTileColor = new Color(0, 255, 0);
    public final static Color orangeTileColor = new Color(255, 150, 0);
    public final static Color yellowTileColor = new Color(255, 255, 0);
    public final static Color purpleTileColor = new Color(160, 32, 240);
}
