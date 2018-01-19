package com.ingenious.algorithm.support.nodegenerators;

import com.ingenious.model.players.Player;

/**
 * Created by carolley on 19-Jan-18.
 */
public class Eval {

    public int[] evaluate(Player player){
        int [] s = new int[6];
        int [] score = player.getScore().getScores();
        for(int i=0; i<score.length; i++){
            s[i] = (18-score[i])^2;
        }
        return s;
    }

    public int evaluate_pos(Player player){
        int [] s = this.evaluate(player);
        int total = 0;
        for(int i=0; i<s.length; i++){
            total = total + s[i];
        }
        return total;
    }

}
