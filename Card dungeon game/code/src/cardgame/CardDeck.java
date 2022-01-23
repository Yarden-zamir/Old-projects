package cardgame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/*
 * @author Yarden zamir
 */
public class CardDeck extends JPanel implements ActionListener {

    public static int adDrawer;
    public static int enDrawer;
    public static int loDrawer;
    public static Card cardPrev = new Card();
    public static statDisplay lvl = new statDisplay("Level", 1);
    public static statDisplay score = new statDisplay("Score", 0);

    public CardDeck() {
        setBackground(Color.lightGray);
        setSize(400, 361);
        setLocation(1000, 315);
        setBorder(new BevelBorder(BevelBorder.RAISED));
        setLayout(null);
        add(cardPrev);
        cardPrev.setLocation(125, 0);

        statDisplay.t = 0;
        statDisplay.Y = 25;
        add(score);
        add(lvl);
        score.setSize(score.getWidth() + 55, score.getHeight());
        lvl.setSize(score.getWidth(), score.getHeight());
        lvl.setText("1");
        score.setText("0");
        setLocation(getLocation().x + 1, getLocation().y + 1);
        setLocation(getLocation().x - 1, getLocation().y - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardGame07.GO.actionPerformed(e);
    }

}

class DeckButton extends JButton {

    private static int y = 10;

    public DeckButton(String name) {
        setSize(100, 100);
        setText(name);
        setToolTipText("Draw " + name + " card");
        setLocation(10, y);
        y += 110;
        setBackground(Color.gray);
//        setEnabled(false);
        setActionCommand(name);
    }

}
