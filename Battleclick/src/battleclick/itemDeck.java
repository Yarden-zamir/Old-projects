/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleclick;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 *
 * @author hrsid
 */
public class itemDeck extends JXPanel implements MouseListener{

    private final int width;
    private final Color bg;

    public itemDeck(int width, Color c) {
        this.width = width;
        this.bg = c;

        init();
        setUpCardSpace();
    }

    private void init() {
        setLayout(null);
        setBackground(this.bg);
        setSize(this.width, settings.vCardHeight + 20);
        DropShadowBorder db = new DropShadowBorder();
        db.setShowTopShadow(true);
        db.setShowLeftShadow(true);
        setBorder(db);
        addMouseListener(this);

    }

    private void setUpCardSpace() {
        JXPanel cardSpace = new JXPanel();
        cardSpace.setBackground(bg.darker());
        cardSpace.setSize(this.width , settings.vCardHeight +10);
        cardSpace.setLocation(5, 5);
        add(cardSpace);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
