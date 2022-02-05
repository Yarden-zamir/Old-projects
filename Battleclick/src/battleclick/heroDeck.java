/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleclick;

import java.awt.Color;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 *
 * @author hrsid
 */
public class heroDeck extends JXPanel {

    private final int height;
    private final Color bg;

    public heroDeck(int height, Color c) {
        this.height = height;
        this.bg = c;
        init();
        setUpCardSpace();

    }

    private void init() {
        setLayout(null);
        setBackground(this.bg);
        setSize(300, this.height);
        DropShadowBorder db = new DropShadowBorder();
        db.setShowTopShadow(true);
        db.setShowLeftShadow(true);
        setBorder(db);
    }

    private void setUpCardSpace() {
        JXPanel cardSpace = new JXPanel();
        cardSpace.setBackground(bg.darker());
        cardSpace.setSize(290, this.height - 10);
        cardSpace.setLocation(5, 5);
        add(cardSpace);
    }

}

