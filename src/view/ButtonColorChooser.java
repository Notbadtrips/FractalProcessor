package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Marta
 */
public class ButtonColorChooser extends JPanel implements ActionListener,ChangeListener{

    private JButton[] botones;
    private int buttonIndex;
    private JColorChooser colorChooser;

    public ButtonColorChooser(String label, int numeroBotones) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel title = new JLabel(label, JLabel.CENTER);
        title.setForeground(Color.WHITE);
        this.add(title);
        botones = new JButton[numeroBotones];
        colorChooser = new JColorChooser(Color.BLACK);
        colorChooser.setBorder(null);
        colorChooser.getSelectionModel().addChangeListener(this);
        Random r = new Random();
        //Añadimos y creamos los botones
        for (int i = 0; i < numeroBotones; i++) {
            int indice = i;
            JButton b = new JButton("Level "+(i+1));
            b.addActionListener(this);
            this.add(b);
            botones[i] = b;
            Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
            cambiarColorDeBoton(color,i);
        }
    }

    private void showColorChooserForButton(int btnIdx) {
        JFrame ventana = new JFrame("Choose color for level " + btnIdx);
        ventana.add(colorChooser);
        ventana.setResizable(false);
        ventana.pack();
        ventana.setVisible(true);
    }

    private void cambiarColorDeBoton(Color color, int buttonIndex) {
        botones[buttonIndex].setForeground(color);
        ImageIcon btnIcon = (ImageIcon) botones[buttonIndex].getIcon();
        if (btnIcon == null) {
            btnIcon = new ImageIcon(new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB));
            botones[buttonIndex].setIcon(btnIcon);
        }
        Graphics g  = btnIcon.getImage().getGraphics();
        int h = btnIcon.getIconHeight();
        int w = btnIcon.getIconWidth();
        g.setColor(color); //Color del cuadrado
        g.fillRect(0, 0, w, h);
        g.setXORMode(Color.DARK_GRAY);
        g.drawRect(0, 0,  w, h);
    }
    
    public Color getColor(int idx){
        if (idx < botones.length)
            return botones[idx].getForeground();
        else
            return null;
    }

    public Color[] getColors(){
        Color[] colors = new Color[botones.length];
        for (int i = 0 ; i < colors.length; i++)
        {
            colors[i] = botones[i].getForeground();
        }
        return colors;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        for (int i = 0; i < botones.length; i++) {
            if (btn == botones[i]) {
                buttonIndex = i;
                showColorChooserForButton(i);
                break;
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Color newColor = colorChooser.getColor();
        cambiarColorDeBoton(newColor, buttonIndex);
    }
}
