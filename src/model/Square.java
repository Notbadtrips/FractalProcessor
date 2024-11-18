package model;

/**
 *
 * @author Marta 
 */
public class Square extends Fractal{
    
    public Square(Model m) {
        super(m,"Square",0.5, 0.5,4);
    }

    public void solveRecursive(int depth, double size, double centroidX, double centroidY){
        //Mientras no hayamos llegado a 1, continuamos
        double halfSize = size/2.0;
        double lCentroidX = centroidX-halfSize;
        double rCentroidX = centroidX+halfSize;
        double uCentroidY = centroidY+halfSize;
        double dCentroidY = centroidY-halfSize;

        //Añadimos al array, como puntos calculados para dibujar el cuadrado
        PuntoUnitario[] puntos = new PuntoUnitario[] {
                new PuntoUnitario (lCentroidX, uCentroidY),
                new PuntoUnitario (rCentroidX, uCentroidY),
                new PuntoUnitario (rCentroidX, dCentroidY),
                new PuntoUnitario (lCentroidX, dCentroidY)
        };
        patterns.add( new Pattern(puntos,model.getColorByDepth(depth)));
        if(depth != maxDepth){
            double newSize = size*reductionRatio;
            solveRecursive(depth+1, newSize, lCentroidX, uCentroidY);   //Esquina Superior Izquierda
            solveRecursive(depth+1, newSize, rCentroidX, uCentroidY);   //Esquina Superior Derecha
            solveRecursive(depth+1, newSize, rCentroidX, dCentroidY );  //Esquina Inferior Derecha
            solveRecursive(depth+1, newSize, lCentroidX, dCentroidY );  //Esquina Inferior Izquierda
        }
    }
}
