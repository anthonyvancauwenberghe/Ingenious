package com.ingenious.model;

import java.util.concurrent.ThreadLocalRandom;

public class PieceTracker extends Bag {

    public Rack getRandomRack(Rack currentRack) {
        PieceTracker bag = this.getClone();

        for (Piece piece : currentRack.getPieces()) {
            bag.removePiece(piece);
        }

        Rack rack = new Rack();
        for (int i = 0; i < 6; i++) {

            if (bag.getPieces().size() > 0) {
                int randomIndex = ThreadLocalRandom.current().nextInt(0, bag.getPieces().size());
                Piece piece = bag.getPieces().get(randomIndex);
                rack.addPiece(piece);
            }
        }
        return rack;
    }

    public double chanceToGrabPiece(Piece piece) {
        int amountOfPieces = this.amountOfPiecesLeft();
        int amountOfSpecificPieces = this.amountOfSpecificPiecesLeft(piece);
        return amountOfSpecificPieces / amountOfPieces;
    }

    public PieceTracker getClone() {
        Bag bag = new Bag();

        for (Piece piece : this.getPieces()) {
            bag.addPiece(piece);
        }

        return (PieceTracker) bag;
    }
}
