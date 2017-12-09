package com.ingenious.model;

public class Move {

    private Piece piece;

    private BoardNode headNode;
    private BoardNode tailNode;

    public Move(BoardNode topNode, BoardNode bottomNode, Piece piece) {
        this.piece = piece;
        this.headNode = topNode;
        this.tailNode = bottomNode;
    }

    public Piece getPiece() {
        return piece;
    }

    public BoardNode getHeadNode() {
        return headNode;
    }

    public BoardNode getTailNode() { return tailNode;}


    public String toString(){
        return this.piece.getHead().toString() + " " + this.piece.getTail().toString()
                + " " + this.headNode.getX() + this.headNode.getY() + " "
                + this.tailNode.getX() + this.tailNode.getY() + " ";
    }
}
