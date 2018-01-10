package com.ingenious.algorithm.bot.impl.qlearning;

import java.util.ArrayList;

/**
 * Created by carolley on 10-Jan-18.
 */
public class Qtable {

    private double [] table;
    private ArrayList<State> states;

    public Qtable(){
        Generator generator = new Generator();
        ArrayList<State> states = generator.generate_All();
        this.states = states;
        double []table = new double[states.size()];
        for(int i=0; i<states.size(); i++){
            table[states.get(i).getIndex()] = 0;
        }
        this.table = table;
    }

    public State getState(int i){
        return this.states.get(i);
    }

    public double[] getTable(){
        return this.table;
    }

    public double get_Qvalue(State state){
        double [] table = this.getTable();
        return table[state.getIndex()];
    }

    public void edit_Qvalue(State state, double newValue){
        double [] table = this.getTable();
        table[state.getIndex()] = newValue;
    }
}
