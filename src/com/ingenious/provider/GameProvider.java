package com.ingenious.provider;

import com.ingenious.engine.Game;
import com.ingenious.factory.BagFactory;
import com.ingenious.factory.BoardFactory;
import com.ingenious.factory.PlayerFactory;
import com.ingenious.gui.MainFrame;
import com.ingenious.model.Bag;
import com.ingenious.model.Board;
import com.ingenious.model.Piece;
import com.ingenious.model.PieceTracker;
import com.ingenious.model.players.Player;

import java.util.ArrayList;

public class GameProvider implements Runnable {

    private static GameProvider instance;

    public Game game;
    private MainFrame gui;

    private GameProvider(boolean enableGui) {
        game = initializeGame();
        if (enableGui)
            gui = initializeGui(game);
    }

    public static void start(boolean enableGui) {
        if (instance == null) {
            instance = new GameProvider(enableGui);
            instance.run();
        } else {
            System.out.println("Error, Already running");
        }
    }

    public static GameProvider getInstance() {
        return instance;
    }
    @Override
    public void run() {
        game.gameLoop();
    }

    public Game initializeGame() {
        Bag bag = initializeBag();
        PieceTracker pieceTracker = this.initializePieceTracker(bag);
        ArrayList<Player> players = initializePlayers(bag);
        Board board = initializeBoard();
        return new Game(board, players, bag, pieceTracker);
    }

    public static void updateGraphics() {
        if (instance.gui != null)
            instance.gui.repaintAll();
    }

    public MainFrame initializeGui(Game game) {
        return new MainFrame(game);
    }

    private PieceTracker initializePieceTracker(Bag bag) {
        PieceTracker tracker = new PieceTracker();
        for (Piece piece : bag.getPieces()) {
            tracker.addPiece(piece);
        }
        return tracker;
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
