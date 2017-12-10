package com.ingenious.algorithm.support;

import com.ingenious.model.Board;
import com.ingenious.model.BoardNode;

public class ThirdDegreeIsolationCheckAlgorithm {
    private Board board;
    private BoardNode node;

    public ThirdDegreeIsolationCheckAlgorithm(Board board, BoardNode node) {
        this.board = board;
        this.node = node;
    }

    /* IMPLEMENTED LIKE THIS FOR MAXIMUM PERFORMANCE!! */
    public boolean execute() {
        BoardNode north = this.board.getNorthNode(this.node);
        BoardNode northWest = this.board.getNorthWestNode(this.node);
        BoardNode northEast = this.board.getNorthEastNode(this.node);
        BoardNode south = this.board.getSouthNode(this.node);
        BoardNode southWest = this.board.getSouthWestNode(this.node);
        BoardNode southEast = this.board.getSouthEastNode(this.node);

        if (north != null
                && !north.isAvailable()
                && this.board.getNorthNode(north) != null
                && !this.board.getNorthNode(north).isAvailable()) {
            return false;
        }

        if (northWest != null
                && !northWest.isAvailable()
                && this.board.getNorthNode(northWest) != null
                && !this.board.getNorthNode(northWest).isAvailable()
                && this.board.getNorthWestNode(northWest) != null
                && !this.board.getNorthWestNode(northWest).isAvailable()) {
            return false;
        }

        if (northEast != null
                && !northEast.isAvailable()
                && this.board.getNorthNode(northEast) != null
                && !this.board.getNorthNode(northEast).isAvailable()
                && this.board.getNorthEastNode(northEast) != null
                && !this.board.getNorthEastNode(northEast).isAvailable()) {
            return false;
        }

        if (south != null
                && !south.isAvailable()
                && this.board.getSouthNode(south) != null
                && !this.board.getSouthNode(south).isAvailable()) {
            return false;
        }

        if (southWest != null
                && !southWest.isAvailable()
                && this.board.getSouthNode(southWest) != null
                && !this.board.getSouthNode(southWest).isAvailable()
                && this.board.getSouthWestNode(southWest) != null
                && !this.board.getSouthWestNode(southWest).isAvailable()) {
            return false;
        }

        if (southEast != null
                && !southEast.isAvailable()
                && this.board.getSouthNode(southEast) != null
                && !this.board.getSouthNode(southEast).isAvailable()
                && !this.board.getSouthEastNode(southEast).isAvailable()) {
            return false;
        }

        return true;
    }


}
