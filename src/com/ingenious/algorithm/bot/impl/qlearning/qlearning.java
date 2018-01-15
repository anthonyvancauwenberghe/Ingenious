package com.ingenious.algorithm.bot.impl.qlearning;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.engine.Game;
import com.ingenious.model.Move;
import com.ingenious.provider.GameProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by carolley on 10-Jan-18.
 */
public class qlearning extends BotAlgorithm{

    private double epsilon;
    private Qtable qtable;
    private Information popup;
    private double P_RATE = 0.6;


    public qlearning(double epsilon){
        this.epsilon = epsilon;
        this.qtable = new Qtable();
        Information popup = new Information();
        this.popup = popup;
    }

    @Override
    public Move execute(Game game) {
        if(random()){
            popup.setType(" RANDOM ");
            Move move = randomMove();
            return move;
        }
        else{
            popup.setType(" Q ");
            Move move = qMove();
            return move;
        }
    }


    public double getEpsilon(){
        return this.epsilon;
    }

    public void setEpsilon(double e){
        this.epsilon = e;
    }

    public boolean random(){
        double e = Math.random();
        if(e <= getEpsilon()){
            return true;
        }
        return false;
    }

    public Move randomMove(){
        Generator generator = new Generator();
        ArrayList<Move> moves = generator.generateActions();
        int moveIndex = (int) (Math.random() * moves.size());
        Move move = moves.get(moveIndex);
        Generator converter = new Generator();
        State state = converter.convert(move);
        setNewQ(state);
        return move;
    }

    public double reward(State state){
        return state.reward();
    }

    public double setNewQ(State state){
        State state1 = getQtable().getState(state);
        if(state1==null){
            popup.error("STATE EQUALS NULL");
        }
        double oldQ = state1.getQ_value();
        double alpha = 1/1+state1.getVisited();
        double newQ = (1-alpha)*oldQ + (state1.getVisited()+1)*P_RATE*reward(state1);
        state1.setQ_value(newQ);
        state1.visited();
        popup.popup(oldQ,newQ);
        return newQ;
    }

    public Qtable getQtable() {
        return this.qtable;
    }
    private Move qMove(){
        Generator generator = new Generator();
        ArrayList<Move> moves = generator.generateActions();
        Move move = highestQ(moves);
        State state = generator.convert(move);
        setNewQ(state);
        return move;
    }

    private Move highestQ(ArrayList<Move> moves){
        Generator gen = new Generator();
        double max = gen.convert(moves.get(0)).getQ_value();
        Move maxMove = moves.get(0);
        for(Move move: moves){
            State state = gen.convert(move);
            double q = state.getQ_value();
            if(q > max){
                max = q;
                maxMove = move;
            }
        }
        return maxMove;
    }

    public void end(){
        QTable_File f = new QTable_File();
        try {
            f.save_Qtable(getQtable());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
