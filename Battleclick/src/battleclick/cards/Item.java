/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleclick.cards;

import battleclick.Card;
import battleclick.MaterialColors;
import battleclick.settings;
import java.awt.Dimension;
import java.io.File;
import java.util.Random;
import javax.swing.ImageIcon;
import net.panda.ActionConsole.tools.Animation;

/**
 *
 * @author hrsid
 */
public class Item extends Card {

    Animation r1 = new Animation();
    Animation r2 = new Animation();

    public Item(int index) {
        super(index);
        init();
    }

    private void init() {
        setUpIcon();

        setSize(settings.vCardWidth, settings.vCardHeight);

        add(content);
        content.setLayout(null);
        content.setSize(getWidth() - 10, getHeight() - 10);
        content.setLocation(5, 5);
        content.setBackground(MaterialColors.getRandomColor());
    }

    File icfile = new File("Rsrc\\items");

    private void setUpIcon() {
        Random rnd = new Random();
        icfile = icfile.listFiles()[rnd.nextInt(icfile.listFiles().length)];
        content.add(icon);
        icon.setSize(128, 128);
        icon.setLocation(0, 0);

        icon.setIcon(new ImageIcon(icfile.getAbsolutePath()));
    }

    public void becomeThumbnail() {
        r1.resizeImgAnim(icon, icfile, icon.getSize(), new Dimension(settings.vCardWidth - 45, settings.vCardWidth - 45), 300);
        setBackground(settings.HomeColor);

        aResize(new Dimension(settings.vCardWidth - 35, settings.vCardWidth - 35));
    }

    public void becomeCard() {
        r1.resizeImgAnim(icon, icfile, icon.getSize(), new Dimension(128, 128), 400);
        setBackground(settings.HomeColor.darker());

        aResize(new Dimension(settings.vCardWidth, settings.vCardHeight));
    }

    public void beGone() {
        r1.resizeImgAnim(icon, icfile, icon.getSize(), new Dimension(1, 1), 300);
        aResize(new Dimension(1, 1));
    }

}
