package com.ingenious.factories;

import com.ingenious.models.Bag;
import com.ingenious.models.Rack;

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
