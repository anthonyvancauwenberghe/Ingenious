package com.ingenious.models;


public class Piece {

    public final static Piece RED_BLUE = new Piece(Tile.red, Tile.blue);
    public final static Piece RED_GREEN = new Piece(Tile.red, Tile.green);
    public final static Piece RED_ORANGE = new Piece(Tile.red, Tile.orange);
    public final static Piece RED_YELLOW = new Piece(Tile.red, Tile.yellow);
    public final static Piece RED_PURPLE = new Piece(Tile.red, Tile.purple);
    public final static Piece RED_RED = new Piece(Tile.red, Tile.red);

    public final static Piece BLUE_GREEN = new Piece(Tile.blue, Tile.green);
    public final static Piece BLUE_ORANGE = new Piece(Tile.blue, Tile.orange);
    public final static Piece BLUE_YELLOW = new Piece(Tile.blue, Tile.yellow);
    public final static Piece BLUE_PURPLE = new Piece(Tile.blue, Tile.purple);
    public final static Piece BLUE_BLUE = new Piece(Tile.blue, Tile.blue);

    public final static Piece GREEN_ORANGE = new Piece(Tile.green, Tile.orange);
    public final static Piece GREEN_YELLOW = new Piece(Tile.green, Tile.yellow);
    public final static Piece GREEN_PURPLE = new Piece(Tile.green, Tile.purple);
    public final static Piece GREEN_GREEN = new Piece(Tile.green, Tile.green);

    public final static Piece ORANGE_YELLOW = new Piece(Tile.orange, Tile.yellow);
    public final static Piece ORANGE_PURPLE = new Piece(Tile.orange, Tile.purple);
    public final static Piece ORANGE_ORANGE = new Piece(Tile.orange, Tile.orange);
    public final static Piece YELLOW_PURPLE = new Piece(Tile.yellow, Tile.purple);
    public final static Piece YELLOW_YELLOW = new Piece(Tile.yellow, Tile.yellow);

    public final static Piece PURPLE_PURPLE = new Piece(Tile.purple, Tile.purple);

    private Tile head;
    private Tile tail;

    private Piece(Tile head, Tile tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public String toString() {
        return head.toString() + "_" + tail.toString();
    }

    public int getUniqueCode() {
        return tail.getUniqueCode() + head.getUniqueCode();
    }

    public Tile getHead() {
        return head;
    }

    public Tile getTail() {
        return tail;
    }

    public boolean hasEqualTiles() {
        return head.isEqual(tail);
    }

    public boolean isEqual(Piece piece) {
        return this.getUniqueCode() == piece.getUniqueCode();
    }

}
