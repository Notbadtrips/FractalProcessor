package model;

/**
 *
 * @author Marta 
 */
public class Sierpinski extends Fractal {
    private final static double  PITHAGORAS_CONST_APOTHEM = Math.sqrt(3.0) / 6.0;
    private final static double PITHAGORAS_CONST_TOP = Math.sqrt(3.0) / 3.0;
    public Sierpinski(Model m) {
        super(m,"Sierpinski", 0.7, 0.5, 3);
    }

    @Override
    protected void solveRecursive(int depth, double size, double centroidX, double centroidY) {
        double newSize =  size * reductionRatio;
        double apothem = PITHAGORAS_CONST_APOTHEM*size; //Apotema
        double bottomY = centroidY-apothem;
        if (depth == maxDepth) {
            PuntoUnitario[] puntos = new PuntoUnitario[] {
                    new PuntoUnitario(centroidX - newSize, bottomY),                        // vertice izq
                    new PuntoUnitario(centroidX + newSize, bottomY),                        // vertice derecho
                    new PuntoUnitario(centroidX, centroidY + PITHAGORAS_CONST_TOP * size),  // vertice top
            };
            patterns.add(new Pattern(puntos,model.getColorByDepth(depth)));
        } else {
            double apothemNewSize = newSize * PITHAGORAS_CONST_APOTHEM; //nuevo apotema.
            solveRecursive(depth+1, newSize, centroidX - newSize/2.0, bottomY+apothemNewSize);
            solveRecursive(depth+1, newSize, centroidX + newSize/2.0, bottomY+apothemNewSize);
            solveRecursive(depth+1, newSize, centroidX, centroidY+apothem);
        }
    }

}
