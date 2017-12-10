package com.ingenious.algorithm.support;

import com.ingenious.engine.Game;
import com.ingenious.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BaseMovesAlgorithm {

    private Game game;
    private ArrayList<Piece> rackPieces;
    private boolean applyHeuristics;

    public BaseMovesAlgorithm(Game game) {
        this.game = game.getClone();
        this.rackPieces = this.getNonDuplicateRackPieces(this.game.getCurrentPlayer().getRack().getClone());
        this.applyHeuristics = true;
    }

    public BaseMovesAlgorithm(Game game, boolean applyHeuristics) {
        this.game = game.getClone();
        this.rackPieces = this.getNonDuplicateRackPieces(game.getCurrentPlayer().getRack().getClone());
        this.applyHeuristics = applyHeuristics;
    }

    private boolean nodeIsThirdDegreeIsolated(BoardNode boardNode) {
        ThirdDegreeIsolationCheckAlgorithm isolationCheckAlgorithm = new ThirdDegreeIsolationCheckAlgorithm(this.game.getBoard(), boardNode);
        return isolationCheckAlgorithm.execute();
    }

    private void applyHeuristics() {
        long startTime = System.nanoTime();
        this.fillWorthlessnodes();
        long endTime = System.nanoTime();
        double timeDifference = ((double) endTime - (double) startTime) / 1000000;
        System.out.println("Applying heuristics to movegenerator Took " + timeDifference + " ms");
    }

    private void fillWorthlessnodes() {
        for (BoardNode node : this.game.getBoard().getBoardNodes()) {
            if (node.isAvailable()) {
                boolean available = node.isAvailable();
                boolean isolated = nodeIsThirdDegreeIsolated(node);
                if (!available
                        || isolated) {
                    node.setTile(Tile.occupied);
                }
            }
        }
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
                        if (this.applyHeuristics && (this.game.getBoard().hasFilledNeighbour(boardNode) || this.game.getBoard().hasFilledNeighbour(neighbour))){
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
                        } else if (!this.applyHeuristics) {
                            moves.add(new Move(boardNode, neighbour, piece));
                            if (!piece.hasEqualTiles()) {
                                moves.add(new Move(neighbour, boardNode, piece));
                            }
                        }

                    }
                }
            }
        }

        return moves;
    }
}
