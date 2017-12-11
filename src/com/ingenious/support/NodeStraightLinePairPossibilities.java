package com.ingenious.support;

import com.ingenious.model.Board;
import com.ingenious.model.BoardNode;
import com.ingenious.model.Tile;

import java.util.ArrayList;

public class NodeStraightLinePairPossibilities {
    private BoardNode originalNode;
    private Board board;


    public NodeStraightLinePairPossibilities(BoardNode originalNode, Board board) {
        this.originalNode = originalNode;
        this.board = board;
    }

    public Tile getToPlaceTile() {
        return originalNode.getTile();
    }

    public ArrayList<NodePair> generateStraightLinePairs() {
        ArrayList<NodePair> pairs = new ArrayList<>();

        BoardNode north = board.getNorthNode(originalNode);
        BoardNode northWest = board.getNorthWestNode(originalNode);
        BoardNode northEast = board.getNorthEastNode(originalNode);
        BoardNode south = board.getSouthNode(originalNode);
        BoardNode southWest = board.getSouthWestNode(originalNode);
        BoardNode southEast = board.getSouthEastNode(originalNode);

        if (northWest != null && northWest.isAvailable()) {
            BoardNode northWestSecond = board.getNorthWestNode(northWest);
            if (northWestSecond != null && northWestSecond.isAvailable()) {
                pairs.add(new NodePair(northWest, northWestSecond, originalNode.getTile()));
            }
        }

        if (southWest != null && southWest.isAvailable()) {
            BoardNode southWestSecond = board.getSouthWestNode(southWest);
            if (southWestSecond != null && southWestSecond.isAvailable()) {
                pairs.add(new NodePair(southWest, southWestSecond, originalNode.getTile()));
            }
        }

        if (north != null && north.isAvailable()) {
            BoardNode northSecond = board.getNorthNode(north);
            if (northSecond != null && northSecond.isAvailable()) {
                pairs.add(new NodePair(north, northSecond, originalNode.getTile()));
            }
        }

        if (south != null && south.isAvailable()) {
            BoardNode southSecond = board.getSouthNode(south);
            if (southSecond != null && southSecond.isAvailable()) {
                pairs.add(new NodePair(south, southSecond, originalNode.getTile()));
            }
        }

        if (northEast != null && northEast.isAvailable()) {
            BoardNode northEastSecond = board.getNorthEastNode(northEast);
            if (northEastSecond != null && northEastSecond.isAvailable()) {
                pairs.add(new NodePair(northEast, northEastSecond, originalNode.getTile()));
            }
        }

        if (southEast != null && southEast.isAvailable()) {
            BoardNode southEastSecond = board.getSouthEastNode(southEast);
            if (southEastSecond != null && southEastSecond.isAvailable()) {
                pairs.add(new NodePair(southEast, southEastSecond, originalNode.getTile()));
            }
        }
        return pairs;
    }
}
