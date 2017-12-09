package com.ingenious.factories;

import com.ingenious.algorithms.impl.RandomAlgorithm;
import com.ingenious.models.Bag;
import com.ingenious.models.Rack;
import com.ingenious.models.players.Player;
import com.ingenious.models.players.impl.Bot;
import com.ingenious.models.players.impl.Human;

import java.util.ArrayList;

public class PlayerFactory {
    private Bag bag;
    private ArrayList<Player> players = new ArrayList<>();

    public PlayerFactory(Bag bag) {
        this.bag = bag;
    }

    private void SetPlayers() {
        this.players.add(getFirstPlayer());
        this.players.add(getSecondPlayer());
    }

    private Player getFirstPlayer() {
        RackFactory rackFactory = new RackFactory(this.bag);
        Rack rack = rackFactory.generate();
        return new Human(rack);
    }

    private Player getSecondPlayer() {
        RackFactory rackFactory = new RackFactory(this.bag);
        Rack rack = rackFactory.generate();
        return new Bot(rack, new RandomAlgorithm());
    }

    public ArrayList<Player> generate() {
        this.SetPlayers();
        return this.players;
    }
}
