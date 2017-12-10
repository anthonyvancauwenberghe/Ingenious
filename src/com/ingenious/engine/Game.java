package com.ingenious.engine;

import com.ingenious.engine.logic.calculation.impl.ScoreCalculatorLogic;
import com.ingenious.engine.logic.game.BoardMovePlacementGameLogic;
import com.ingenious.model.Bag;
import com.ingenious.model.Board;
import com.ingenious.model.Move;
import com.ingenious.model.Tile;
import com.ingenious.model.players.Player;
import com.ingenious.model.players.impl.Bot;
import com.ingenious.model.players.impl.Human;
import com.ingenious.provider.GameProvider;

import java.util.ArrayList;

public class Game {
    private Board board;
    private ArrayList<Player> players;
    private Bag bag;

    private int currentPlayerIndex = 0;
    public int bonusPlay = 0;

    public Game(Board board, ArrayList<Player> players, Bag bag) {
        this.board = board;
        this.players = players;
        this.bag = bag;
    }

    public Board getBoard() {
        return board;
    }

    public Bag getBag() {
        return bag;
    }

    public void gameLoop() {

    }

    public void swap() {
        if(canSwap()){
            for(int i=0; i<6; i++){
                this.getBag().addPiece(getCurrentPlayer().getRack().getPieces().get(i));
                getCurrentPlayer().getRack().getPieces().set(i,getBag().getAndRemoveRandomPiece());
            }
        }
    }

    public void refresh(){
        while(getCurrentPlayer().getRack().getPieces().size()!=6){
            getCurrentPlayer().getRack().addPiece(getBag().getAndRemoveRandomPiece());
        }
        GameProvider.updateGraphics();
    }

    public boolean canSwap(){
        Tile[] score = getCurrentPlayer().getScore().sort();
        int min = getCurrentPlayer().getScore().getScore(score[0]);
        Tile minC = score[0];
        for(int i=0; i<score.length; i++){
            if(getCurrentPlayer().getScore().getScore(score[i]) > min){
                break;
            }
            if(!getCurrentPlayer().getRack().contains(score[i])){
                return true;
            }
        }
        return false;
    }

    public void executeMove(Move move) {
        BoardMovePlacementGameLogic placeMove = new BoardMovePlacementGameLogic(this, move);

        /* Execute move on board if it is valid */
        if (!placeMove.execute())
            return;

        System.out.println(this.getBoard().getNode(1, 4).getTile().toString() + "," + this.getBoard().getNode(2, 3).getTile().toString());

        /* Remove piece from currentplayer rack */
        this.getCurrentPlayer().rack.removePiece(move.getPiece());

        /* Calculate current player score */
        ScoreCalculatorLogic scoreCalculator = new ScoreCalculatorLogic(this, move);
        scoreCalculator.calculate();

        if (this.bonusPlay > 0)
            this.bonusPlay--;

        GameProvider.updateGraphics();

        if(this.bonusPlay == 0 || getCurrentPlayer().getRack().getPieces().size()==0){
            setNextPlayerAsCurrent();
        }

        if (this.getCurrentPlayer() instanceof Bot) {
            Move botMove = ((Bot) this.getCurrentPlayer()).getMove(this);
            this.executeMove(botMove);
        }

    }

    public void setNextPlayerAsCurrent() {
        this.setBonusPlay(0);
        refresh();
        if (this.currentPlayerIndex == 1) {
            this.currentPlayerIndex = 0;
        } else {
            this.currentPlayerIndex++;
        }
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public Player getOtherPlayer() {
        if (currentPlayerIndex == 0)
            return getPlayers().get(1);
        else {
            return getPlayers().get(0);
        }
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public int getBonusPlay() {
        return bonusPlay;
    }

    public void setBonusPlay(int bonusPlay) {
        this.bonusPlay = bonusPlay;
    }

    public Game getClone() {
        ArrayList<Player> players = new ArrayList<>();
        for (Player player : this.getPlayers()) {
            if (player instanceof Human) {
                players.add(((Human) player).getClone());
            } else if (player instanceof Bot) {
                players.add(((Bot) player).getClone());
            }
        }
        return new Game(this.board.getClone(), players, this.bag.getClone());
    }


}
