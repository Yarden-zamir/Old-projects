/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Yarden zamir
 */
public class StatsBar extends JLayeredPane {

    public static JTextField nameF = new JTextField();
    public static ProgBar HPbar = new ProgBar("Health"),
            XPbar = new ProgBar("Xp"),
            Staminabar = new ProgBar("Stamina");

    public StatsBar() {
        setOpaque(true);
        setBackground(Color.lightGray);
        setLayout(null);
        setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED)));
        setSize(400, 290);
        setLocation(1000, 25);
        add(HPbar);
        HPbar.setForeground(Color.red);
        add(XPbar);
        add(Staminabar);
        XPbar.setForeground(Color.green);
        Staminabar.setForeground(Color.gray);
        add(nameF);
        nameF.setSize(380, 45);
        nameF.setLocation(5, 210);
        nameF.setHorizontalAlignment(SwingConstants.CENTER);
        nameF.setFont(nameF.getFont().deriveFont(2, 40));
        nameF.setBorder(null);
        nameF.setBackground(null);
        nameF.setEditable(false);
    }

    public static void UpDateStatsBar() {
        HPbar.setValue(Player.HP);
        XPbar.setValue(Player.XP);
        Staminabar.setValue(Player.stamina);
        HPbar.setString(HPbar.getValue() + " / " + HPbar.getMaximum());
        XPbar.setString(XPbar.getValue() + " / " + XPbar.getMaximum());
        Staminabar.setString(Staminabar.getValue() + " / " + Staminabar.getMaximum());
        nameF.setText(Player.name);

    }

}

class ProgBar extends JProgressBar {

    public static int B = 0;

    public ProgBar(String text) {
        setBackground(Color.lightGray);
        setStringPainted(true);
        setSize(380, 40);
        setBorder(new TitledBorder(text));
        setLocation(5, 20 + B);
        setValue(46);
        B += 60;
        setToolTipText("Player's " + text);
    }

}
