/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitp.pkg0.pkg2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Yarden zamir
 */
public class PDisplayableObject extends JLabel {

    private File iconLoc = new File("src/unitp/pkg0/pkg2/Rsrc/city-car.png");

    public PDisplayableObject() {
        setUp(50, 50);
    }

    private void setUp(int width, int height) {
        setSize(width, height);
    }

    public void setColor(Color c) {
        setOpaque(true);
        setBackground(c);
    }

    public void setIcon(File IMG) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(IMG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        Image dimg = img.getScaledInstance(getWidth(), getHeight(), img.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        super.setIcon(imageIcon);
    }

    public void rotateObject(double angle) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(iconLoc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        img = rotate(img, Math.toRadians(angle));
        Image dimg = img.getScaledInstance(getWidth(), getHeight(), img.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        super.setIcon(imageIcon);
    }

    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
}
