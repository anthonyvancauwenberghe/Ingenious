package com.ingenious.algorithm.bot.impl.greedy;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.algorithm.support.AllBaseMovesGenerator;
import com.ingenious.engine.Game;
import com.ingenious.engine.logic.calculation.ScoreCalculatorLogic;
import com.ingenious.model.Move;
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
                int score = calc.getScoreStreakHeadPiece() + calc.getScoreStreakTailPiece();
                Tile color = aMove.getPiece().getHead();
                ScoreMove scoreMove = new ScoreMove(aMove, score, color);
                if (score > 0)
                    scoreMoves.add(scoreMove);
            }
            else{
                ScoreCalculatorLogic calc = new ScoreCalculatorLogic(game, aMove);
                int score1 = calc.getScoreStreakHeadPiece();
                int score2 = calc.getScoreStreakTailPiece();
                Tile color1 = aMove.getPiece().getHead();
                Tile color2 = aMove.getPiece().getTail();
                ScoreMove scoreMove1 = new ScoreMove(aMove, score1, color1);
                ScoreMove scoreMove2 = new ScoreMove(aMove, score2, color2);
                if (score1 > 0)
                    scoreMoves.add(scoreMove1);
                if (score2 > 0)
                    scoreMoves.add(scoreMove2);
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
        ScoreMove highestScoreMove = null;
        for (ScoreMove aScoreMove : scoreMoves) {
            if (aScoreMove.tile.equals(tile)) {
                if (highestScoreMove == null) {
                    highestScoreMove = aScoreMove;
                } else {
                    if (highestScoreMove.score < aScoreMove.score)
                        highestScoreMove = aScoreMove;
                }
            }
        }
        return highestScoreMove.move;
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

    public boolean playHighest(Game game) {
        Tile[] sorted = game.getCurrentPlayer().getScore().sort();
        int low = game.getCurrentPlayer().getScore().getScore(sorted[0]);
        int high = game.getCurrentPlayer().getScore().getScore(sorted[5]);
        if (high <= (low * 2)) {
            return true;
        }
        return false;
    }


    @Override
    public Move execute(Game game) {
        Game simulatedGame = game.getClone();
        Set<Move> allMoves = this.getAvailableMoves(simulatedGame);
        ArrayList<ScoreMove> scoreMoves = this.generateScoreMoves(simulatedGame, allMoves);
        return selectBestMove(simulatedGame, scoreMoves);
    }
}
