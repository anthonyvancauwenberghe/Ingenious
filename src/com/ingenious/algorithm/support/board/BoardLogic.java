package com.ingenious.algorithm.support.board;

import com.ingenious.config.Configuration;
import com.ingenious.model.Board;
import com.ingenious.model.BoardNode;
import com.ingenious.model.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BoardLogic {
    private Board board;

    private ArrayList<BoardNode> emptyNodes;
    private ArrayList<BoardNode> filledNodes;

    private boolean updateList = false;

    public BoardLogic(Board board) {
        this.board = board;
        this.updateEmptyNodeList();
    }

    public void updateEmptyNodeList() {
        this.updateList = true;
    }

    public void regenerateEmptyNodeList() {
        ArrayList<BoardNode> emptyNodes = new ArrayList<>();
        ArrayList<BoardNode> filledNodes = new ArrayList<>();
        for (int x = 0; x < board.getNodeCoord().length; x++) {
            for (int y = 0; y < board.getNodeCoord()[x].length; y++) {
                int offset = Configuration.BOARD_WIDTH - 1;
                if (board.getNodeCoord()[x][y] != -1 && board.getNodeCoord()[x][y] == Configuration.EMPTY_TILE_ID)
                    emptyNodes.add(board.getBoardNodeFromCoordinate(x - offset, y - offset));
                else if (board.getNodeCoord()[x][y] != -1 && board.getNodeCoord()[x][y] != Configuration.EMPTY_TILE_ID)
                    filledNodes.add(board.getBoardNodeFromCoordinate(x - offset, y - offset));
            }
        }
        this.filledNodes = filledNodes;
        this.emptyNodes = emptyNodes;
    }

    private int getArrayXCoordinate(int x) {
        return x + Configuration.BOARD_WIDTH - 1;
    }

    private int getArrayYCoordinate(int y) {
        return y + Configuration.BOARD_WIDTH - 1;
    }

    public BoardNode getNode(int x, int y) {
        //ADDING THE WIDTH OF THE BOARD TO MAKE SURE THERE ARE NO NEGATIVE NUMBERS AS KEY IN THE ARRAY
        int shiftedX = getArrayXCoordinate(x);
        int shiftedY = getArrayXCoordinate(y);

        if (shiftedX < 0 || shiftedX > 2 * (Configuration.BOARD_WIDTH - 1) || shiftedY < 0 || shiftedY > 2 * (Configuration.BOARD_WIDTH - 1))
            return null;

        if (board.getNodeCoord()[shiftedX][shiftedY] == -1)
            return null;

        return new BoardNode(x, y, new Tile(board.getNodeCoord()[shiftedX][shiftedY]));
    }

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

    public ArrayList<BoardNode> getEmptyNeighboursOfNode(BoardNode boardNode) {
        ArrayList<BoardNode> neighbours = new ArrayList<>();

        BoardNode north = this.getNorthNode(boardNode);
        BoardNode northWest = this.getNorthWestNode(boardNode);
        BoardNode northEast = this.getNorthEastNode(boardNode);
        BoardNode south = this.getSouthNode(boardNode);
        BoardNode southWest = this.getSouthWestNode(boardNode);
        BoardNode southEast = this.getSouthEastNode(boardNode);


        if (northWest != null && northWest.isEmpty()) {
            neighbours.add(northWest);
        }

        if (southWest != null && southWest.isEmpty()) {
            neighbours.add(southWest);
        }

        if (north != null && north.isEmpty()) {
            neighbours.add(north);
        }

        if (south != null && south.isEmpty()) {
            neighbours.add(south);
        }

        if (northEast != null && northEast.isEmpty()) {
            neighbours.add(northEast);
        }

        if (southEast != null && southEast.isEmpty()) {
            neighbours.add(southEast);
        }

        return neighbours;
    }

    public boolean nodeHasAllEmptyNeighbours(BoardNode node) {
        return getNeighboursOfNode(node).size() == getEmptyNeighboursOfNode(node).size();
    }

    public boolean nodeHasNoAvailableNeighbours(BoardNode node) {
        return getEmptyNeighboursOfNode(node).size() == 0;
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

    public Set<BoardNode> getNeighboursOfFilledNodes() {
        HashSet<BoardNode> neighbours = new HashSet<>();

        for (BoardNode filledNode : this.getFilledNodes()) {
            for (BoardNode neighbour : this.getNeighboursOfNode(filledNode)) {
                if (!neighbours.contains(neighbour)) {
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }


    public ArrayList<BoardNode> getEmptyNodes() {
        if (this.updateList)
            this.regenerateEmptyNodeList();

        updateList = false;
        return emptyNodes;
    }

    public ArrayList<BoardNode> getFilledNodes() {
        if (this.updateList)
            this.regenerateEmptyNodeList();

        updateList = false;
        return filledNodes;
    }
}
