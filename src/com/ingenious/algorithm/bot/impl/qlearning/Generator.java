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
        int index = 0;

        for(int i1 = 0; i1 < 2; i1++)
        {
            for(int i2 = -1; i2 < 4; i2++)
            {
                for(int i3 = -1; i3 < 4; i3++)
                {
                    for(int i4 = -1; i4 < 4; i4++)
                    {
                        for(int i5 = -1; i5 < 4; i5++)
                        {
                            for(int i6 = -1; i6 < 4; i6++)
                            {
                                for(int i7 = -1; i7 < 4; i7++)
                                {
                                    for(int i8 = -1; i8 < 4; i8++)
                                    {
                                        for(int i9 = -1; i9 < 4; i9++)
                                        {
                                            int[] array = {0,i1,i2,i3,i4,i5,i6,i7,i8,i9};
                                            if(!contain4(array)){

                                                State state = new State(array);

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

    public boolean contain4(int [] b){
        int x =0;
        for(int i=0; i<b.length; i++){
            if(b[i] == -1){
                x++;
                if(x>3){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Generator gen = new Generator();
        ArrayList<State> states = gen.generate_All();
        for(int i=0; i<100; i++){
            int[] ar = states.get(i).get_description();
            for(int j=0; j<ar.length; j++){
                System.out.print(ar[j]+" ");
            }
            System.out.println();
        }
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