package model;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 *
 * @author Marta
 */
public class Pattern {
    private Color color;
    private Point2D[] puntos;
    public Pattern(Point2D[] puntos, Color c) {
        this.puntos = puntos;
        color = c;
    }

    public Point2D[] getPoints() {
        return puntos;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color c) {
        color = c;
    }
}
