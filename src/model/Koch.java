
package model;

/**
 *
 * @author Marta 
 */
public class Koch extends Fractal {

    public Koch(Model m) {
        super(m,"Koch", 0.5, 0.33, 5);
    }

    protected void solveRecursive(int depth, double size, double centroidX, double centroidY) {
        if (depth == maxDepth) { //Caso de que es un triangulo
            double h = Math.sqrt(Math.pow(size, 2) - Math.pow(size / 2.0, 2));
            PuntoUnitario[] puntos = new PuntoUnitario[]{
                    new PuntoUnitario(centroidX, centroidY + h / 2.0),
                    new PuntoUnitario( centroidX - size / 2.0, centroidY - h / 2.0),
                    new PuntoUnitario(centroidX + size / 2.0, centroidY - h / 2.0),

            };
            patterns.add(new Pattern(puntos,model.getColorByDepth(depth)));
        } else {
            double h = Math.sqrt(Math.pow(size, 2) - Math.pow(size / 2.0, 2));
            //Al ser un triangulo dividiremos en 3 las propias llamadas recurvisa
            snowSolve(depth, centroidX, centroidY + h / 2.0, centroidX - size / 2.0, centroidY - h / 2.0, 0);
            snowSolve(depth, centroidX - size / 2.0, centroidY - h / 2.0, centroidX + size / 2.0, centroidY - h / 2.0, 0);
            snowSolve(depth, centroidX, centroidY + h / 2.0, centroidX + size / 2.0, centroidY - h / 2.0, 1);
        }
    }

    private void snowSolve(int depth, double x1, double y1, double x2, double y2, int l) {
        if (depth < maxDepth) {
            double auxX = x2 - x1;
            double auxY = y2 - y1;
            double x3 = x1 + auxX / 3.0;
            double y3 = y1 + auxY / 3.0;
            double x4 = (0.5 * (x1 + x2) - Math.sqrt(3) * (y1 - y2) / 6.0);
            double y4 = (0.5 * (y1 + y2) - Math.sqrt(3) * (x2 - x1) / 6.0);
            if (l == 1) {
                x4 = (0.5 * (x1 + x2) + Math.sqrt(3) * (y1 - y2) / 6.0);
                y4 = (0.5 * (y1 + y2) + Math.sqrt(3) * (x2 - x1) / 6.0);

            }
            double x5 = x1 + 2 * auxX / 3.0;
            double y5 = y1 + 2 * auxY / 3.0;
            PuntoUnitario[] puntos = new PuntoUnitario[]{
                    new PuntoUnitario(x1, y1),
                    new PuntoUnitario(x3, y3),
                    new PuntoUnitario(x4, y4),
                    new PuntoUnitario(x5, y5),
                    new PuntoUnitario(x2, y2)
            };
            patterns.add(new Pattern(puntos,model.getColorByDepth(depth)));
            snowSolve(depth + 1, x1, y1, x3, y3, l);
            snowSolve(depth + 1, x3, y3, x4, y4, l);
            snowSolve(depth + 1, x4, y4, x5, y5, l);
            snowSolve(depth + 1, x5, y5, x2, y2, l);
        }
    }

}
