package com.ingenious.models.players.impl;

import com.ingenious.models.Rack;
import com.ingenious.models.players.Player;

public class Human extends Player {

    public Human(String name, Rack rack) {
        super(name, rack, true);
    }

    public Human(Rack rack) {
        super("Human Player", rack, true);
    }
}
