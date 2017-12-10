package com.ingenious.model.players.impl;

import com.ingenious.model.Rack;
import com.ingenious.model.players.Player;

public class Human extends Player {

    public Human(String name, Rack rack) {
        super(name, rack, true);
    }

    public Human(Rack rack) {
        super("Human Player", rack, true);
    }

    public Human getClone() {
        return new Human(this.getName(), this.getRack().getClone());
    }
}
