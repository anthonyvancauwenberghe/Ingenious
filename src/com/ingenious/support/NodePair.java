package com.ingenious.support;

import com.ingenious.model.BoardNode;
import com.ingenious.model.Tile;

public class NodePair {
    private BoardNode firstNode;
    private BoardNode secondNode;
    private Tile scoreTile;

    public NodePair(BoardNode firstNode, BoardNode secondNode, Tile scoreTile) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.scoreTile = scoreTile;
    }

    public BoardNode getFirstNode() {
        return firstNode;
    }

    public BoardNode getSecondNode() {
        return secondNode;
    }

    public Tile getScoreTile() {
        return scoreTile;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof NodePair && this.firstNode.equals(((NodePair) o).firstNode) && this.secondNode.equals(((NodePair) o).secondNode) && this.scoreTile.equals(((NodePair) o).scoreTile);
    }
}
