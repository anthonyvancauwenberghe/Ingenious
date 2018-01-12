package com.ingenious.engine;

import com.ingenious.config.Configuration;
import com.ingenious.engine.logic.calculation.ScoreCalculatorLogic;
import com.ingenious.engine.logic.game.BoardMovePlacementGameLogic;
import com.ingenious.engine.logic.game.GameOverLogic;
import com.ingenious.model.Bag;
import com.ingenious.model.Board;
import com.ingenious.model.Move;
import com.ingenious.model.Tile;
import com.ingenious.model.players.Player;
import com.ingenious.model.players.impl.Bot;
import com.ingenious.model.players.impl.Human;
import com.ingenious.provider.GameProvider;

import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private Board board;
    private ArrayList<Player> players;
    private Bag bag;
    private Player winner;

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

        /* Execute move on board */
        placeMove.execute();

        /* Remove piece from currentplayer rack */
        this.getCurrentPlayer().rack.removePiece(move.getPiece());

        /* Calculate current player score */
        ScoreCalculatorLogic scoreCalculator = new ScoreCalculatorLogic(this, move);
        scoreCalculator.execute();

        if(gameOver()){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "GAME OVER");
        }
        else{
            if (this.bonusPlay > 0)
                this.bonusPlay--;


            if(this.bonusPlay == 0 || getCurrentPlayer().getRack().getPieces().size()==0){
                setNextPlayerAsCurrent();
            }

            GameProvider.updateGraphics();
            if (this.getCurrentPlayer() instanceof Bot) {
                Move botMove = ((Bot) this.getCurrentPlayer()).getMove(this);
                this.doSimulationMove(botMove);
                GameProvider.updateGraphics();
            }
        }


    }

    public void doSimulationMove(Move move) {
        BoardMovePlacementGameLogic placeMove = new BoardMovePlacementGameLogic(this, move);

        /* Execute move on board */
        placeMove.execute();

        /* Remove piece from currentplayer rack */
        this.getCurrentPlayer().rack.removePiece(move.getPiece());

        /* Calculate current player score */
        ScoreCalculatorLogic scoreCalculator = new ScoreCalculatorLogic(this, move);
        scoreCalculator.execute();

        if (this.bonusPlay > 0)
            this.bonusPlay--;

        if (this.bonusPlay == 0 || getCurrentPlayer().getRack().getPieces().size() == 0) {
            setNextPlayerAsCurrent();
        }
    }

    public void end(){
        if (Configuration.DEBUG_MODE) {
            if (this.winner.equals(null)) {
                System.out.println("IS A DRAW");
            } else {
                System.out.println("WINNER IS: " + this.winner);
            }
        }
    }
    public boolean gameOver(){
        GameOverLogic logic = new GameOverLogic(this);
        if(won()){
            this.winner = getCurrentPlayer();
            return true;
        }
        if(logic.noMovesLeft()){
            this.winner = getWinner();
            return true;
        }
        return false;
    }

    public Player getWinner(){
        for(int i=0; i<6; i++){
            Tile current_low = getCurrentPlayer().getScore().sort()[i];
            Tile other_low = getOtherPlayer().getScore().sort()[i];
            if(getCurrentPlayer().getScore().getScore(current_low)<getOtherPlayer().getScore().getScore(other_low)){
                return  getOtherPlayer();
            }
            if(getCurrentPlayer().getScore().getScore(current_low)>getOtherPlayer().getScore().getScore(other_low)){
                return getCurrentPlayer();
            }
        }
        //draw case
        return null;
    }


    public boolean isOver() {
        GameOverLogic logic = new GameOverLogic(this);
        return logic.execute();
    }

    public boolean won() {
        for (int i = 0; i < 6; i++) {
            if (getCurrentPlayer().getScore().toArray()[i] < 18) {
                return false;
            }
        }
        return true;
    }

    public boolean isWinner(Player player) {
        return isOver() && this.getCurrentPlayer().getName().equals(player.getName());
    }

    public void setNextPlayerAsCurrent() {
        if(!gameOver()) {
            this.setBonusPlay(0);
            refresh();
            if (this.currentPlayerIndex == 1) {
                this.currentPlayerIndex = 0;
            } else {
                this.currentPlayerIndex++;
            }
        }
        else{
            end();
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

    public boolean firstPlayerWon() {
        GameOverLogic logic = new GameOverLogic(this);

        if (logic.playerHasMaxScoreAcrossAllColors(0))
            return true;
        else if (logic.playerHasMaxScoreAcrossAllColors(1))
            return false;
        else if (logic.noMovesLeft()) {
            return logic.firstPlayerWinsWithBestScore();
        }
        return false;
    }

    public Boolean whoWon() // true for first player, false for second player, null for no winner
    {
        GameOverLogic logic = new GameOverLogic(this);

        if (logic.playerHasMaxScoreAcrossAllColors(0))
            return true;
        else if (logic.playerHasMaxScoreAcrossAllColors(1))
            return false;
        else if (logic.noMovesLeft())
        {
            return logic.firstPlayerWinsWithBestScore();
        }
        else
        {
            return null;
        }
    }
}
