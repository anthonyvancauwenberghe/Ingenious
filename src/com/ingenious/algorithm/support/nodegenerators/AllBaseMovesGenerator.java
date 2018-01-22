package com.ingenious.algorithm.support.nodegenerators;

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
        this.rackPieces = this.getNonDuplicateRackPieces(this.game.getCurrentPlayer().getRack());
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

        for (BoardNode boardNode : this.game.getBoard().logic().getEmptyNodes()) {
            for (BoardNode neighbour : this.game.getBoard().logic().getEmptyNeighboursOfNode(boardNode)) {
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
        return getNonDuplicateMoves(moves);
    }

    private Set<Move> getNonDuplicateMoves(HashSet<Move> list)
    {
        HashSet<Move> moves = new HashSet<>();

        boolean moveIsNonDuplicate;
        for (Move move : list) {
            moveIsNonDuplicate = true;
            for (Move aMove : moves) {
                if (move.equals(aMove)) {
                    moveIsNonDuplicate = false;
                    break;
                }
            }
            if (moveIsNonDuplicate) {
                moves.add(move);
            }
        }
        return moves;
    }
}
