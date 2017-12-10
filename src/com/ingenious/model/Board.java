package com.ingenious.model; /**
 * Created by alexisguillot on 14/09/2017.
 */

import com.ingenious.config.Configuration;

import java.util.ArrayList;

public class Board {

    private ArrayList<BoardNode> boardNodes = new ArrayList<BoardNode>(); //This is the list containing the boardNodes
    private int[][] nodeCoord = new int[2 * (Configuration.boardWidth) - 1][2 * (Configuration.boardWidth) - 1];

    public Board() {
    }

    public int[][] getNodeCoord() {
        return nodeCoord;
    }

    private Board(ArrayList<BoardNode> ns, int[][] nc) {
        this.boardNodes = ns;
        this.nodeCoord = nc;
    }

    public ArrayList<BoardNode> getBoardNodes() {
        return boardNodes;
    }

    public boolean inBoard(int x, int y) {
        return getNode(x, y) != null;
    }

    /**
     * @param x
     * @param y
     * @return mixed
     * return node when it's found on the board else return null
     */
    public BoardNode getNode(int x, int y) {
        //ADDING THE WIDTH OF THE BOARD TO MAKE SURE THERE ARE NO NEGATIVE NUMBERS AS KEY IN THE ARRAY
        x = x + Configuration.boardWidth - 1;
        y = y + Configuration.boardWidth - 1;

        if (x < 0 || x > 2 * (Configuration.boardWidth - 1) || y < 0 || y > 2 * (Configuration.boardWidth - 1))
            return null;

        if (this.nodeCoord[x][y] == -1)
            return null;

        return boardNodes.get(this.nodeCoord[x][y]);
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

    public void addTile(Tile tile, BoardNode boardNode) {
        boardNode.setTile(tile);
    }

    public void setTile(int x, int y, Tile tile) {
        this.getNode(x, y).setTile(tile);
    }

    public Board getClone() {
        ArrayList<BoardNode> boardNodes = new ArrayList<>();

        for (BoardNode boardNode : this.boardNodes) {
            boardNodes.add(boardNode.getClone());
        }
        return new Board(boardNodes, this.nodeCoord.clone());
    }

    public ArrayList<BoardNode> getAvailableBoardNodes() {
        ArrayList<BoardNode> availableNodes = new ArrayList<>();

        for (BoardNode node : this.getBoardNodes()) {
            if (node.isAvailable())
                availableNodes.add(node);
        }
        return availableNodes;
    }


}
