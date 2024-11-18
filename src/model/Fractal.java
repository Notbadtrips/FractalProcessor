package model;

import java.util.ArrayList;

/**
 *
 * @author Marta 
 */
public abstract class Fractal {

    protected ArrayList<Pattern> patterns; //Array de puntos con coordenadas x e y tipo double
   
    private final String name;
    protected final PuntoUnitario initialCoord;
    protected double reductionRatio; // Ratio de reducción por nivel de profundidad del fractal.
    protected double lineLength;     //Tamaño unitario de la linea del fractal.
    protected Model model;
    protected int maxDepth;
    private int coordsPerPattern;
    public Fractal(Model m,String fractalName, double sideLength,double reduxRatio, int coordsPerPattern) {
        patterns = new ArrayList<>();
        this.coordsPerPattern = coordsPerPattern;
        model=m;
        name = fractalName;
        lineLength = sideLength;
        reductionRatio = reduxRatio;
        initialCoord = new PuntoUnitario(0.5,0.5);
    }

    private void clearSolution() {
        patterns = new ArrayList<>();
    }

    public void solveDepth(int depth){
        if (depth > -1) {
            maxDepth = depth;
            patterns.clear();
            solveRecursive(0, lineLength, initialCoord.getX(), initialCoord.getY());
        }
    }

    public int getIteration() {
        return patterns.size();
    }
    public int getTotalIterations() {
        int iterations = 1;
        for (int i = 1; i < maxDepth; i++) {
            iterations += Math.pow(coordsPerPattern,i);
        }
        return iterations;
    }
    protected abstract void solveRecursive(int depth, double size, double centroidX, double centroidY);
    public ArrayList<Pattern> getPatrones(){
        return patterns;
    }
    @Override
    public String toString(){
        return name;
    }
}
