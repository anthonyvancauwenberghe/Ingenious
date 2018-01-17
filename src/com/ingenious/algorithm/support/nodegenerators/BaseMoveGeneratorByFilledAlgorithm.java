package com.ingenious.algorithm.support.nodegenerators;

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
        Rack rack = game.getCurrentPlayer().getRack().getClone();
        System.out.println(rack.toString());

        this.rackPieces = this.getNonDuplicateRackPieces(rack);
        System.out.println("rack contains: " + rackPieces.size());
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
        //System.out.println("Rack contains " + pieces.size() + " non duplicate pieces");
        return pieces;
    }


    public Set<Move> generate() {
        ArrayList<BoardNode> filledNodes = getFilledBoardNodes();
        HashSet<BoardNode> neighbouringFilledNodes = getAvailableNeighboursOfNode(filledNodes);
        return generateMovesFromNodes(neighbouringFilledNodes);
    }

    private HashSet<Move> generateMovesFromNodes(HashSet<BoardNode> neighbouringFilledNodes) {
        HashSet<Move> moves = new HashSet<>();
        HashSet<BoardNode> nodeHashSet = new HashSet<>();
        /* GET NEIGHBOUR NODES OF THE NEIGHBOURING FILLED NODES AND FOR EACH ONE OF THEM GENERATE MOVES */
        for (BoardNode filledNode : neighbouringFilledNodes) {
            for (BoardNode neighbour : this.board.getAvailableNeighboursOfNode(filledNode)) {
                if (!nodeHashSet.contains(neighbour)) {
                    nodeHashSet.add(neighbour);
                    generateAllMovesFromNode(moves, filledNode, neighbour);
                }

            }
        }
        return moves;
    }

    private void generateAllMovesFromNode(HashSet<Move> moves, BoardNode boardNode, BoardNode neighbour) {
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

    private HashSet<BoardNode> getAvailableNeighboursOfNode(ArrayList<BoardNode> filledNodes) {
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
        return neighbouringFilledNodes;
    }

    private ArrayList<BoardNode> getFilledBoardNodes() {
        /* GET ALL FILLED NODES AND STORE IN ARRAYLIST */
        ArrayList<BoardNode> filledNodes = new ArrayList<>();
        for (BoardNode node : board.getBoardNodes()) {
            if (!node.isAvailable())
                filledNodes.add(node);
        }
        return filledNodes;
    }

}
