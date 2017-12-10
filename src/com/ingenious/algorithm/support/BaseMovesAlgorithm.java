package com.ingenious.algorithm.support;

import com.ingenious.engine.Game;
import com.ingenious.model.*;

import java.util.ArrayList;

public class BaseMovesAlgorithm {

    private Board board;
    private ArrayList<Piece> rackPieces;

    public BaseMovesAlgorithm(Game game) {
        this.board = game.getBoard().getClone();
        this.rackPieces = this.getNonDuplicateRackPieces(game.getCurrentPlayer().getRack().getClone());
        this.applyHeuristics();
    }

    public BaseMovesAlgorithm(Game game, boolean applyHeuristics) {
        this.board = game.getBoard().getClone();
        this.rackPieces = this.getNonDuplicateRackPieces(game.getCurrentPlayer().getRack().getClone());
        if (applyHeuristics)
            this.applyHeuristics();
    }

    private boolean nodeIsThirdDegreeIsolated(BoardNode boardNode) {
        ThirdDegreeIsolationCheckAlgorithm isolationCheckAlgorithm = new ThirdDegreeIsolationCheckAlgorithm(this.board, boardNode);
        return isolationCheckAlgorithm.execute();
    }

    private void applyHeuristics() {
        // long startTime = System.nanoTime();
        this.fillWorthlessnodes();
        //long endTime = System.nanoTime();
        // double timeDifference = ((double) endTime - (double) startTime) / 1000000;
        // System.out.println("Applying heuristics to movegenerator Took " + timeDifference + " ms");
    }

    private void fillWorthlessnodes() {
        for (BoardNode node : this.board.getBoardNodes()) {
            if (!node.isAvailable() || nodeIsThirdDegreeIsolated(node)) {
                node.setTile(Tile.occupied);
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

    public ArrayList<Move> generate() {
        long startTime = System.nanoTime();
        ArrayList<Move> moves = new ArrayList<>();

        for (BoardNode boardNode : this.board.getBoardNodes()) {
            if (boardNode.isEmpty()) {
                for (BoardNode neighbour : this.board.getAvailableNeighboursOfNode(boardNode)) {
                    for (Piece piece : this.rackPieces) {
                        moves.add(new Move(boardNode, neighbour, piece));

                        if (!piece.hasEqualTiles()) {
                            moves.add(new Move(neighbour, boardNode, piece));
                        }
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        double timeDifference = ((double) endTime - (double) startTime) / 1000000;
        System.out.println("Generating " + moves.size() + " moves took " + timeDifference + " ms");
        return moves;
    }
}
