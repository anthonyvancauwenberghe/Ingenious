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
        for(int tail = 0; tail < 2; tail++)
        {
            for(int north = -1; north < 4; north++)
            {
                for(int hWest= -1; hWest < 4; hWest++)
                {
                    for(int hEast = -1; hEast < 4; hEast++)
                    {
                        for(int left = -1; left < 4; left++)
                        {
                            for(int right = -1; right < 4; right++)
                            {
                                for(int tWest = -1; tWest < 4; tWest++)
                                {
                                    for(int tEast = -1; tEast < 4; tEast++)
                                    {
                                        for(int south = -1; south < 4; south++)
                                        {
                                            int[] array = {north,hWest,hEast,left,right,tWest,tEast,south,tail};
                                            State state = new State(array);
                                            if(viable_canditate(state)){
                                                states.add(state);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return states;
    }

    public boolean viable_canditate(State state){
        int [] a = state.get_description();
        int count = 0;
        for(int i=0; i<a.length; i++){
            if(a[i]==-1){
                count++;
                if(count>3){
                    return false;
                }
            }
        }
        if(count == 1){
            return false;
        }
        if(count >=2 && !correctPlacement(count, state)){
            return false;
        }
        return true;
    }

    public boolean correctPlacement(int count, State state){
        if(count == 2){
            if(state.getNorth()==-1 && (state.gethWest()==-1 || state.gethEast()==-1)){
                return true;
            }
            if(state.getSouth()==-1 && (state.gettEast() == -1 || state.gettWest() == -1)){
                return true;
            }
            return false;
        }
        if(count == 3){
            if(state.gethWest()==-1 && state.gettWest()==-1 && state.getLeft()==-1){
                return true;
            }
            if(state.getRight() == -1 && state.gethEast()==-1 && state.gettEast()==-1){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Generator gen = new Generator();
        ArrayList<State> states = gen.generate_All();
        System.out.println(states.size());
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