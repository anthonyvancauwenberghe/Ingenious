package com.ingenious.engine;

import com.ingenious.models.Bag;
import com.ingenious.models.Board;
import com.ingenious.models.Move;
import com.ingenious.models.players.Player;

import java.util.ArrayList;

public class Game {
    private Board board;
    private ArrayList<Player> players;
    private Bag bag;

    private int currentPlayerIndex;

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
        this.currentPlayerIndex = 0;
    }

    public void swap() {

    }

    public void executeMove(Move move) {

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
}
