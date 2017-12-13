package com.ingenious.algorithm.bot.impl;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.engine.logic.calculation.ScoreCalculatorLogic;
import com.ingenious.model.*;
import com.ingenious.provider.GameProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by carolley on 12-Dec-17.
 */
public class GreedyAlgorithm extends BotAlgorithm {


    public GreedyAlgorithm() {
    }

    public Game getGame() {
        return GameProvider.getInstance().game;
    }

    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        Game game = getGame();
        ArrayList<BoardNode> nodes = game.getBoard().getBoardNodes();
        Board board = game.getBoard();
        ArrayList<Piece> pieces = game.getCurrentPlayer().getRack().getPieces();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).isEmpty()) {
                ArrayList<BoardNode> neighbours = board.getAvailableNeighboursOfNode(nodes.get(i));
                for (int j = 0; j < neighbours.size(); j++) {
                    for (int k = 0; k < pieces.size(); k++) {

                        moves.add(new Move(nodes.get(i), neighbours.get(j), pieces.get(k)));
                    }
                }
            }
        }
        System.out.println("amount of moves generated" + moves.size());
        return moves;
    }

    public Move[] getMoves() {
        Game game = GameProvider.getInstance().game;
        Move[] moves = new Move[7];
        ArrayList<Move> available = getAvailableMoves();
        int[] score = {0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < available.size(); i++) {
            Move move = available.get(i);
            ScoreCalculatorLogic calculator = new ScoreCalculatorLogic(game, move);
            int head = calculator.getScoreStreakHeadPiece();
            Tile headc = move.getPiece().getHead();
            int tail = calculator.getScoreStreakTailPiece();
            Tile tailc = move.getPiece().getTail();
            if (score[6] < head + tail) {
                score[6] = head + tail;
                moves[6] = move;
            }
            if(!move.getPiece().hasEqualTiles()) {
                if (score[getIndex(headc)] < head) {
                    moves[getIndex(headc)] = move;
                }
                if (score[getIndex(tailc)] < tail) {
                    moves[getIndex(tailc)] = move;
                }
            }
            else{
                if(score[getIndex(headc)] < head+tail){
                    score[getIndex(headc)] = head+tail;
                    moves[getIndex(headc)] = move;
                }
            }
        }
        return moves;
    }

    public boolean playHighest() {
        Game game = GameProvider.getInstance().game;
        Tile[] sorted = game.getCurrentPlayer().getScore().sort();
        int low = game.getCurrentPlayer().getScore().getScore(sorted[0]);
        int high = game.getCurrentPlayer().getScore().getScore(sorted[5]);
        if (high <= (low * 2)) {
            return true;
        }
        return false;
    }

    public Move getLowPlay(Move[] moves) {
        Tile[] sorted = GameProvider.getInstance().game.getCurrentPlayer().getScore().sort();
        for (int i = 0; i < 6; i++) {
            if (moves[getIndex(sorted[i])] != null) {
                System.out.println("MOVE NOT NULL");

                return moves[i];
            }
        }
        return moves[6];
    }

    public int getIndex(Tile color) {
        if (color.equals(Tile.red)) {
            return 0;
        }
        if (color.equals(Tile.green)) {
            return 1;
        }
        if (color.equals(Tile.blue)) {
            return 2;
        }
        if (color.equals(Tile.orange)) {
            return 3;
        }
        if (color.equals(Tile.yellow)) {
            return 4;
        }
        if (color.equals(Tile.purple)) {
            return 5;
        }
        return -1;
    }


    @Override
    protected Move execute(Game game) {
        Move[] moves = getMoves();
        if (!playHighest()) {
            return getLowPlay(moves);
        } else {
            return moves[6];
        }
    }
}
