package com.ingenious.algorithm.bot.impl.qlearning;

import com.ingenious.algorithm.support.AllBaseMovesGenerator;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.provider.GameProvider;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by carolley on 10-Jan-18.
 */
public class Generator {

    public ArrayList<State> generate_All(){
        //GENERATE ALL POSSIBLE STATES !!
        ArrayList<State> states = new ArrayList<>();
        return states;
    }

    public ArrayList<State> generate_Moves(){
        ArrayList<State> moves = new ArrayList<>();
        AllBaseMovesGenerator generator = new AllBaseMovesGenerator(GameProvider.getInstance().game);
        Set<Move> move = generator.generate();
        return moves;
    }

    //public State convert(Move move){
    //}


}
