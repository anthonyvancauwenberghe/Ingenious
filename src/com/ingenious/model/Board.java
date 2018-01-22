package com.ingenious.model;
/*
 * Created by alexisguillot on 14/09/2017.
 */

import com.ingenious.algorithm.support.board.BoardLogic;
import com.ingenious.config.Configuration;

import java.util.ArrayList;

public class Board {

    private int[][] nodeCoord = new int[2 * (Configuration.BOARD_WIDTH) - 1][2 * (Configuration.BOARD_WIDTH) - 1];
    private BoardLogic logic;

    public Board() {
    }

    public int[][] getNodeCoord() {
        return nodeCoord;
    }

    private Board(int[][] nc) {
        this.nodeCoord = nc;
    }

    @Deprecated
    public ArrayList<BoardNode> getBoardNodes() {
        ArrayList<BoardNode> nodes = new ArrayList<>();
        for (int x = 0; x < getNodeCoord().length; x++) {
            for (int y = 0; y < getNodeCoord()[x].length; y++) {
                int offset = Configuration.BOARD_WIDTH - 1;
                if (this.nodeCoord[x][y] != -1)
                    nodes.add(getBoardNodeFromCoordinate(x - offset, y - offset));
            }
        }
        return nodes;
    }

    public boolean inBoard(int x, int y) {
        return getTileIdFromCoordinate(x, y) != -1;
    }

    public BoardNode getBoardNodeFromCoordinate(int x, int y) {
        return new BoardNode(x, y, getTileFromCoordinate(x, y));
    }

    public Tile getTileFromCoordinate(int x, int y) {
        int tileId = getTileIdFromCoordinate(x, y);
        if (tileId == -1)
            return null;
        return new Tile(tileId);
    }

    public int getTileIdFromCoordinate(int x, int y) {
        int offset = Configuration.BOARD_WIDTH - 1;
        return this.nodeCoord[x + offset][y + offset];
    }

    /**
     * @deprecated
     */
    public BoardNode getNode(int x, int y) {
        //ADDING THE WIDTH OF THE BOARD TO MAKE SURE THERE ARE NO NEGATIVE NUMBERS AS KEY IN THE ARRAY
        int shiftedX = x + Configuration.BOARD_WIDTH - 1;
        int shiftedY = y + Configuration.BOARD_WIDTH - 1;

        if (shiftedX < 0 || shiftedX > 2 * (Configuration.BOARD_WIDTH - 1) || shiftedY < 0 || shiftedY > 2 * (Configuration.BOARD_WIDTH - 1))
            return null;

        if (this.nodeCoord[shiftedX][shiftedY] == -1)
            return null;

        return new BoardNode(x, y, getTileFromCoordinate(x, y));
    }

    public boolean nodeHasAllEmptyNeighbours(BoardNode boardNode) {
        for (BoardNode node : this.getNeighboursOfNode(boardNode)) {
            if (!node.isEmpty())
                return false;
        }
        return true;
    }

    public BoardNode getNorthNode(BoardNode node) {
        return this.getNode(node.getX(), node.getY() - 1);
    }

    public BoardNode getNorthWestNode(BoardNode node) {
        return this.getNode(node.getX() - 1, node.getY());
    }

    public BoardNode getNorthEastNode(BoardNode node) {
        return this.getNode(node.getX() + 1, node.getY() - 1);
    }

    public BoardNode getSouthNode(BoardNode node) {
        return this.getNode(node.getX(), node.getY() + 1);
    }

    public BoardNode getSouthWestNode(BoardNode node) {
        return this.getNode(node.getX() - 1, node.getY() + 1);
    }

    public BoardNode getSouthEastNode(BoardNode node) {
        return this.getNode(node.getX() + 1, node.getY());
    }

    @Deprecated
    public ArrayList<BoardNode> getNeighboursOfNode(BoardNode boardNode) {
        ArrayList<BoardNode> neighbours = new ArrayList<BoardNode>();

        BoardNode north = this.getNorthNode(boardNode);
        BoardNode northWest = this.getNorthWestNode(boardNode);
        BoardNode northEast = this.getNorthEastNode(boardNode);
        BoardNode south = this.getSouthNode(boardNode);
        BoardNode southWest = this.getSouthWestNode(boardNode);
        BoardNode southEast = this.getSouthEastNode(boardNode);


        if (northWest != null) {
            neighbours.add(northWest);
        }

        if (southWest != null) {
            neighbours.add(southWest);
        }

        if (north != null) {
            neighbours.add(north);
        }

        if (south != null) {
            neighbours.add(south);
        }

        if (northEast != null) {
            neighbours.add(northEast);
        }

        if (southEast != null) {
            neighbours.add(southEast);
        }

        return neighbours;
    }

