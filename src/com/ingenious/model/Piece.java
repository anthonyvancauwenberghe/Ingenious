package com.ingenious.model;


public class Piece {

    public final static Piece RED_BLUE = new Piece(Tile.red, Tile.blue,"RED_BLUE");
    public final static Piece RED_GREEN = new Piece(Tile.red, Tile.green, "RED_GREEN");
    public final static Piece RED_ORANGE = new Piece(Tile.red, Tile.orange, "RED_ORANGE");
    public final static Piece RED_YELLOW = new Piece(Tile.red, Tile.yellow, "RED_YELLOW");
    public final static Piece RED_PURPLE = new Piece(Tile.red, Tile.purple, "RED_PURPLE");
    public final static Piece RED_RED = new Piece(Tile.red, Tile.red, "RED_RED");

    public final static Piece BLUE_GREEN = new Piece(Tile.blue, Tile.green, "BLUE_GREEN");
    public final static Piece BLUE_ORANGE = new Piece(Tile.blue, Tile.orange, "BLUE_ORANGE");
    public final static Piece BLUE_YELLOW = new Piece(Tile.blue, Tile.yellow, "BLUE_YELLOW");
    public final static Piece BLUE_PURPLE = new Piece(Tile.blue, Tile.purple, "BLUE_PURPLE");
    public final static Piece BLUE_BLUE = new Piece(Tile.blue, Tile.blue, "BLUE_BLUE");

    public final static Piece GREEN_ORANGE = new Piece(Tile.green, Tile.orange, "GREEN_ORANGE");
    public final static Piece GREEN_YELLOW = new Piece(Tile.green, Tile.yellow, "GREEN_YELLOW");
    public final static Piece GREEN_PURPLE = new Piece(Tile.green, Tile.purple, "GREEN_PURPLE");
    public final static Piece GREEN_GREEN = new Piece(Tile.green, Tile.green, "GREEN_GREEN");

    public final static Piece ORANGE_YELLOW = new Piece(Tile.orange, Tile.yellow, "ORANGE_YELLOW");
    public final static Piece ORANGE_PURPLE = new Piece(Tile.orange, Tile.purple, "ORANGE_PURPLE");
    public final static Piece ORANGE_ORANGE = new Piece(Tile.orange, Tile.orange, "ORANGE_ORANGE");

    public final static Piece YELLOW_PURPLE = new Piece(Tile.yellow, Tile.purple, "YELLOW_PURPLE");
    public final static Piece YELLOW_YELLOW = new Piece(Tile.yellow, Tile.yellow, "YELLOW_YELLOW");

    public final static Piece PURPLE_PURPLE = new Piece(Tile.purple, Tile.purple, "PURPLE_PURPLE");

    private Tile head;
    private Tile tail;
    private String name;

    private Piece(Tile head, Tile tail, String name) {
        this.head = head;
        this.tail = tail;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ":" + this.getUniqueCode();
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

    public boolean containsTile(Tile tile) {
        return this.getHead().isEqual(tile) || this.getTail().isEqual(tile);
    }

    public boolean isEqual(Piece piece) {
        return this.getUniqueCode() == piece.getUniqueCode();
    }

}
