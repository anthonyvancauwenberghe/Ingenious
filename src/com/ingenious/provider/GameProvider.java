package com.ingenious.provider;

import com.ingenious.engine.Game;
import com.ingenious.factory.BagFactory;
import com.ingenious.factory.BoardFactory;
import com.ingenious.factory.PlayerFactory;
import com.ingenious.gui.MainFrame;
import com.ingenious.model.Bag;
import com.ingenious.model.Board;
import com.ingenious.model.players.Player;

import java.util.ArrayList;

public class GameProvider implements Runnable {

    private static GameProvider instance;

    private Game game;
    private MainFrame gui;

    private GameProvider() {
        game = initializeGame();
        gui = initializeGui(game);
    }

    public static void start() {
        if (instance == null) {
            instance = new GameProvider();
            instance.run();
        } else {
            System.out.println("Error, Already running");
        }
    }

    @Override
    public void run() {
        game.gameLoop();
    }

    public Game initializeGame() {
        Bag bag = initializeBag();
        ArrayList<Player> players = initializePlayers(bag);
        Board board = initializeBoard();

        return new Game(board, players, bag);
    }

    public static void updateGraphics() {
        instance.gui.repaintAll();
    }

    public MainFrame initializeGui(Game game) {
        return new MainFrame(game);
    }


    private Bag initializeBag() {
        BagFactory factory = new BagFactory();
        return factory.generate();
    }

    private ArrayList<Player> initializePlayers(Bag bag) {
        PlayerFactory factory = new PlayerFactory(bag);
        return factory.generate();
    }

    private Board initializeBoard() {
        return (new BoardFactory()).generate();
    }
}
