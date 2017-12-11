package com.ingenious.algorithm.support;

import com.ingenious.engine.Game;
import com.ingenious.model.*;
import com.ingenious.support.NodePair;
import com.ingenious.support.NodeStraightLinePairPossibilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StraightLineMoveGenerator {

    private Board board;
    private ArrayList<Piece> rackPieces;

    private ArrayList<Piece> redPieces = new ArrayList<>();
    private ArrayList<Piece> bluePieces = new ArrayList<>();
    private ArrayList<Piece> greenPieces = new ArrayList<>();
    private ArrayList<Piece> orangePieces = new ArrayList<>();
    private ArrayList<Piece> yellowPieces = new ArrayList<>();
    private ArrayList<Piece> purplePieces = new ArrayList<>();

    public StraightLineMoveGenerator(Game game) {
        this.board = game.getBoard().getClone();
        Rack rack = game.getCurrentPlayer().getRack().getClone();
        //System.out.println("rack contains: " + rack.getPieces().size());
        this.rackPieces = this.getNonDuplicateRackPieces(rack);
        this.generateColorPiecesLists(this.rackPieces);
    }

    private void generateColorPiecesLists(ArrayList<Piece> pieces) {
        for (Piece piece : pieces) {
            if (piece.containsTile(Tile.red))
                redPieces.add(piece);
            if (piece.containsTile(Tile.blue))
                bluePieces.add(piece);
            if (piece.containsTile(Tile.green))
                greenPieces.add(piece);
            if (piece.containsTile(Tile.orange))
                orangePieces.add(piece);
            if (piece.containsTile(Tile.yellow))
                yellowPieces.add(piece);
            if (piece.containsTile(Tile.purple))
                purplePieces.add(piece);
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
        //System.out.println("Rack contains " + pieces.size() + " non duplicate pieces");
        return pieces;
    }

    public Set<Move> generate() {
        ArrayList<BoardNode> filledNodes = getFilledBoardNodes();
        HashSet<NodePair> straightLinePairs = getStraightLinePairsFromFilledNodes(filledNodes);
        return pairsToMoves(straightLinePairs);
    }

    public ArrayList<Piece> getRackPiecesThatContainTile(Tile tile) {
        if (tile.isRed()) {
            return redPieces;
        }
        if (tile.isBlue()) {
            return bluePieces;
        }
        if (tile.isGreen()) {
            return greenPieces;
        }
        if (tile.isOrange()) {
            return orangePieces;
        }
        if (tile.isYellow()) {
            return yellowPieces;
        }
        if (tile.isPurple()) {
            return purplePieces;
        }
        return new ArrayList<>();

    }

    public HashSet<Move> pairsToMoves(Iterable<NodePair> pairs) {
        HashSet<Move> moves = new HashSet<>();
        Move move;
        for (NodePair pair : pairs) {
            for (Piece piece : this.getRackPiecesThatContainTile(pair.getScoreTile())) {
                if (piece.getHead().isEqual(pair.getScoreTile())) {
                    move = new Move(pair.getFirstNode(), pair.getSecondNode(), piece);
                    if (!moves.contains(move)) {
                        moves.add(move);
                    }
                } else if (piece.getTail().isEqual(pair.getScoreTile())) {
                    move = new Move(pair.getSecondNode(), pair.getFirstNode(), piece);
                    if (!moves.contains(move)) {
                        moves.add(move);
                    }
                }

            }
        }
        return moves;
    }

    private HashSet<NodePair> getStraightLinePairsFromFilledNodes(ArrayList<BoardNode> filledNodes) {
        /* GET ALL AVAILABLE NEIGHBOURS OF THE FILLED NODES AND STORE IN HASHSET */
        HashSet<NodePair> straightLinePairs = new HashSet<>();
        for (BoardNode filledNode : filledNodes) {
            NodeStraightLinePairPossibilities generator = new NodeStraightLinePairPossibilities(filledNode, this.board);
            for (NodePair pair : generator.generateStraightLinePairs()) {
                if (!straightLinePairs.contains(pair)) {
                    straightLinePairs.add(pair);
                }
            }
        }
        return straightLinePairs;
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
