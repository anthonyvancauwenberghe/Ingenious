package com.ingenious.model;

import com.ingenious.config.Configuration;

import java.awt.*;

public class Tile extends Color {

    private int tileId;

    public final static Tile empty = new Tile(Configuration.EMPTY_TILE_ID);
    public final static Tile occupied = new Tile(Configuration.OCCUPIED_TILE_ID);
    public final static Tile red = new Tile(Configuration.RED_TILE_ID);
    public final static Tile blue = new Tile(Configuration.BLUE_TILE_ID);
    public final static Tile green = new Tile(Configuration.GREEN_TILE_ID);
    public final static Tile orange = new Tile(Configuration.ORANGE_TILE_ID);
    public final static Tile yellow = new Tile(Configuration.YELLOW_TILE_ID);
    public final static Tile purple = new Tile(Configuration.PURPLE_TILE_ID);

    public Tile(int tileId) {
        super(getColorFromId(tileId).getRed(), getColorFromId(tileId).getGreen(), getColorFromId(tileId).getBlue());
        this.tileId = tileId;
    }

    public static Color getColorFromId(int tileId) {
        if (tileId == Configuration.EMPTY_TILE_ID)
            return Configuration.EMPTY_TILE_COLOR;

        if (tileId == Configuration.OCCUPIED_TILE_ID)
            return Configuration.OCCUPIED_TILE_COLOR;

        if (tileId == Configuration.RED_TILE_ID)
            return Configuration.RED_TILE_COLOR;

        if (tileId == Configuration.BLUE_TILE_ID)
            return Configuration.BLUE_TILE_COLOR;

        if (tileId == Configuration.GREEN_TILE_ID)
            return Configuration.GREEN_TILE_COLOR;

        if (tileId == Configuration.ORANGE_TILE_ID)
            return Configuration.ORANGE_TILE_COLOR;

        if (tileId == Configuration.YELLOW_TILE_ID)
            return Configuration.YELLOW_TILE_COLOR;

        if (tileId == Configuration.PURPLE_TILE_ID)
            return Configuration.PURPLE_TILE_COLOR;

        return Configuration.OUTSIDE_TILE_COLOR;
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
        return this.tileId == Configuration.EMPTY_TILE_ID || this.tileId == Configuration.OCCUPIED_TILE_ID;
    }

    public boolean isOccupied() {
        return this.tileId == Configuration.OCCUPIED_TILE_ID;
    }

    public boolean isEmpty() {
        return this.tileId == Configuration.EMPTY_TILE_ID;
    }

    public boolean isRed() {
        return this.tileId == Configuration.RED_TILE_ID;
    }

    public boolean isBlue() {
        return this.tileId == Configuration.BLUE_TILE_ID;
    }

    public boolean isGreen() {
        return this.tileId == Configuration.GREEN_TILE_ID;
    }

    public boolean isOrange() {
        return this.tileId == Configuration.ORANGE_TILE_ID;
    }

    public boolean isYellow() {
        return this.tileId == Configuration.YELLOW_TILE_ID;
    }

    public boolean isPurple() {
        return this.tileId == Configuration.PURPLE_TILE_ID;
    }

    public String getName()
    {
        switch (this.tileId)
        {
            case Configuration.OCCUPIED_TILE_ID:
                return "Occupied";
            case Configuration.EMPTY_TILE_ID:
                return "Empty";
            case Configuration.RED_TILE_ID:
                return "Red";
            case Configuration.BLUE_TILE_ID:
                return "Blue";
            case Configuration.GREEN_TILE_ID:
                return "Green";
            case Configuration.ORANGE_TILE_ID:
                return "Orange";
            case Configuration.YELLOW_TILE_ID:
                return "Yellow";
            case Configuration.PURPLE_TILE_ID:
                return "Purple";
        }
        return null;
    }
}
