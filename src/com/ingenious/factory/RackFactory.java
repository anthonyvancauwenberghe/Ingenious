package com.ingenious.factory;

import com.ingenious.model.Bag;
import com.ingenious.model.Rack;

public class RackFactory {
    private Bag bag;
    private Rack rack;

    public RackFactory(Bag bag) {
        this.rack = new Rack();
        this.bag = bag;
    }

    public Rack generate() {
        for (int i = 0; i < 6; i++) {
            this.rack.addPiece(bag.getAndRemoveRandomPiece());
        }
        return this.rack;
    }
}
