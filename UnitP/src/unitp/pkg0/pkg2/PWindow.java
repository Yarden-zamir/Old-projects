/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitp.pkg0.pkg2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.shape.Line;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Yarden zamir
 */
public class PWindow extends JFrame {

    private GPaint mainPane = new GPaint();

    /**
     *
     */
    public PWindow() {
        setUp(0, 0, 500, 500, "PWindow", new File(""));
    }

    /**
     *
     * @param x the location of the window on the x axis
     * @param y the location of the window on the y axis
     */
    public PWindow(int x, int y) {
        setUp(x, y, 500, 500, "PWindow", new File(""));
    }

    /**
     *
     * @param x the location of the window on the x axis
     * @param y the location of the window on the y axis
     * @param width the width of the window
     * @param height the height of the window
     */
    public PWindow(int x, int y, int width, int height) {
        setUp(x, y, width, height, "PWindow", new File(""));
    }

    /**
     *
     * @param x the location of the window on the x axis
     * @param y the location of the window on the y axis
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title of the window
     */
    public PWindow(int x, int y, int width, int height, String title) {
        setUp(x, y, width, height, title, new File(""));
    }

    /**
     *
     * @param x the location of the window on the x axis
     * @param y the location of the window on the y axis
     * @param width the width of the window
     * @param height the height of the window
     * @param title the title of the window
     * @param iconLoc the icon of the window
     */
    public PWindow(int x, int y, int width, int height, String title, File iconLoc) {
        setUp(x, y, width, height, "PWindow", new File(""));
    }

    private void setUp(int x, int y, int width, int height, String title, File iconLoc) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        //
        setLocation(x, y);
        setSize(width, height);
        setTitle(title);
//        add(mainPane);
        setContentPane(mainPane);
        getContentPane().setLayout(null);
//        mainPane.setLayout(null);
//        mainPane.setIgnoreRepaint(false);
    }

    /**
     * sets the color of the window
     *
     * @param c the color
     */
    public void setColor(Color c) {
        mainPane.setBackground(c);
    }

    public Color getColor() {
        return this.getBackground();
    }

    /**
     * adds a displayable object to the window
     *
     * @param obj the displayable object
     */
    public void addDisplayableObject(PDisplayableObject obj) {
//        mainPane.add(obj);
        getContentPane().add(obj);
//        obj.setLocation(0, 0);
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        mainPane.drawLine(x1, y1, x2, y2);
    }

}

class GPaint extends JPanel {

    private JLabel canvas = new JLabel();
    ArrayList<Line> lines = new ArrayList<>();

    public GPaint() {
        add(canvas);
        canvas.setSize(500, 500);
        lines.add(new Line(0, 0, 0, 0));
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        lines.add(new Line(x1, y1, x2, y2));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        if (lines.size() > 1000) {

        }
        lines.stream().forEach((l) -> {
            ((Graphics2D) g).drawLine((int) l.getStartX(), (int) l.getStartY(), (int) l.getEndX(), (int) l.getEndY());
        });
    }

}
