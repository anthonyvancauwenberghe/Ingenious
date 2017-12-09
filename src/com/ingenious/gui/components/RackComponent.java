package com.ingenious.gui.components;

import com.ingenious.config.Configuration;
import com.ingenious.engine.Game;
import com.ingenious.provider.GameProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class RackComponent extends JComponent {

    private int X_start = 100;
    private int X_position;
    private int Y_line = 25;
    private int X_gap = (int) (Hexagon.Width() + 40);

    private Game game;

    public RackComponent(Game game) {
        this.game = game;
        this.setVisible(true);
    }


    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        super.paintComponent(g);

        X_position = X_start;

        for (int i = 0; i < 6; i++) {
            if (game.getCurrentPlayer().getRack().getIndexSelected() != i) {
                g.setColor(game.getCurrentPlayer().getRack().getPieces().get(i).getHead());
                    Hexagon hexagon;
                    hexagon = new Hexagon(new Point(X_position, Y_line));

                    g.fillPolygon(hexagon.getHexagon());
                    g.setColor(Configuration.LineColor);
                    g.drawPolygon(hexagon.getHexagon());

                    g.setColor(game.getCurrentPlayer().getRack().getPieces().get(i).getTail());
                    Hexagon hexagon2;
                    hexagon2 = new Hexagon(new Point(X_position, (int) (Y_line + Hexagon.Height())));

                    g.fillPolygon(hexagon2.getHexagon());
                    g.setColor(Configuration.LineColor);
                    g.drawPolygon(hexagon2.getHexagon());
                }

                X_position += X_gap;
            }

            MouseSpy listener = new MouseSpy();
            addMouseListener(listener);
            MouseWatcher watcher = new MouseWatcher();
            addMouseMotionListener(watcher);
        }

    class MouseWatcher implements MouseMotionListener {
        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }
    }

    class MouseSpy implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            float x = e.getX();
            if (x >= (X_start - Hexagon.Width(0.5)) && x <= (X_start + Hexagon.Width(0.5))) {

                game.getCurrentPlayer().getRack().setPieceSelected(0);
                GameProvider.updateGraphics();
            }
            if (x >= ((X_start + X_gap) - Hexagon.Width(0.5)) && x <= ((X_start + X_gap) + Hexagon.Width(0.5))) {

                game.getCurrentPlayer().getRack().setPieceSelected(1);
                GameProvider.updateGraphics();
            }
            if (x >= ((X_start + (X_gap * 2)) - Hexagon.Width(0.5)) && x <= ((X_start + (X_gap * 2)) + Hexagon.Width(0.5))) {

                game.getCurrentPlayer().getRack().setPieceSelected(2);
                GameProvider.updateGraphics();
            }
            if (x >= ((X_start + (X_gap * 3)) - Hexagon.Width(0.5)) && x <= ((X_start + (X_gap * 3)) + Hexagon.Width(0.5))) {

                game.getCurrentPlayer().getRack().setPieceSelected(3);
                GameProvider.updateGraphics();
            }
            if (x >= ((X_start + (X_gap * 4)) - Hexagon.Width(0.5)) && x <= ((X_start + (X_gap * 4)) + Hexagon.Width(0.5))) {

                game.getCurrentPlayer().getRack().setPieceSelected(4);
                GameProvider.updateGraphics();
            }
            if (x >= ((X_start + (X_gap * 5)) - Hexagon.Width(0.5)) && x <= ((X_start + (X_gap * 5)) + Hexagon.Width(0.5))) {

                game.getCurrentPlayer().getRack().setPieceSelected(5);
                GameProvider.updateGraphics();
            }
            repaint();
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
    }


}
