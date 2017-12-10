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


    @Override
    public boolean equals(Object o) {
        if (o instanceof Move) {
            Move move = (Move) o;
            return this.piece.getHead().isEqual(move.getPiece().getHead())
                    && this.piece.getTail().isEqual(move.getPiece().getTail())
                    && this.headNode.isEqual(move.getHeadNode())
                    && this.tailNode.isEqual(move.getTailNode());
        } else
            return false;
    }

    public boolean isEqual(Move move) {
        return this.piece.isEqual(move.getPiece()) && this.headNode.isEqual(move.getHeadNode()) && this.tailNode.isEqual(move.tailNode);
    }

    public String toString(){
        return this.piece.getHead().toString() + " " + this.piece.getTail().toString()
                + " " + this.headNode.getX() + this.headNode.getY() + " "
                + this.tailNode.getX() + this.tailNode.getY() + " ";
    }
}
