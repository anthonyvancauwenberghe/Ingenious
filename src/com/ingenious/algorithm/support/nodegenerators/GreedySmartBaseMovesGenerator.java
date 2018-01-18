package com.ingenious.algorithm.support.nodegenerators;

import com.ingenious.engine.Game;
import com.ingenious.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GreedySmartBaseMovesGenerator {

    private Game game;
    private ArrayList<Piece> rackPieces;

    public GreedySmartBaseMovesGenerator(Game game) {
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

        for (BoardNode boardNode : this.game.getBoard().logic().getNeighboursOfFilledNodes()) {
            if (boardNode.isEmpty()) {
                for (BoardNode neighbour : this.game.getBoard().logic().getEmptyNeighboursOfNode(boardNode)) {
                    for (Piece piece : this.rackPieces) {

                        Move move = new Move(boardNode, neighbour, piece);
                        if (!moves.contains(move) && this.neighbourContainsTile(boardNode, piece.getHead())) {
                            moves.add(move);
                        }
                        if (!piece.hasEqualTiles() && this.neighbourContainsTile(boardNode, piece.getTail())) {
                            Move invertedMove = new Move(neighbour, boardNode, piece);
                            if (!moves.contains(move)) {
                                moves.add(invertedMove);
                            }
                        }

                    }

                }
            }
        }
        if (moves.size() == 0) {
            SmartBaseMovesGenerator generator = new SmartBaseMovesGenerator(this.game);
            moves = (HashSet<Move>) generator.generate();
            System.out.println("ERROR didn't find greedy moves to generate. Using SmartBaseMoveGenerator!");
        }

        return moves;
    }

    private boolean neighbourContainsTile(BoardNode node, Tile tile) {
        for (BoardNode neighbour : this.game.getBoard().logic().getNeighboursOfNode(node)) {
            if (neighbour.getTile().isEqual(tile))
                return true;
        }
        return false;
    }
}
