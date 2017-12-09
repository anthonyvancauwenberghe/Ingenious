package com.ingenious.models;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {

    private ArrayList<Piece> pieces = new ArrayList<>();

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public Piece getPiece(Piece piece) {
        for (Piece aPiece : pieces) {
            if (aPiece.getUniqueCode() == piece.getUniqueCode()) {
                return aPiece;
            }
        }
        System.out.println("ERROR! couldn't find the piece in the bag");
        return null;
    }

    public Piece getAndRemoveRandomPiece() {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, this.pieces.size());
        Piece piece = this.pieces.get(randomIndex);
        this.pieces.remove(randomIndex);
        return piece;
    }

    public void removePiece(Piece piece) {
        for (Piece aPiece : pieces) {
            if (aPiece.getUniqueCode() == piece.getUniqueCode()) {
                pieces.remove(pieces.indexOf(aPiece));
                return;
            }
        }
        System.out.println("ERROR! couldn't remove the piece in the bag. Piece is not in the bag");
    }

    public boolean isEmpty() {
        return this.pieces.isEmpty();
    }

    public Bag getClone() {
        Bag bag = new Bag();

        for (Piece piece : this.pieces) {
            bag.addPiece(piece);
        }

        return bag;
    }
}
