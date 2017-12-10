package com.ingenious.model;

public class BoardNode {
    public int x;
    public int y;

    private Tile tile;

    public BoardNode(int x, int y, Tile tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

    public BoardNode getClone() {
        return new BoardNode(this.x, this.y, this.tile);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getCoord() {
        return new int[]{x, y};
    }

    public boolean isOccupied() {
        return tile.isOccupied();
    }

    public boolean isEmpty() {
        return tile.isEmpty();
    }

    public boolean isAvailable() {
        return tile.isAvailable();
    }

    public Tile getTile() {
        return this.tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void removeTile() {
        this.tile = Tile.empty;
    }

    public boolean isEqual(BoardNode node) {
        return this.x == node.getX() && this.y == node.y;
    }

}
