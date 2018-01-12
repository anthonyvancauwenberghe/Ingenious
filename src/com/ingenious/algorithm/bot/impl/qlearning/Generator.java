package com.ingenious.algorithm.bot.impl.qlearning;

import com.ingenious.algorithm.support.AllBaseMovesGenerator;
import com.ingenious.model.Board;
import com.ingenious.model.BoardNode;
import com.ingenious.model.Move;
import com.ingenious.model.Piece;
import com.ingenious.provider.GameProvider;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by carolley on 10-Jan-18.
 */
public class Generator{

    /*
    Generates all possible 167.936 states
     */
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
    /*
    Check if generated state can actually occur
     */
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

    /*
    0 = color of head, 1 = color of tail, -1 = node doesnt exist, 2 = different color, 3 = empty node
     */
    public State convert(Move move){
        Board board = GameProvider.getInstance().game.getBoard();
        int description [] = new int [9];
        int [] head = {move.getHeadNode().getX(), move.getHeadNode().getY()};
        int [] tail = {move.getTailNode().getX(), move.getTailNode().getY()};
        Piece piece = move.getPiece();
        if(!piece.hasEqualTiles()){
            description[8] = 1;
        }
        //pos 1:
        if(tail[0] == head[0] && tail[1]== head[1]+1){
            description[0] = giveValue(board.getNode(head[0],head[1]-1), piece);
            description [7] = giveValue(board.getNode(tail[0], tail[1]+1), piece);
            description[3] = giveValue(board.getNode(head[0]-1, head[1]+1), piece);
            description[4] = giveValue(board.getNode(head[0]+1,head[1]), piece);
            description[2] = giveValue(board.getNode(head[0]+1,head[1]-1), piece);
            description[6] = giveValue(board.getNode(tail[0]+1,tail[1]), piece);
            description[5] = giveValue(board.getNode(tail[0]-1,tail[1]+1), piece);
            description[1] = giveValue(board.getNode(head[0]-1, head[1]), piece);
        }
        //pos 2:
        if(tail[0] == head[0]+1 && tail[1]== head[1]){
            description[0] = giveValue(board.getNode(head[0]-1,head[1]), piece);
            description [7] = giveValue(board.getNode(tail[0]+1, tail[1]), piece);
            description[3] = giveValue(board.getNode(head[0], head[1]+1), piece);
            description[4] = giveValue(board.getNode(head[0]+1,head[1]-1), piece);
            description[2] = giveValue(board.getNode(head[0],head[1]-1), piece);
            description[6] = giveValue(board.getNode(tail[0]+1,tail[1]-1), piece);
            description[5] = giveValue(board.getNode(tail[0],tail[1]+1), piece);
            description[1] = giveValue(board.getNode(head[0]-1, head[1]+1), piece);
        }
        //pos 3:
        if(tail[0] == head[0]+1 && tail[1]== head[1]-1){
            description[0] = giveValue(board.getNode(head[0]-1,head[1]+1), piece);
            description[7] = giveValue(board.getNode(tail[0]+1, tail[1]-1), piece);
            description[3] = giveValue(board.getNode(head[0]+1, head[1]), piece);
            description[4] = giveValue(board.getNode(head[0],head[1]-1), piece);
            description[2] = giveValue(board.getNode(head[0]-1,head[1]), piece);
            description[6] = giveValue(board.getNode(tail[0],tail[1]-1), piece);
            description[5] = giveValue(board.getNode(tail[0]+1,tail[1]), piece);
            description[1] = giveValue(board.getNode(head[0], head[1]+1), piece);
        }
        //pos 4:
        if(tail[0] == head[0] && tail[1]== head[1]-1){
            description[0] = giveValue(board.getNode(head[0],head[1]+1), piece);
            description[7] = giveValue(board.getNode(tail[0], tail[1]-1), piece);
            description[3] = giveValue(board.getNode(head[0]+1, head[1]-1), piece);
            description[4] = giveValue(board.getNode(head[0]-1,head[1]), piece);
            description[2] = giveValue(board.getNode(head[0]-1,head[1]+1), piece);
            description[6] = giveValue(board.getNode(tail[0]-1,tail[1]), piece);
            description[5] = giveValue(board.getNode(tail[0]+1,tail[1]-1), piece);
            description[1] = giveValue(board.getNode(head[0]+1, head[1]), piece);
        }
        //pos 5:
        if(tail[0] == head[0]-1 && tail[1]== head[1]){
            description[0] = giveValue(board.getNode(head[0]+1,head[1]), piece);
            description[7] = giveValue(board.getNode(tail[0]-1, tail[1]), piece);
            description[3] = giveValue(board.getNode(head[0], head[1]-1), piece);
            description[4] = giveValue(board.getNode(head[0]-1,head[1]+1), piece);
            description[2] = giveValue(board.getNode(head[0],head[1]+1), piece);
            description[6] = giveValue(board.getNode(tail[0]-1,tail[1]+1), piece);
            description[5] = giveValue(board.getNode(tail[0],tail[1]-1), piece);
            description[1] = giveValue(board.getNode(head[0]+1, head[1]-1), piece);
        }
        //pos 6:
        if(tail[0] == head[0]-1 && tail[1]== head[1]+1){
            description[0] = giveValue(board.getNode(head[0]+1,head[1]-1), piece);
            description[7] = giveValue(board.getNode(tail[0]-1, tail[1]+1), piece);
            description[3] = giveValue(board.getNode(head[0]-1, head[1]), piece);
            description[4] = giveValue(board.getNode(head[0],head[1]+1), piece);
            description[2] = giveValue(board.getNode(head[0]+1,head[1]), piece);
            description[6] = giveValue(board.getNode(tail[0],tail[1]+1), piece);
            description[5] = giveValue(board.getNode(tail[0]-1,tail[1]), piece);
            description[1] = giveValue(board.getNode(head[0], head[1]-1), piece);
        }
        State state = new State(description);
        return state;
    }


    public ArrayList<Move> generateActions(){
        AllBaseMovesGenerator generator = new AllBaseMovesGenerator(GameProvider.getInstance().game);
        Set<Move> moves  = generator.generate();
        ArrayList<Move> actions = new ArrayList<>();
        for(Move move: moves){
            actions.add(move);
        }
        return actions;
    }

    public int giveValue(BoardNode node, Piece piece){
        if(node == null){
            return -1;
        }

        if(node.getTile().equals(piece.getHead())){
            return 0;
        }

        if(node.getTile().equals(piece.getTail()) && !piece.hasEqualTiles()){
            return 1;
        }

        if(node.isEmpty()){
            return 3;
        }

        return 2;
    }

}