package com.ingenious.model;


import java.util.ArrayList;

public class Rack {

    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private int indexSelected = -1;

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public Rack getClone() {
        Rack rack = new Rack();
        for (Piece piece : this.pieces) {
            rack.addPiece(piece);
        }
        return rack;
    }

    public void setPieceSelected(int i) {
        this.indexSelected = i;
    }

    public int getIndexSelected() {
        return this.indexSelected;
    }

    public Piece getPieceSelected() {
        if (selected())
            return this.pieces.get(indexSelected);

        return null;
    }

    public boolean selected() {
        return this.indexSelected != -1;
    }

    public ArrayList<Piece> getPieces() {
        return this.pieces;
    }

    public boolean isEmpty() {
        return this.getPieces().isEmpty();
    }

    public boolean removePiece(Piece piece) {
        int i = 0;
        for (Piece aPiece : this.pieces) {
            if (aPiece.isEqual(piece)) {
                this.pieces.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }
}