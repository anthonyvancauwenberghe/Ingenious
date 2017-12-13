package com.ingenious.algorithm.bot.impl.greedy;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.algorithm.support.AllBaseMovesGenerator;
import com.ingenious.engine.Game;
import com.ingenious.engine.logic.calculation.ScoreCalculatorLogic;
import com.ingenious.model.Move;
import com.ingenious.model.Score;
import com.ingenious.model.Tile;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by carolley on 12-Dec-17.
 */
public class GreedyAlgorithm extends BotAlgorithm {

    public Set<Move> getAvailableMoves(Game game) {
        AllBaseMovesGenerator generator = new AllBaseMovesGenerator(game);
        return generator.generate();
    }

    public ArrayList<ScoreMove> generateScoreMoves(Game game, Set<Move> allMoves) {
        ArrayList<ScoreMove> scoreMoves = new ArrayList<>();
        for (Move aMove : allMoves) {
            if(aMove.getPiece().hasEqualTiles()){
                ScoreCalculatorLogic calc = new ScoreCalculatorLogic(game,aMove);
                int scoreHead = calc.getScoreStreakHeadPiece();
                int scoreTail = calc.getScoreStreakTailPiece();
                Tile color = aMove.getPiece().getHead();
                ScoreMove scoreMove = new ScoreMove(aMove, scoreHead, scoreTail, color, color);
                scoreMoves.add(scoreMove);
            }
            else{
                ScoreCalculatorLogic calc = new ScoreCalculatorLogic(game, aMove);
                int score1 = calc.getScoreStreakHeadPiece();
                int score2 = calc.getScoreStreakTailPiece();
                Tile color1 = aMove.getPiece().getHead();
                Tile color2 = aMove.getPiece().getTail();
                ScoreMove scoreMove1 = new ScoreMove(aMove, score1, score2, color1, color2);
               // ScoreMove scoreMove2 = new ScoreMove(aMove, score2, color2);
                scoreMoves.add(scoreMove1);
                //scoreMoves.add(scoreMove2);
            }
        }
        return scoreMoves;
    }

    public Move getGlobalHigh(ArrayList<ScoreMove> scoreMoves){
        Move move = scoreMoves.get(0).getMove();
        ScoreMove scoreMove = scoreMoves.get(0);
        for(ScoreMove aScoreMove : scoreMoves){
            if(scoreMove.getScore()<aScoreMove.getScore()){
                move = aScoreMove.getMove();
                scoreMove = aScoreMove;
            }
        }
        return move;
    }

    public Move getBestMoveColor(Tile tile, ArrayList<ScoreMove> scoreMoves){
        return null;
    }


    public Move selectBestMove(Game game, ArrayList<ScoreMove> scoreMoves) {
       if(!playHighest(game)){
           Tile[] sorted = game.getCurrentPlayer().getScore().sort();

           for(Tile color: sorted){
            return getBestMoveColor(color, scoreMoves);
           }
       }
            return getGlobalHigh(scoreMoves);
    }


    public Move[] getMoves(Game game) {
        ArrayList<ScoreMove> scoreMoves = new ArrayList<>();

        Move[] moves = new Move[7];
        int[] score = {0, 0, 0, 0, 0, 0, 0};
        for (Move move : this.getAvailableMoves(game)) {
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

    public boolean playHighest(Game game) {
        Tile[] sorted = game.getCurrentPlayer().getScore().sort();
        int low = game.getCurrentPlayer().getScore().getScore(sorted[0]);
        int high = game.getCurrentPlayer().getScore().getScore(sorted[5]);
        if (high <= (low * 2)) {
            return true;
        }
        return false;
    }

    public Move getLowPlay(Move[] moves, Game game) {
        Tile[] sorted = game.getCurrentPlayer().getScore().sort();
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
        Game simulatedGame = game.getClone();
        Set<Move> allMoves = this.getAvailableMoves(simulatedGame);
        ArrayList<ScoreMove> scoreMoves = this.generateScoreMoves(simulatedGame, allMoves);
        return selectBestMove(simulatedGame, scoreMoves);
    }
}
