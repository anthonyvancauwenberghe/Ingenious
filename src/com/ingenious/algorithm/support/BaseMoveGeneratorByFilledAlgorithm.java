package com.ingenious.algorithm.support;

import com.ingenious.engine.Game;
import com.ingenious.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BaseMoveGeneratorByFilledAlgorithm {

    private Board board;
    private ArrayList<Piece> rackPieces;

    public BaseMoveGeneratorByFilledAlgorithm(Game game) {
        this.board = game.getBoard().getClone();
        this.rackPieces = this.getNonDuplicateRackPieces(game.getCurrentPlayer().getRack().getClone());
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

        /* GET ALL FILLED NODES AND STORE IN ARRAYLIST */
        ArrayList<BoardNode> filledNodes = new ArrayList<>();
        for (BoardNode node : board.getBoardNodes()) {
            if (!node.isAvailable())
                filledNodes.add(node);
        }

        /* GET ALL AVAILABLE NEIGHBOURS OF THE FILLED NODES AND STORE IN HASHSET */
        HashSet<BoardNode> neighbouringFilledNodes = new HashSet<>();
        for (BoardNode filledNode : filledNodes) {
            for (BoardNode neighbourFilledNode : this.board.getNeighboursOfNode(filledNode)) {
                if (neighbourFilledNode.isAvailable()) {
                    if (!neighbouringFilledNodes.contains(neighbourFilledNode)) {
                        neighbouringFilledNodes.add(neighbourFilledNode);
                    }
                }
            }

        }

        for (BoardNode boardNode : neighbouringFilledNodes) {
            for (BoardNode neighbour : this.board.getAvailableNeighboursOfNode(boardNode)) {
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
        return moves;
    }

}
