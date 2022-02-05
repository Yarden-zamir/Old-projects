/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleclick;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import org.jdesktop.swingx.JXLabel;

/**
 *
 * @author hrsid
 */
public class actionButton extends JXLabel implements MouseListener {
    ArrayList<action> actions = new ArrayList<>();
    public actionButton(int size, String icon, String text) {
        this.icon = icon;
        init(size, icon, text);
    }

    private String icon;

    private void init(int size, String icon, String text) {
        setText(text);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setSize(size, size);
        setIcon(new ImageIcon("Rsrc\\Buttons\\" + icon + ".png"));

        addMouseListener(this);
    }

    public void addAction(action act){
        actions.add(act);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setIcon(new ImageIcon("Rsrc\\Buttons\\" + icon + "_Clicked.png"));
        actions.forEach((act) -> {
            act.act(e);
        });
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setIcon(new ImageIcon("Rsrc\\Buttons\\" + icon + ".png"));

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
