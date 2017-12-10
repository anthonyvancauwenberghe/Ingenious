package com.ingenious.engine;

import com.ingenious.engine.logic.calculation.impl.ScoreCalculatorLogic;
import com.ingenious.engine.logic.game.BoardMovePlacementGameLogic;
import com.ingenious.model.Bag;
import com.ingenious.model.Board;
import com.ingenious.model.BoardNode;
import com.ingenious.model.Move;
import com.ingenious.model.players.Player;
import com.ingenious.model.players.impl.Bot;
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

    }

    public void executeMove(Move move) {
        BoardMovePlacementGameLogic placeMove = new BoardMovePlacementGameLogic(this, move);

        /* Execute move on board if it is valid */
        if (!placeMove.execute())
            return;

        System.out.println(this.getBoard().getNode(1, 4).getTile().toString() + "," + this.getBoard().getNode(2, 3).getTile().toString());

        /* Remove piece from currentplayer rack */
        this.getCurrentPlayer().rack.removePiece(move.getPiece());

        /* Add new piece to currentplayer rack from bag */
        this.getCurrentPlayer().rack.addPiece(this.bag.getAndRemoveRandomPiece());

        /* Calculate current player score */
        ScoreCalculatorLogic scoreCalculator = new ScoreCalculatorLogic(this, move);
        scoreCalculator.calculate();

        if (this.bonusPlay > 0)
            bonusPlay--;

        setNextPlayerAsCurrent();
        GameProvider.updateGraphics();

        if (this.getCurrentPlayer() instanceof Bot) {
            Move botMove = ((Bot) this.getCurrentPlayer()).getMove(this);
            this.executeMove(botMove);
        }

    }

    public void setNextPlayerAsCurrent() {
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


}
