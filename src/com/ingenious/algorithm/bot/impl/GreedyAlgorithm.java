package com.ingenious.algorithm.bot.impl;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.engine.logic.calculation.ScoreCalculatorLogic;
import com.ingenious.model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by carolley on 12-Dec-17.
 */
public class GreedyAlgorithm extends BotAlgorithm{

    private Game game;
    private int [] addscore;

    public GreedyAlgorithm(Game game){
        this.game = game;
    }

    public ArrayList<Move> getAvailableMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        ArrayList<BoardNode> nodes = game.getBoard().getBoardNodes();
        Board board = game.getBoard();
        ArrayList<Piece> pieces = game.getCurrentPlayer().getRack().getPieces();
        for(int i=0; i<nodes.size(); i++){
            if(nodes.get(i).isEmpty()){
                ArrayList<BoardNode> neighbours = board.getAvailableNeighboursOfNode(nodes.get(i));
                for(int j=0; j<neighbours.size(); j++){
                    for(int k=0; k<pieces.size(); k++){
                        moves.add(new Move(nodes.get(i), neighbours.get(j), pieces.get(k)));
                    }
                }
            }
        }
        return moves;
    }

    public Move[] getMoves(){
        Move[] moves = new Move[6];
        ArrayList<Move> available = getAvailableMoves();
        int[] score = {0,0,0,0,0,0};
        for(int i=0; i<available.size(); i++){
            Move move = available.get(i);
            ScoreCalculatorLogic calculator = new ScoreCalculatorLogic(this.game, move);
            int head = calculator.getScoreStreakHeadPiece();
            Tile headc = move.getPiece().getHead();
            int tail = calculator.getScoreStreakTailPiece();
            Tile tailc = move.getPiece().getTail();
            if(score[getIndex(headc)]<head){
                moves[getIndex(headc)] = move;
                this.addscore[getIndex(headc)] = head;
            }
            if(score[getIndex(tailc)]<tail){
                this.addscore[getIndex(tailc)] = tail;
                moves[getIndex(tailc)] = move;
            }
        }
        return moves;
    }


    public int getIndex(Tile color){
        if(color.equals(Tile.red)){
            return 0;
        }
        if(color.equals(Tile.green)){
            return 1;
        }
        if(color.equals(Tile.blue)){
            return 2;
        }
        if(color.equals(Tile.orange)){
            return 3;
        }
        if(color.equals(Tile.yellow)){
            return 4;
        }
        if(color.equals(Tile.purple)){
            return 5;
        }
        return -1;
    }


    @Override
    protected Move execute(Game game){
        Move[] moves = getMoves();
        Tile lowest = this.game.getCurrentPlayer().getScore().sort()[0];
        Move move = moves[getIndex(lowest)];
        int [] score = this.game.getCurrentPlayer().getScore().getScores();
        for(int i=0; i<6; i++){
            if(score[i]!= 18 && score[i] + this.addscore[i] >= 18){
                return moves[i];
            }
        }
        return move;
    }
}
