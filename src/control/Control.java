package control;

import main.Main;
import model.Fractal;
import model.Model;




/**
 * Actua como intermediario entre la vista y el modelo
 *
 * @author Marta
 */
public class Control{

    private Main prog; // Puntero al programa principal

    private FractalProcessor fractalProcessor;
    private Model model; // Puntero al modelo

    public Control(Main p, Model m) {
        prog = p;
        model = m;
    }

    /**
     * Ejecuta un proceso para realizar el fractal en una profundidad en caso de que aun no haya ningun proceso activo.
     * @param profundidad
     * @param fract
     */
    public void start(int profundidad, Fractal fract) {
        if (fractalProcessor == null || !fractalProcessor.isAlive()) {
            fractalProcessor = new FractalProcessor(prog.getView(),profundidad,fract);
            fractalProcessor.start();
        }
    }

    /**
     * Para completamente el procesamiento del fractal.
     * Para que fuese correcto, deberia llamar a una función que seteara la variable para parar el proceso, implicando
     * que no deberia ser recursivo el procesamiento del fractal.
     */
    public void stop() {
        if (fractalProcessor != null && fractalProcessor.isAlive()) {
            fractalProcessor.stop();
        }
    }

}
