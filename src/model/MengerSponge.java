
package model;

/**
 *
 * @author Marta 
 */
public class MengerSponge extends Fractal{
    
     public MengerSponge(Model m) {
        super(m,"MengerSponge",0.25, 0.33,4);
    }
    
    
    /*
        Algoritmo recursivo va desde Depth hasta 1, En caso de ser un cuadrado ejecuta 4 llamadas por figura, hay que pasarle x e y
        donde en un inicio será el punto centrico del panel dibujo pero deplazando 1/2 de x e y con tal de que quede centrico, el punto 
        de referencia para pintar es la esquina superior izquierda para Graphics2D.drawRectangle(...)
    */
    public void solveRecursive(int depth, double size, double centroidX, double centroidY){
        if(depth <= maxDepth){ //Mientras no hayamos llegado a 1, continuamos
            double halfSize = (size/3.0);
            double lCentroidX = centroidX-2.0*halfSize;
            double rCentroidX = centroidX+2.0*halfSize;
            double uCentroidY = centroidY+2.0*halfSize;
            double dCentroidY = centroidY-2.0*halfSize;
            
            double newSize = size*reductionRatio;
            halfSize *= 2;
            //Añadimos al array, como puntos calculados para dibujar el cuadrado
            PuntoUnitario[] puntos = new PuntoUnitario[] {
                    new PuntoUnitario (lCentroidX, uCentroidY),
                    new PuntoUnitario (rCentroidX, uCentroidY),
                    new PuntoUnitario (rCentroidX, dCentroidY),
                    new PuntoUnitario (lCentroidX, dCentroidY)
            };
            patterns.add(new Pattern(puntos,model.getColorByDepth(depth)));

            solveRecursive(depth+1, newSize, lCentroidX-halfSize, uCentroidY+halfSize);   //Sup izq
            solveRecursive(depth+1, newSize, centroidX, uCentroidY+halfSize);   //Sup centro
            solveRecursive(depth+1, newSize, rCentroidX+halfSize, uCentroidY +halfSize);  //Sup der
            solveRecursive(depth+1, newSize, rCentroidX+halfSize, centroidY );  //Der centro
            solveRecursive(depth+1, newSize, rCentroidX+halfSize, dCentroidY-halfSize );  //Derecha inf
            solveRecursive(depth+1, newSize, centroidX, dCentroidY-halfSize);   //Inf centro
            solveRecursive(depth+1, newSize, lCentroidX-halfSize, dCentroidY-halfSize );  //Inf izq
            solveRecursive(depth+1, newSize, lCentroidX-halfSize, centroidY );  //Izq centro
        }
    }
    
}
