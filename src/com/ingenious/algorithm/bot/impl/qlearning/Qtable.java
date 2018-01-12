package com.ingenious.algorithm.bot.impl.qlearning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by carolley on 10-Jan-18.
 */
public class Qtable {

    private State[] table;

    public Qtable(){
        Generator generator = new Generator();
        ArrayList<State> states = generator.generate_All();
        State[] qtable = new State[states.size()];
        for(int i=0; i<states.size(); i++){
            qtable[i] = states.get(i);
        }
        this.table = qtable;
    }

    public State getState(State state){
        State[] table = this.getTable();
            for(State s: table){
                if (s.same(state)){
                    return s;
                }
            }
        return null;
    }

    public State[] getTable(){
        return this.table;
    }

    public double get_Qvalue(State state){
        return this.getState(state).getQ_value();
    }

    public void edit_Qvalue(State state, double newValue){
        getState(state).setQ_value(newValue);
    }
}
