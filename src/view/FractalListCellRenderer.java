package view;



import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import model.Fractal;
import model.Pattern;

/**
 *
 * @author Marta 
 */
public class FractalListCellRenderer extends JLabel implements ListCellRenderer<Fractal> {
    private final static Color[] fractalColor = new Color[]{Color.WHITE};
    private final int size;
    public FractalListCellRenderer() {
        this(25);
    }
    public FractalListCellRenderer(int iconSize){
        setOpaque(true);
        size= iconSize;
        setHorizontalAlignment(LEFT);
        setVerticalAlignment(CENTER);
    }

    /**
     * This is the main renderer for each fractal into the list.
     * it mainly draws the fractal by solving it on 1st depth level.
     * @param list The JList we're painting.
     * @param value The value returned by list.getModel().getElementAt(index).
     * @param index The cells index.
     * @param isSelected True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Fractal> list, Fractal value, int index, boolean isSelected, boolean cellHasFocus) {
        value.solveDepth(1);
        ArrayList<Pattern> coords = value.getPatrones();
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        ImageIcon imic = (ImageIcon) getIcon();
        if (imic == null) {
            imic = new ImageIcon(new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB));
            setIcon(imic);
        }
        Graphics g = imic.getImage().getGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,size,size);
        //We use the same method for getting the coordinates related to the size of the canvas.
        ArrayList<Pattern> cordsIcon = PanellDibuix.getCoordsInCanvas(coords,size,size);
        for (int i = 0; i < cordsIcon.size(); i++) {
            cordsIcon.get(i).setColor(Color.WHITE);
        }
        //We use the same methods for drawing into the panel.
        PanellDibuix.dibujarFractal(g,cordsIcon,size,size);
        setText(value.toString());
        setFont(list.getFont());
        return this;
    }

}
