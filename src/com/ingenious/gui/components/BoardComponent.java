package com.ingenious.gui.components;

import com.ingenious.config.Configuration;
import com.ingenious.models.board.BoardNode;
import com.ingenious.models.pieces.Piece;
import com.ingenious.models.tiles.Tile;
import com.ingenious.providers.impl.GameServiceProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by alexisguillot on 14/09/2017.
 */
public class BoardComponent extends JComponent {

    int startingX = 350; // coordinates for node 0,0
    int startingY = 253;

    public BoardComponent() {
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        if (GameServiceProvider.isBooted()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);


            ArrayList<BoardNode> boardNodeList = GameServiceProvider.board().getBoardNodes();

            for (int i = 0; i < boardNodeList.size(); i++) {
                //adjust x and y of node to actual location
                Point node = hex_to_centerpoint(boardNodeList.get(i).x, boardNodeList.get(i).y);

                Hexagon hexagon = new Hexagon(new Point(node.x, node.y));

                g.setColor(boardNodeList.get(i).getTile());

                g.fillPolygon(hexagon.getHexagon());

                g.setColor(Configuration.LineColor);

                if (Configuration.showCoordinates)
                    g.drawString((boardNodeList.get(i).getX()) + "," + (boardNodeList.get(i).getY()), node.x - 9, node.y + 3);

                g.drawPolygon(hexagon.getHexagon());
            }

            if (GameServiceProvider.game().getCurrentPlayer().getRack().selected()) {
                Piece piece = GameServiceProvider.game().getCurrentPlayer().getRack().getPieceSelected();
                Hexagon hexagon = new Hexagon(new Point(670, 140));
                g.setColor(piece.getHead());
                g.fillPolygon(hexagon.getHexagon());
                g.setColor(Configuration.LineColor);
                g.drawPolygon(hexagon.getHexagon());
                hexagon = new Hexagon(new Point(670, (int) (140 + Hexagon.Height())));
                g.setColor(piece.getTail());
                g.fillPolygon(hexagon.getHexagon());
                g.setColor(Configuration.LineColor);
                g.drawPolygon(hexagon.getHexagon());
            }

            BoardListener listener = new BoardListener();
            addMouseListener(listener);
            addKeyListener(listener);
            requestFocus();

            g.drawString("Current Player: " + GameServiceProvider.game().getCurrentPlayer().getName(), 20, 20);
        }

    }

    public void repaintNode(Graphics g, int X, int Y, Tile tileColor) {
        if (GameServiceProvider.board().inBoard(X, Y)) {
            Point p = hex_to_centerpoint(X, Y);

            Hexagon h = new Hexagon(p);
            g.setColor(tileColor);
            g.fillPolygon(h.getHexagon());
            g.setColor(Configuration.LineColor);
            g.drawPolygon(h.getHexagon());
        }
    }

    public Point point_to_hex(int x, int y) {
        int q = (int) Math.round((x - startingX) / Hexagon.Width(0.75));
        int r = (int) Math.round((y - startingY - (q * Hexagon.Height(0.5))) / Hexagon.Height());

        return new Point(q, r);
    }

    public Point hex_to_centerpoint(int x, int y) {
        Point p = new Point();
        p.x = (int) (startingX + (x * Hexagon.Width(0.75)));
        p.y = (int) (startingY + (y * Hexagon.Height()) + (x * Hexagon.Height(0.5)));
        return p;
    }


    class BoardListener implements MouseListener, KeyListener {

        BoardNode clicked;
        BoardNode clicked2;
        int cnt = 0;
        Graphics g = GameServiceProvider.gui().getBoardPanel().getGraphics();

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Point coord = point_to_hex(x, y);

            if (cnt == 0) {
                clicked = GameServiceProvider.board().getNode((int) coord.getX(), (int) coord.getY());
                if (GameServiceProvider.game().getCurrentPlayer().getRack().getPieceSelected() != null) {
                    Tile c = GameServiceProvider.game().getCurrentPlayer().getRack().getPieceSelected().getHead();
                    repaintNode(g, coord.x, coord.y, c);
                }

                cnt++;
            } else if (cnt == 1) {
                clicked2 = GameServiceProvider.board().getNode((int) coord.getX(), (int) coord.getY());

                if (GameServiceProvider.board().isNeighbour(clicked, clicked2) && GameServiceProvider.game().getCurrentPlayer().getRack().getPieceSelected() != null) {
                    Tile d = GameServiceProvider.game().getCurrentPlayer().getRack().getPieceSelected().getTail();
                    repaintNode(g, coord.x, coord.y, d);
                    cnt++;
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (GameServiceProvider.game().getCurrentPlayer().getRack().selected() && cnt == 2) {
                    GameServiceProvider.game().place_piece(GameServiceProvider.game().getCurrentPlayer().getRack().getPieceSelected(), clicked, clicked2);
                    GameServiceProvider.gui().repaintAll();
                    cnt = 0;
                    clicked = null;
                    clicked2 = null;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                cnt = 0;
                repaint();
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
