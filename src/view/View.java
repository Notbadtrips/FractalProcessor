package view;

import control.Control;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Fractal;
import model.Model;


/**
 *
 * @author Marta 
 */
public class View extends JFrame implements ActionListener {

    private Model model;
    private Control control; //Objeto del componente control.
    private PanellDibuix panellDib; // Puntero al panel de dibujo
    private JProgressBar pBar; // Progress bar para observar el progreso de ejecución.
    private JFormattedTextField depthField; //Numero de profundidad
    private JComboBox<Fractal> fractalOpts;
    private ProgressBarHandler progressBar;
    private ButtonColorChooser buttonColors;

    private JButton start, stop;
    private JPanel optsPanel;
    
//    private BotonesColores botonColores;

    public View(String s, Model mod, Control ctrl) {
        super(s);
        model = mod;
        control = ctrl;
        optsPanel = new JPanel();
        optsPanel.setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout());
        optsPanel.setLayout(new BoxLayout(optsPanel,BoxLayout.X_AXIS));

        buildFractalPanel();
        buildDepthPanel();
        buildColorPanel();

        panellDib = new PanellDibuix(model);
        buildBtnPanel();
        add(BorderLayout.NORTH, optsPanel);
        add(BorderLayout.CENTER, panellDib);

        pBar = new JProgressBar();
        progressBar = new ProgressBarHandler(mod,pBar);
        add(pBar, BorderLayout.SOUTH);
        progressBar.start();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == start) {
            int depth = Integer.parseInt(depthField.getText()); //Recogemos el valor de la profundidad
            model.setColors(buttonColors.getColors());
            Fractal frtl = (Fractal) fractalOpts.getSelectedItem();
            model.setChosenFractal(frtl);
            progressBar.setMaximumValue(frtl.getTotalIterations());
            progressBar.checkProgress();
            control.start(depth, frtl);
        } else if (btn == stop) {
            progressBar.stopCheckProgress();
            control.stop();
        }
    }

    public void actualizaPanelDibujo(Fractal fractProcesado) {
        panellDib.setFractalADibujar(fractProcesado);
        panellDib.repaint();
    }

    private void buildDepthPanel() {
        JPanel p = new JPanel();
        JLabel title = new JLabel("Depth", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        depthField = new JFormattedTextField(0);
        depthField.setHorizontalAlignment(JTextField.CENTER);
        depthField.setPreferredSize(new Dimension(30,10));
        p.setBackground(optsPanel.getBackground());
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(title);
        p.add(depthField);
        optsPanel.add(p);
    }

    private void buildFractalPanel() {
        JLabel title = new JLabel("Fractal", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        fractalOpts = new JComboBox();
        FractalListCellRenderer fractalRenderer = new FractalListCellRenderer();
        fractalOpts.setMaximumRowCount(3);
        fractalOpts.setRenderer(fractalRenderer);
        for (int i = 0; i < model.getAmountOfFractals(); i++) {
            fractalOpts.addItem(model.getFractal(i));
        }
        optsPanel.add(title);
        optsPanel.add(fractalOpts);
    }

    private void buildColorPanel() {
        buttonColors = new ButtonColorChooser("Color by depth",3);
        buttonColors.setBackground(optsPanel.getBackground());
        optsPanel.add(buttonColors);
    }

    private void buildBtnPanel() {
        JPanel p = new JPanel();
        p.setBackground(optsPanel.getBackground());
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        start = new JButton("Start");
        stop = new JButton("Stop");
        start.addActionListener(this);
        stop.addActionListener(this);
        p.add(start);
        p.add(stop);
        optsPanel.add(p);
    }    
}
