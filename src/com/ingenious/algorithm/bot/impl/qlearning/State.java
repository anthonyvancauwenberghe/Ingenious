package com.ingenious.algorithm.bot.impl.qlearning;

/**
 * Created by carolley on 10-Jan-18.
 */
public class State {

    private int[] description;
    private int index;
    private int north;
    private int hWest;
    private int hEast;
    private int left;
    private int right;
    private int tWest;
    private int tEast;
    private int south;
    private int tail;


    public State(int [] description, int index){
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
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

    public int gethEast() {
        return hEast;
    }

    public void sethEast(int hEast) {
        this.hEast = hEast;
    }

    public int getNorth() {
        return north;
    }

    public void setNorth(int north) {
        this.north = north;
    }

    public int gethWest() {
        return hWest;
    }

    public void sethWest(int hWest) {
        this.hWest = hWest;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int gettWest() {
        return tWest;
    }

    public void settWest(int tWest) {
        this.tWest = tWest;
    }

    public int gettEast() {
        return tEast;
    }

    public void settEast(int tEast) {
        this.tEast = tEast;
    }

    public int getSouth() {
        return south;
    }

    public void setSouth(int south) {
        this.south = south;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public boolean same(State b){
        if(this.get_description() == b.get_description()){
            return true;
        }
        return false;
    }

    public int[] get_description(){
        return this.description;
    }

}
