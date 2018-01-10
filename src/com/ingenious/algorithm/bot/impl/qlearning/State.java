package com.ingenious.algorithm.bot.impl.qlearning;

/**
 * Created by carolley on 10-Jan-18.
 */
public class State {

    private int[] description;
    private int index;

    public State(int [] description, int index){
        this.description = description;
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

    public boolean same(State b){
        if(this.get_description() == b.get_description()){
            return true;
        }
        return false;
    }

    public int[] get_description(){
        return this.description;
    }

}
