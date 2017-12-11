package com.ingenious.algorithm.support;

import com.ingenious.model.Board;
import com.ingenious.model.BoardNode;
import com.ingenious.model.Tile;

import java.util.ArrayList;

public class NodeProfile {
    public final BoardNode node;
    public final Tile tile;

    public Board board;

    public NodeProfile(BoardNode node, Board board) {
        this.node = node;
        this.tile = node.getTile();
        this.board = board;
    }

    private ArrayList<BoardNode> getNeigbhours(ArrayList<BoardNode> neigbhours) {
        return this.board.getNeighboursOfNode(this.node);
    }

}
