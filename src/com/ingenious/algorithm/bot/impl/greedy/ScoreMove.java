package com.ingenious.algorithm.bot.impl.greedy;

import com.ingenious.model.Move;
import com.ingenious.model.Score;
import com.ingenious.model.Tile;

public class ScoreMove {
    public Move move;
    public int score;
    public Tile tile;

    public ScoreMove(Move move, int score, Tile tile) {
        this.move = move;
        this.score = score;
        this.tile = tile;
    }
}
