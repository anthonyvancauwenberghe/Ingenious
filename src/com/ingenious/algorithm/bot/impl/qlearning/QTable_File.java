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

    public State[] load_Qtable() throws IOException {
        Generator g = new Generator();
        File q_table = new File("QTable.txt");
        ArrayList<State> states = g.generate_All();
        State[] qtable = new State[states.size()];

        if(q_table.exists()){
            Scanner reader = new Scanner(q_table);
            for(int i=0; i<states.size(); i++){
                states.get(i).setQ_value(reader.nextDouble());
                states.get(i).setVisited(reader.nextInt());
                qtable[i] = states.get(i);
            }
        }
        else{
            q_table.createNewFile();
            q_table.setWritable(true);
            FileWriter writer = new FileWriter(q_table);
            for(int i=0; i<states.size(); i++){
                qtable[i] = states.get(i);
                writer.write(Double.toString(0.0));
                writer.write(" ");
                writer.write(Integer.toString(0));
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        }
        return qtable;
    }

    public void save_Qtable(Qtable q) throws IOException {
        File file = new File("QTable.txt");
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        FileWriter qFile = new FileWriter("QTable.txt");
        State[] states = q.getTable();
        int t = 0;
        for (int i = 0; i < states.length; i++){
            double q_value = states[i].getQ_value();
            int visited = states[i].getVisited();
            qFile.write(Double.toString(q_value));
            qFile.write(" ");
            qFile.write(Integer.toString(visited));
            if(visited>0){
                t++;
            }
            qFile.write(System.getProperty("line.separator"));
        }
        qFile.close();
        System.out.println("DONE EDITING");
    }

}
