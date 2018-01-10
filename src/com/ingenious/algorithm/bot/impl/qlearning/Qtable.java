package com.ingenious.algorithm.bot.impl.qlearning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by carolley on 10-Jan-18.
 */
public class Qtable {

    private Map<State,Double> table;
    private ArrayList<State> states;

    public Qtable(){
        Generator generator = new Generator();
        ArrayList<State> states = generator.generate_All();
        this.states = states;
        Map<State,Double> qtable = new HashMap<>();
        for(int i=0; i<states.size(); i++){
            qtable.put(states.get(i),0.0);
        }
        this.table = qtable;
    }

    public State getState(int i){
        return this.states.get(i);
    }

    public Map<State,Double> getTable(){
        return this.table;
    }

    public double get_Qvalue(State state){
        Map<State,Double> table = this.getTable();
        return table.get(state);
    }

    public void edit_Qvalue(State state, double newValue){
        Map<State,Double> table = this.getTable();
        table.put(state,newValue);
    }
}
