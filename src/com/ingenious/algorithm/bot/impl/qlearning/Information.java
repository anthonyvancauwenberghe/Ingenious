package com.ingenious.algorithm.bot.impl.qlearning;

import javax.swing.*;

/**
 * Created by carolley on 11-Jan-18.
 */
public class Information {


    String type;

    public void popup(double oldQ, double newQ){
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,  this.type + "Move Old Q: "+ oldQ + " New Q: "+ newQ);
    }
    public void error(String s){
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,  s);
    }

    public void setType(String type){
        this.type = type;
    }

}
