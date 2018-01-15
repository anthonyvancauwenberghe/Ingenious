package com.ingenious.algorithm.support;

import com.ingenious.engine.Game;
import com.ingenious.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AllBaseMovesGenerator {

    private Game game;
    private ArrayList<Piece> rackPieces;

    public AllBaseMovesGenerator(Game game) {
        this.game = game.getClone();
        this.rackPieces = this.getNonDuplicateRackPieces(this.game.getCurrentPlayer().getRack().getClone());
    }

    public AllBaseMovesGenerator(Game game, Rack rack) {
        this.game = game.getClone();
        this.rackPieces = this.getNonDuplicateRackPieces(rack);
    }

    private ArrayList<Piece> getNonDuplicateRackPieces(Rack rack) {
        ArrayList<Piece> pieces = new ArrayList<>();

        boolean pieceIsNonDuplicate;
        for (Piece piece : rack.getPieces()) {
            pieceIsNonDuplicate = true;
            for (Piece aPiece : pieces) {
                if (piece.getUniqueCode() == aPiece.getUniqueCode()) {
                    pieceIsNonDuplicate = false;
                    break;
                }
            }
            if (pieceIsNonDuplicate) {
                pieces.add(piece);
            }
        }
        return pieces;
    }

    public Set<Move> generate() {
        HashSet<Move> moves = new HashSet<>();

        for (BoardNode boardNode : this.game.getBoard().getBoardNodes()) {
            if (boardNode.isEmpty()) {
                for (BoardNode neighbour : this.game.getBoard().getAvailableNeighboursOfNode(boardNode)) {
                    for (Piece piece : this.rackPieces) {
                        Move move = new Move(boardNode, neighbour, piece);
                        if (!moves.contains(move)) {
                            moves.add(move);
                        }
                        if (!piece.hasEqualTiles()) {
                            Move invertedMove = new Move(neighbour, boardNode, piece);
                            if (!moves.contains(move)) {
                                moves.add(invertedMove);
                            }
                        }

                    }

                }
            }
        }

        return moves;
    }
}
