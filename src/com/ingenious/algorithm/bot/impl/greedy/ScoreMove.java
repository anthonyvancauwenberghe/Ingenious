package com.ingenious.algorithm.bot.impl.greedy;

import com.ingenious.model.Move;
import com.ingenious.model.Tile;

public class ScoreMove {
    public Move move;
    public int scoreHead;
    public int scoreTail;
    public Tile head;
    public Tile tail;

    public ScoreMove(Move move, int scoreHead, int scoreTail, Tile head, Tile tail) {
        this.move = move;
        this.head = head;
        this.tail = tail;
    }

    public int getTotalScore(){
        return this.scoreHead + scoreTail;
    }

    public int getScoreHead(){
        return this.scoreHead;
    }

    public int getScoreTail(){
        return scoreTail;
    }

    public Tile[] getColor(){
        Tile [] colors = {head,tail};
        return colors;
    }

    public Move getMove(){
        return this.move;
    }
}
