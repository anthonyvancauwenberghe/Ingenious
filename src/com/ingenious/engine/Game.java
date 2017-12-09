package com.ingenious.engine;

import com.ingenious.models.Bag;
import com.ingenious.models.Board;
import com.ingenious.models.players.Player;

import java.util.ArrayList;

public class Game {
    private Board board;
    private ArrayList<Player> players;
    private Bag bag;

    public Game(Board board, ArrayList<Player> players, Bag bag) {
        this.board = board;
        this.players = players;
        this.bag = bag;
    }

    public void gameLoop() {

    }

    public void swap() {

    }

    public Player getCurrentPlayer() {
        return null;
    }

    public Player getOtherPlayer() {
        return null;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }
}
