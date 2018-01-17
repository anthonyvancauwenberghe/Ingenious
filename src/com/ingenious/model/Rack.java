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

    public void printRackPieces(){
        for(Piece piece : this.pieces){
            System.out.println(piece.toString());
        }
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

    public void resetSelectedPiece()
    {
        this.indexSelected = -1;
    }

    public ArrayList<Piece> getPieces() {
        return this.pieces;
    }

    public boolean isEmpty() {
        return this.getPieces().isEmpty();
    }

    public boolean removePiece(Piece piece) {
        for (int index = 0; index < this.getPieces().size(); index++) {
            //System.out.println("comparing " + piece.getUniqueCode() + " with " + this.pieces.get(index).getUniqueCode());
            if (this.pieces.get(index).isEqual(piece)) {
                this.pieces.remove(index);
                return true;
            }
        }
        System.out.println("Error: Did not found piece to remove in rack");
        return false;
    }

    public boolean contains(Tile color){
        for(int i=0; i<this.getPieces().size(); i++){
            if(this.pieces.get(i).getHead().equals(color)){
                return true;
            }
            if(this.pieces.get(i).getTail().equals(color)){
                return true;
            }
        }
        return false;
    }
}