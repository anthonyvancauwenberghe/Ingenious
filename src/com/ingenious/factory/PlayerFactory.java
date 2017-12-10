package com.ingenious.factory;

import com.ingenious.config.Configuration;
import com.ingenious.model.Bag;
import com.ingenious.model.Rack;
import com.ingenious.model.players.Player;
import com.ingenious.model.players.impl.Bot;
import com.ingenious.model.players.impl.Human;

import java.util.ArrayList;

public class PlayerFactory {
    private Bag bag;
    private ArrayList<Player> players = new ArrayList<>();

    public PlayerFactory(Bag bag) {
        this.bag = bag;
    }

    private void setPlayers() {
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
        return new Bot(rack, Configuration.algorithm);
    }

    public ArrayList<Player> generate() {
        this.setPlayers();
        return this.players;
    }
}
