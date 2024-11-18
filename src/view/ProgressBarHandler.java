package view;



import javax.swing.*;
import java.awt.*;
import model.Model;

/**
 *
 * @author Marta
 */
public class ProgressBarHandler extends Thread{
    private Model model;
    private JProgressBar progressBar;
    private boolean isRunning;
    public ProgressBarHandler(Model m, JProgressBar pB) {
        isRunning = false;
        pB.setMinimum(0);
        pB.setBackground(Color.DARK_GRAY);
        progressBar = pB;
        model = m;
    }

    @Override
    public void run() {
        while (true) {
            int value = isRunning ? model.getProgress(): 0;
            progressBar.setValue(value);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (value == progressBar.getMaximum())
                isRunning = false;
        }
    }

    public void checkProgress() {
        isRunning = true;
    }

    public void setMaximumValue (int setMax) {
        progressBar.setMaximum(setMax);
    }
    public void stopCheckProgress() {
        isRunning = false;
    }
}
