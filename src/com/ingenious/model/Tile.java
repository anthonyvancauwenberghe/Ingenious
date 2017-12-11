package com.ingenious.model;

import com.ingenious.config.Configuration;

import java.awt.*;

public class Tile extends Color {

    private int tileId;

    public final static Tile empty = new Tile(0, Configuration.emptyTileColor);
    public final static Tile occupied = new Tile(1, Configuration.occupiedTileColor);

    public final static Tile red = new Tile(3, Configuration.redTileColor);
    public final static Tile blue = new Tile(7, Configuration.blueTileColor);
    public final static Tile green = new Tile(15, Configuration.greenTileColor);
    public final static Tile orange = new Tile(31, Configuration.orangeTileColor);
    public final static Tile yellow = new Tile(62, Configuration.yellowTileColor);
    public final static Tile purple = new Tile(123, Configuration.purpleTileColor);

    public Tile(int tileId, Color color) {
        super(color.getRed(), color.getGreen(), color.getBlue());
        this.tileId = tileId;
    }

    public boolean isEqual(Tile tile) {
        return this.tileId == tile.tileId;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Tile && this.getUniqueCode() == ((Tile) o).getUniqueCode();
    }

    public int getUniqueCode() {
        return this.tileId;
    }

    public boolean isAvailable() {
        return this.tileId == 0 || this.tileId == 1;
    }

    public boolean isOccupied() {
        return this.tileId == 1;
    }

    public boolean isEmpty() {
        return this.tileId == 0;
    }

    public boolean isRed() {
        return this.tileId == 3;
    }

    public boolean isBlue() {
        return this.tileId == 7;
    }

    public boolean isGreen() {
        return this.tileId == 15;
    }

    public boolean isOrange() {
        return this.tileId == 31;
    }

    public boolean isYellow() {
        return this.tileId == 62;
    }

    public boolean isPurple() {
        return this.tileId == 123;
    }
}
