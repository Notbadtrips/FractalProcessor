
package model;

/**
 * Dragon Lévy C curve
 * @author  Marta 
 */
public class Dragon extends Fractal {

    public Dragon(Model m) {
        super(m,"Lévy Dragon", 0.25, 1, 2);
        // super("Lévy Dragon", 0.25, 1/Math.sqrt(2), 2);
    }

    @Override
    protected void solveRecursive(int depth, double size, double centroidX, double centroidY) {
        solveDragon(depth, size, 0.6, 0.4, 90.0);

    }

    private void solveDragon(int depth, double size, double centroidX, double centroidY, double angle) {
        if (depth == maxDepth) {
            PuntoUnitario[] puntos = new PuntoUnitario[] {
                    new PuntoUnitario(centroidX, centroidY), //primer punto
                    new PuntoUnitario(centroidX + (size * Math.cos(Math.toRadians(angle))),
                            centroidY + (size * Math.sin(Math.toRadians(angle)))) //segundo punto rotado.
            };
            patterns.add(new Pattern(puntos, model.getColorByDepth(depth)));
        } else {
            double newSize = size / Math.sqrt(2); //double newSize = size * reductionRatio;

            solveDragon(depth + 1, newSize, centroidX, centroidY, angle + 45.0);
            centroidX = (centroidX + (newSize * Math.cos(Math.toRadians(angle + 45.0))));
            centroidY = (centroidY + (newSize * Math.sin(Math.toRadians(angle + 45.0))));
            solveDragon(depth + 1, newSize, centroidX, centroidY, angle - 45.0);
        }
    }
}