    public ArrayList<BoardNode> getSameColorNeighbours(BoardNode boardNode) {
        ArrayList<BoardNode> neighbours = new ArrayList<BoardNode>();

        BoardNode north = this.getNorthNode(boardNode);
        BoardNode northWest = this.getNorthWestNode(boardNode);
        BoardNode northEast = this.getNorthEastNode(boardNode);
        BoardNode south = this.getSouthNode(boardNode);
        BoardNode southWest = this.getSouthWestNode(boardNode);
        BoardNode southEast = this.getSouthEastNode(boardNode);


        if (northWest != null) {
            neighbours.add(northWest);
        }

        if (southWest != null) {
            neighbours.add(southWest);
        }

        if (north != null) {
            neighbours.add(north);
        }

        if (south != null) {
            neighbours.add(south);
        }

        if (northEast != null) {
            neighbours.add(northEast);
        }

        if (southEast != null) {
            neighbours.add(southEast);
        }

        return neighbours;
    }

    public boolean hasFilledNeighbour(BoardNode boardNode) {
        BoardNode north = this.getNorthNode(boardNode);
        BoardNode northWest = this.getNorthWestNode(boardNode);
        BoardNode northEast = this.getNorthEastNode(boardNode);
        BoardNode south = this.getSouthNode(boardNode);
        BoardNode southWest = this.getSouthWestNode(boardNode);
        BoardNode southEast = this.getSouthEastNode(boardNode);

        if (northWest != null && !northWest.isAvailable()) {
            return true;
        }

        if (southWest != null && !southWest.isAvailable()) {
            return true;
        }

        if (north != null && !north.isAvailable()) {
            return true;
        }

        if (south != null && !south.isAvailable()) {
            return true;
        }

        if (northEast != null && !northEast.isAvailable()) {
            return true;
        }

        if (southEast != null && !southEast.isAvailable()) {
            return true;
        }
        return false;
    }

    public ArrayList<BoardNode> getAvailableNeighboursOfNode(BoardNode boardNode) {
        ArrayList<BoardNode> neighbours = new ArrayList<>();

        BoardNode north = this.getNorthNode(boardNode);
        BoardNode northWest = this.getNorthWestNode(boardNode);
        BoardNode northEast = this.getNorthEastNode(boardNode);
        BoardNode south = this.getSouthNode(boardNode);
        BoardNode southWest = this.getSouthWestNode(boardNode);
        BoardNode southEast = this.getSouthEastNode(boardNode);


        if (northWest != null && northWest.isAvailable()) {
            neighbours.add(northWest);
        }

        if (southWest != null && southWest.isAvailable()) {
            neighbours.add(southWest);
        }

        if (north != null && north.isAvailable()) {
            neighbours.add(north);
        }

        if (south != null && south.isAvailable()) {
            neighbours.add(south);
        }

        if (northEast != null && northEast.isAvailable()) {
            neighbours.add(northEast);
        }

        if (southEast != null && southEast.isAvailable()) {
            neighbours.add(southEast);
        }

        return neighbours;
    }

    public boolean isNeighbour(BoardNode boardNode1, BoardNode boardNode2) {
        if (boardNode1 == null || boardNode2 == null)
            return false;

        return this.getNeighboursOfNode(boardNode1).contains(boardNode2);
    }

    public void setTile(int x, int y, Tile tile) {
        int offset = Configuration.BOARD_WIDTH - 1;
        this.nodeCoord[x + offset][y + offset] = tile.getUniqueCode();
        this.logic().updateEmptyNodeList();
    }

    public BoardLogic logic() {
        if (this.logic == null)
            this.logic = new BoardLogic(this);

        return this.logic;
    }

    public Board getClone() {
        int[][] coords = new int[2 * (Configuration.BOARD_WIDTH) - 1][2 * (Configuration.BOARD_WIDTH) - 1];
        for (int x = 0; x < getNodeCoord().length; x++) {
            for (int y = 0; y < getNodeCoord()[x].length; y++) {
                coords[x][y] = getNodeCoord()[x][y];
            }
        }
        return new Board(coords);
    }

}
