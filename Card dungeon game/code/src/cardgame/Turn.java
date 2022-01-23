
package cardgame;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/* 
 * @author Yarden zamir
 */
public class Turn extends JLabel{
    Entity owner;
    public Turn(Entity e) {
        owner=e;
        setLayout(null);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setSize(90, 25);
        setPreferredSize(getSize());
        setBackground(Color.gray);
        setOpaque(true);
        setIconTextGap(0);
        setText(e.getToolTipText());
        setToolTipText(getText());
    }
    public void myTurn(){
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.red.brighter(), Color.red.darker()));
        Journal.append("\nit is "+getText()+"'s turn...");
        
        if (owner.getToolTipText().equals(Player.name))
        {
            
        }else {
            owner.me.tick.setActionCommand("m");
            owner.me.tick.start();
        }
//        owner.prevMe();
        
    }

}
