package com.ingenious.gui.components;

import com.ingenious.config.Configuration;

import java.awt.*;

/**
 * Created by danig on 10/2/2017.
 */
public class Hexagon
{
    private final Point center;
    private final Polygon hexagon;

    private static int SIZE = Configuration.hexagonSize;
    private static double WIDTH = SIZE * 2;
    private static double HEIGHT = WIDTH * 3 / 4;

    public Hexagon(Point center)
    {
        this.center = center;
        this.hexagon = createHexagon();
    }

    public Polygon getHexagon() { return hexagon; }

    public static double Height() { return HEIGHT; }
    public static double Height(double r) // r: ratio
    {
        if(r > 0)
            return HEIGHT * r;
        else
            return 0;
    }
    public static double Width() { return WIDTH; }
    public static double Width(double r) // r: ratio
    {
        if(r > 0)
            return WIDTH * r;
        else
            return 0;
    }

    private Polygon createHexagon()
    {
        Polygon polygon = new Polygon();
        
        //clockwise, with starting point on the far left
        polygon.addPoint(
                (int) (center.x - Width(0.5)),    // -1/2w, y
                center.y
        );
        polygon.addPoint(
                (int) (center.x - Width(0.25)),    // -1/4w, -1/2h
                (int) (center.y - Height(0.5))
        );
        polygon.addPoint(
                (int) (center.x + Width(0.25)),    // +1/4w, -1/2h
                (int) (center.y - Height(0.5))
        );
        polygon.addPoint(
                (int) (center.x + Width(0.5)),    // +1/2w, y
                center.y
        );
        polygon.addPoint(
                (int) (center.x + Width(0.25)),    // +1/4w, +1/2h
                (int) (center.y + Height(0.5))
        );
        polygon.addPoint(
                (int) (center.x - Width(0.25)),    // -1/4w, +1/2h
                (int) (center.y + Height(0.5))
        );
        return polygon;
    }
}
