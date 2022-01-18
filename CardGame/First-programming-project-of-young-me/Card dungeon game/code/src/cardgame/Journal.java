package cardgame;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

/*
 * @author Yarden zamir
 */
public class Journal extends JScrollPane {

    private static juTextBox box = new juTextBox();

    public Journal() {
        setSize(975, 55);
        setLocation(25, 25);
        setBorder(new BevelBorder(BevelBorder.RAISED));
        setViewportView(box);
        append("Welocme!");
    }

    public static void append(String text) {
        box.setText(box.getText() + text + "");
    }

}

class juTextBox extends JTextPane {

    public juTextBox() {
        setBackground(Color.lightGray);
        setSelectedTextColor(Color.DARK_GRAY);
        setSelectionColor(Color.gray);
        setFont(getFont().deriveFont(2, 15));
        setAutoscrolls(true);
        setEditable(false);
    }

}
