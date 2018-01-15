package com.ingenious.algorithm.bot.impl.qlearning;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        QTable_File f = new QTable_File();
        State[] qtable = null;

        try {
            qtable = f.load_Qtable();
        } catch (IOException e) {
            e.printStackTrace();
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
