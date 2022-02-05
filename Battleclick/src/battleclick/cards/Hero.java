/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleclick.cards;

import battleclick.Card;
import battleclick.MaterialColors;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import org.jdesktop.swingx.JXPanel;

/**
 *
 * @author hrsid
 */
public class Hero extends Card {


    public final JXPanel itemBar = new JXPanel();
    public ArrayList<Item> equiped = new ArrayList<>();

    public Hero(int index) {
        super(index);
        init();
    }

    private void init() {
        setUpIcon();

        setSize(normalSize);

        add(content);
        content.setLayout(null);
        content.setSize(getWidth() - 10, getHeight() - 10);
        content.setLocation(5, 5);
        content.setBackground(MaterialColors.getRandomColor());
        setUpBar();
    }

    private void setUpBar() {
        itemBar.setSize((normalSize.width * 2 - 20), normalSize.height - 20);
        itemBar.setLocation(5, normalSize.height + 5);
        itemBar.setBackground(getBackground().brighter());
        itemBar.setOpaque(true);
        content.add(itemBar);
    }

    private void setUpIcon() {
        File icfile = new File("Rsrc\\Heroes");
        Random rnd = new Random();
        icfile = icfile.listFiles()[rnd.nextInt(icfile.listFiles().length)];
        content.add(icon);
        icon.setSize(128, 128);
        icon.setLocation(0, 0);

        icon.setIcon(new ImageIcon(icfile.getAbsolutePath()));
    }

    public void addItem(Item itm) {
        equiped.add(itm);
    }

    public void removeItem(Item itm) {
        equiped.remove(itm);
    }
}
