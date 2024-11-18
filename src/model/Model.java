package model;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Marta 
 */
public class Model{
    Color[] colors;
    private ArrayList<Fractal> fractals;

    private Fractal chosenFractal;
    public Model() {
        colors = new Color[]{Color.BLACK};
        fractals = new ArrayList<>();
        Square sqrFract = new Square(this);
        Sierpinski srpFract = new Sierpinski(this);
        Koch snowFract = new Koch(this);
        MengerSponge alfFract = new MengerSponge(this);
        Dragon dragon = new Dragon(this);
        
        fractals.add(sqrFract);
        fractals.add(srpFract);
        fractals.add(snowFract);
        fractals.add(alfFract);
        fractals.add(dragon);
        
        
    }

    public int getAmountOfFractals(){
        return fractals.size();
    }
    public Fractal getFractal(int idx) {
        if (idx < fractals.size())
            return fractals.get(idx);
        return null;
    }

    public void setColors(Color[] colrs) {
        colors = colrs;
    }
    public Color getColorByDepth(int depth) {
        return colors[depth%colors.length];
    }

    public void setChosenFractal(Fractal frtl) {
        chosenFractal = frtl;
    }
    public synchronized int getProgress() {
        return chosenFractal.getIteration();
    }
}
