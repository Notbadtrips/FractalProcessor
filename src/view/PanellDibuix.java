package view;

import java.awt.*;
import java.awt.event.MouseEvent;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Fractal;
import model.Model;
import model.Pattern;
import model.PuntoUnitario;



/**
 *
 * @author Marta 
 */
public class PanellDibuix extends JPanel implements MouseWheelListener, MouseMotionListener {

    private final Model mod; //Puntero al modelo
    private ArrayList<Pattern> fractalPoints;
    private Point oldMPos, newMPos;

    int posRelY;
    int posRelX;
    private double zoom;

    public PanellDibuix(Model m) {
        mod = m;
        posRelX = getWidth()/2;
        posRelY = getWidth()/2;
        zoom = 1.0;
        setPreferredSize(new Dimension(800, 800));
        addMouseWheelListener(this);
        addMouseMotionListener(this);
    }

    protected static ArrayList<Pattern> getCoordsInCanvas(ArrayList<Pattern> pu, int canvasWidth, int canvasHeight) {
        if (pu == null)
            return null;
        ArrayList<Pattern> convertedCoords = new ArrayList<>();
        for (int i = 0; i < pu.size(); i++) {
            convertedCoords.add(patronUnitToPatronPoint(pu.get(i),canvasWidth,canvasHeight));
        }
        return convertedCoords;
    }

    private static Pattern patronUnitToPatronPoint(Pattern p, int width, int height) {
        PuntoUnitario[] puntosunits = (PuntoUnitario[]) p.getPoints();
        Point[] puntos = new Point[puntosunits.length];
        for (int i = 0; i < puntos.length; i++) {
            puntos[i] = new Point((int) ((puntosunits[i].getX()) * width), (int) ((1.0 - (puntosunits[i].getY())) * height));
        }
        return new Pattern(puntos,p.getColor());
    }

    public void paint(Graphics g) {
        ArrayList<Pattern> coordsNorm = getCoordsInCanvas(fractalPoints,getWidth(),getHeight());
        applyZoomingAndTraslation(coordsNorm);
        dibujarFractal(g,coordsNorm,getWidth(),getHeight());
    }


    protected static void dibujarFractal(Graphics g, ArrayList<Pattern> cordsToDraw, int width, int height) {
        if (cordsToDraw != null) {
            g.clearRect(0, 0, width, height);
            for (int i = 0; i < cordsToDraw.size(); i++) {
                Pattern p = cordsToDraw.get(i);
                dibujarPatronFractal(g, p);
            }
        }
    }
    protected static void dibujarPatronFractal(Graphics g, Pattern patron) {
        Point[] points = (Point[]) patron.getPoints();
        g.setColor(patron.getColor());
        for (int i = 0; i < points.length - 1; i++) {
            g.drawLine(points[i].x, points[i].y, points[i+1].x, points[i+1].y);
        }
        g.drawLine(points[0].x, points[0].y, points[points.length-1].x, points[points.length - 1].y);
    }

    public void setFractalADibujar(Fractal frtl) {
        fractalPoints = frtl.getPatrones();
    }

    public void applyZoomingAndTraslation (ArrayList<Pattern> coords) {
        if (coords != null) {
            for (int i = 0; i < coords.size(); i++) {
                Point[] puntos = (Point[]) coords.get(i).getPoints();
                for (int j = 0; j < puntos.length; j++) {
                    puntos[j].x = puntos[j].x + (int)(puntos[j].x*(zoom - 1.0)) - (int)((getWidth()/2)*(zoom - 1.0)) + posRelX;
                    puntos[j].y = puntos[j].y + (int)(puntos[j].y*(zoom - 1.0))  - (int)((getHeight()/2)*(zoom - 1.0)) + posRelY;
                }
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0 && zoom < 10.0) {
            zoom += 0.1;
            repaint();
        } else if (e.getWheelRotation() > 0 && zoom > 1.0) {
            zoom -= 0.1;
            repaint();
        }
   }

    @Override
    public void mouseDragged(MouseEvent me) {
        newMPos = me.getPoint();
        if(oldMPos == null)
            oldMPos = newMPos;
        posRelX += (newMPos.x - oldMPos.x);
        posRelY += (newMPos.y - oldMPos.y);
        oldMPos = newMPos;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        newMPos = e.getPoint();
        oldMPos = newMPos;
    }
}
