package com.ingenious.models;

public class Move {

    private Piece piece;

    private BoardNode firstNode;
    private BoardNode secondNode;

    private boolean inverted;

    public Move(BoardNode firstNode, BoardNode secondNode, Piece piece, boolean inverted) {
        this.piece = piece;
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.inverted = inverted;
    }

    public Piece getPiece() {
        return piece;
    }

    public BoardNode getFirstNode() {
        return firstNode;
    }

    public BoardNode getSecondNode() { return secondNode;}

    public boolean isInverted() {
        return inverted;
    }

    public String toString(){
        return this.piece.getHead().toString() + " " + this.piece.getTail().toString()
                + " " + this.firstNode.getX() + this.firstNode.getY() + " "
                + this.secondNode.getX() + this.secondNode.getY() + " "
                + this.inverted;
    }
}
