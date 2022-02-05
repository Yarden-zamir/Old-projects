/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleclick;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import net.panda.ActionConsole.tools.Animation;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

/**
 *
 * @author hrsid
 */
public class Card extends JXPanel implements MouseListener {

    ArrayList<action> actions = new ArrayList<>();
    public boolean isPrev = false;
    public JXPanel content = new JXPanel();
    public Dimension normalSize = new Dimension(settings.hCardWidth, settings.hCardHeight); //260
    protected int index;
    protected JXLabel icon = new JXLabel();
    DropShadowBorder db = new DropShadowBorder();


    public Card(int index) {
        this.index = index;
        init();
    }

    private void init() {
        addMouseListener(this);
        setLayout(null);
        setBackground(settings.HomeColor.darker());
        setBorder(db);
        db.setShowLeftShadow(true);
        db.setShowTopShadow(true);
    }

    public void addAction(action a) {
        actions.add(a);

    }

    public int getIndex() {
        return index;
    }



    Animation anim1 = new Animation();
    Animation anim2 = new Animation();
    Animation anim3 = new Animation();

    public void moveAnim(Point to, int miliseconds) {
        anim1.move(this, getLocation(), to, miliseconds);
    }

    public void moveToPlaceVert(int p) {
        anim1.move(this, getLocation(), new Point(10, 10 + p * (normalSize.height + 5)), 400);

    }

    public void moveToPlaceH(int p) {
        p--;
        anim1.move(this, getLocation(), new Point(30+normalSize.width+p*(settings.vCardWidth+5), 820-settings.vCardHeight - 50), 400);
    }

    public void aResize(Dimension d) {

        anim3.resize(content, this.getSize(), new Dimension(d.width - 10, d.height - 10), 300);
        anim2.resize(this, this.getSize(), d, 300);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        actions.forEach((act) -> {
            act.act(e);
        });
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
