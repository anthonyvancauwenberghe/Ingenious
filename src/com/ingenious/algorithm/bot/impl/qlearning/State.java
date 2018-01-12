package com.ingenious.algorithm.bot.impl.qlearning;

/**
 * Created by carolley on 10-Jan-18.
 */
public class State {

    private int[] description;
    private int north;
    private int hWest;
    private int hEast;
    private int left;
    private int right;
    private int tWest;
    private int tEast;
    private int south;
    private int tail;
    private int visited;
    private double q_value;


    public State(int [] description, double q_value){
        this.description = description;
        this.north = description[0];
        this.hWest = description[1];
        this.hEast = description[2];
        this.left = description[3];
        this.right =description[4];
        this.tWest = description[5];
        this.tEast = description[6];
        this.south = description[7];
        this.tail = description[8];
        this.visited = 0;
        this.q_value = q_value;

    }

    public double getQ_value(){
        return this.q_value;
    }

    public void setQ_value(double q){
        this.q_value = q;
    }

    public int getVisited(){
        return this.visited;
    }

    public void visited(){
        this.visited = this.visited + 1;
    }
    public int gethEast() {
        return hEast;
    }

    public void sethEast(int hEast) {
        this.hEast = hEast;
        this.get_description()[2] = hEast;
    }

    public int getNorth() {
        return north;
    }

    public void setNorth(int north) {
        this.north = north;
        this.get_description()[0] = north;
    }

    public int gethWest() {
        return hWest;
    }

    public void sethWest(int hWest) {
        this.hWest = hWest;
        this.get_description()[1] = hWest;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
        this.get_description()[3] = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
        this.get_description()[4] = right;
    }

    public int gettWest() {
        return tWest;
    }

    public void settWest(int tWest) {
        this.tWest = tWest;

        this.get_description()[5] = tWest;
    }

    public int gettEast() {
        return tEast;
    }

    public void settEast(int tEast) {
        this.tEast = tEast;
        this.get_description()[6] = tEast;
    }

    public int getSouth() {
        return south;
    }

    public void setSouth(int south) {
        this.south = south;
        this.get_description()[7] = south;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
        this.get_description()[8] = tail;
    }

    public boolean same(State b){
        int [] des_b = b.get_description();
        for(int i=0; i<des_b.length; i++){
            if(des_b[i]!=this.get_description()[i]){
                return false;
            }
        }
        return true;
    }

    public int[] get_description(){
        return this.description;
    }

    public void print(){
        for(int i =0; i<get_description().length; i++){
            System.out.print(get_description()[i]+" ");
        }
        System.out.println("");
    }



    public double reward(){
        //+0.15 for matching, -0.1 for not mismatch , double + for double color?
        this.print();
        double reward = 0.0;
        if(this.getTail()==0){
            for(int i=0; i<8; i++){
                if(this.get_description()[i] == 0) {
                    reward = reward + 0.3;
                }
                if(this.get_description()[i]==2){
                    reward = reward - 0.04;
                }
            }
        }
        else{
            for(int i =0; i<5; i++){
                if(this.get_description()[i]==0){
                    reward = reward + 0.15;
                }
            }
            for(int i=3; i<8; i++){
                if(this.get_description()[i]==1){
                    reward= reward + 0.15;
                }
            }
            for(int i =0; i<8; i++){

                if(this.get_description()[i]==2){
                    reward = reward - 0.02;
                }
            }

        }
        if(disconnected()){
            reward = reward - 0.5;
        }

        return reward;
    }

    public boolean disconnected(){
        for(int i=0; i<8; i++){
            if(this.get_description()[i]!=-1 && this.get_description()[i]!=3){
                return false;
            }
        }
        return true;
    }



}
