package com.ingenious.config;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.algorithm.bot.impl.greedy.GreedyAlgorithm;

import java.awt.*;

public class Configuration {
    public final static int boardWidth = 6;
    public final static boolean showCoordinates = true;
    public final static int hexagonSize = 30;
    public final static boolean DEBUG_MODE = false;

//    public final static BotAlgorithm algorithm = new MCTSAlgorithm();
    public final static BotAlgorithm algorithm = new GreedyAlgorithm();

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
