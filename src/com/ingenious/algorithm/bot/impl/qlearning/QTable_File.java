package com.ingenious.algorithm.bot.impl.qlearning;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

/**
 * Created by carolley on 15-Jan-18.
 */
public class QTable_File{

    public State[] load_Qtable() throws FileNotFoundException{
        Generator g = new Generator();
        File q_table = new File("QTable.txt");
        ArrayList<State> states = g.generate_All();
        State[] qtable = new State[states.size()];

        if(q_table.exists()){
            Scanner reader = new Scanner(q_table);
            for(int i=0; i<states.size(); i++){
                states.get(i).setQ_value(reader.nextDouble());
                qtable[i] = states.get(i);
            }
        }
        else{
            for(int i=0; i<states.size(); i++){
                qtable[i] = states.get(i);
            }
        }
        return qtable;
    }
    

    public void save_Qtable(Qtable q) throws IOException {
        File file = new File("QTable.txt");
        if(!file.exists()){
            file.createNewFile();
            file.setWritable(true);
        }
        FileWriter qFile = new FileWriter("QTable.txt");
        State[] states = q.getTable();
        for (int i = 0; i < states.length; i++){
            double value = states[i].getQ_value();
            qFile.write(Double.toString(value)+ "\n");
        }
        qFile.close();
    }
}
