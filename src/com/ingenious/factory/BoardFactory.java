package com.ingenious.factory;

import com.ingenious.config.Configuration;
import com.ingenious.model.Board;
import com.ingenious.model.BoardNode;
import com.ingenious.model.Tile;

import java.util.ArrayList;

public class BoardFactory {
    private Board board;
    private ArrayList<BoardNode> boardNodes = new ArrayList<BoardNode>();
    public BoardFactory() {
        this.board = new Board();
    }

    public Board generate() {
        this.buildBoardList();
        this.setInitialTiles();
        this.buildNodeCoordinatesArray();
        return this.board;
    }

    public void setInitialTiles() {
        this.boardNodes.get(0).setTile(Tile.red);
        this.boardNodes.get(79).setTile(Tile.green);
        this.boardNodes.get(89).setTile(Tile.blue);
        this.boardNodes.get(10).setTile(Tile.orange);
        this.boardNodes.get(80).setTile(Tile.yellow);
        this.boardNodes.get(90).setTile(Tile.purple);
    }

    private void buildBoardList() {
        int cnt = 0; //This counter keeps track of the number of boardNodes that were created
        int board_width = Configuration.BOARD_WIDTH; // this is the radius of boardNodes from the center to the end of the com.ingenious.model.board (incl. center)
        int tmp_top = -(board_width - 1);//reference to keep track of the decrease in the number of hexes to be created
        for (int i = 0; i < board_width; i++) //loop as many times as there are columns on the sides of the central one (* 2)
        {
            for (int j = board_width - 1; j >= tmp_top; j--) {
                if (i == 0) {
                    this.boardNodes.add(new BoardNode(i, -j, Tile.empty));//Create boardNodes only once for the case when i is 0 because it is the center
                } else {
                    this.boardNodes.add(new BoardNode(i, -j, Tile.empty));
                    this.boardNodes.add(new BoardNode(-i, j, Tile.empty)); // then creates 2 version of the node, its regular and mirrored version.
                    cnt += 2;
                }
            }
            ++tmp_top; //update the size of the colum to be generated and loop again
        }
    }

    private void buildNodeCoordinatesArray() {
        int offset = Configuration.BOARD_WIDTH - 1;

        /*Loop through the array and set all initial values to -1 */
        for (int x = 0; x < board.getNodeCoord().length; x++) {
            for (int y = 0; y < board.getNodeCoord()[x].length; y++) {
                board.getNodeCoord()[x][y] = -1;
            }
        }

        /* Set all coordinate values to the given index from the node arraylist */
        for (int i = 0; i < this.boardNodes.size(); i++) {
            int xOffset = this.boardNodes.get(i).getX() + offset;
            int yOffset = this.boardNodes.get(i).getY() + offset;
            board.getNodeCoord()[xOffset][yOffset] = Configuration.EMPTY_TILE_ID;
        }
    }
}
