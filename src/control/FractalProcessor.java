package control;

import model.Fractal;

import view.View;
/**
 *
 * @author Marta 
 */
public class FractalProcessor extends Thread{
    private Fractal fractal;
    private View vista;
    int dpth;
    public FractalProcessor(View vista,int depth, Fractal frtl) {
        this.vista = vista;
        dpth = depth;
        fractal = frtl;
    }
    @Override
    public void run() {
        fractal.solveDepth(dpth);
        vista.actualizaPanelDibujo(fractal);
    }
}
