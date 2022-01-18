/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Yarden zamir
 */
public class Card extends JLayeredPane implements MouseListener {

    private JLabel title = new JLabel();
    public JLabel imag = new JLabel();
    public JTabbedPane tabs = new JTabbedPane();
    private JScrollPane desScrollPane = new JScrollPane();
    private JTextArea desArea = new JTextArea();

    private JList statsList = new JList();
    public JProgressBar hpBar = new JProgressBar();

    public Card() {
        setLayout(null);
        setBorder(new BevelBorder(BevelBorder.RAISED));
        setSize(220, 360);
        setPreferredSize(getSize());
        setOpaque(true);
        setBackground(Color.lightGray);
    }
    public Skill s;

    public void loadSkill(Skill S) {
        s = S;
        clearCard(this);
        setTitle(S.meFolder.getName());
        setImage(Systems.Graphic.ImgReader.ReadImage(new File(S.meFolder + "\\BigIcon.png")));
        tabs.setSize(200, 100);
        add(tabs);
        tabs.setLocation(10, 250);
        setDes(S.stats.getProperty("description"));
    }

    public void loadWeapon(Weapon w) {
        clearCard(this);
        setTitle(w.name);
        setImage(Systems.Graphic.ImgReader.ReadImage(new File(w.myFolder + "\\BigIcon.png")));
        setTabs(w);
        setWepStats(w);
    }

    public void loadArmor(Armour a) {
        clearCard(this);
        setTitle(a.name);
        setImage(Systems.Graphic.ImgReader.ReadImage(new File(a.myFolder + "\\BigIcon.png")));
        setTabs(a);
        setArmStats(a);
    }

    public void loadAdventure(Adventure a) {
        clearCard(this);
        setTitle(a.me.getName());
        setImage(Systems.Graphic.ImgReader.ReadImage(new File(a.me + "\\Icon.png")));
        tabs.setSize(200, 100);
        add(tabs);
        tabs.setLocation(10, 250);
        setDes(a.stats.getProperty("Desc"));
    }

    public void loadEnemy(Enemy e) {
        Random rnd = new Random();
        clearCard(this);
        setTitle(e.name);
        setImage(Systems.Graphic.ImgReader.ReadImage(new File(e.me + "\\Icon.png")));
        tabs.setSize(200, 100);
        add(tabs);
        tabs.setLocation(10, 250);
        setDes(e.stats.getProperty("Desc"));
        setHealthBar(e);
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\enm" + (rnd.nextInt(6)) + ".wav"));
    }
    public Enemy E;

    private void setHealthBar(Enemy e) {
        hpBar.setSize(200, 20);
        imag.add(hpBar);
        hpBar.setLocation(0, 180);
        hpBar.setMaximum(Integer.parseInt(e.stats.getProperty("hp")));
        hpBar.setValue(e.hp);
        hpBar.setString(hpBar.getValue() + "");
        hpBar.setStringPainted(true);
        hpBar.setForeground(Color.darkGray);
        hpBar.setToolTipText(e.me.getName() + "'s health");
        hpBar.setOpaque(false);
        hpBar.setBorderPainted(false);
    }

    private void setTitle(String t) {

        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\draw.wav"));
        for (int i = 0; i < getComponentCount(); i++) {
            getComponent(i).setVisible(true);
        }
        title.addMouseListener(this);
        title.setSize(200, 20);
        title.setBorder(new BevelBorder(BevelBorder.LOWERED));
        title.setText(t);
        add(title);
        title.setLocation(10, 10);
    }

    private void setImage(BufferedImage img) {
        imag.setSize(200, 200);
        imag.setIcon(new ImageIcon(img));
        add(imag);
        imag.setLocation(10, 40);
    }

    public void clearCard(Card c) {
        removeAll();
        title.removeMouseListener(this);
        setLocation(getLocation().x + 1, getLocation().y);
        setLocation(getLocation().x - 1, getLocation().y);
        doLayout();

    }

    private void setTabs(Weapon w) {
        tabs.setSize(200, 100);
        add(tabs);
        tabs.setLocation(10, 250);
        setDes(w.stats.getProperty("Desc"));
    }

    private void setTabs(Armour a) {
        tabs.setSize(200, 100);
        add(tabs);
        tabs.setLocation(10, 250);
        setDes(a.stats.getProperty("Desc"));
    }

    private void setDes(String d) {
        desArea.setWrapStyleWord(true);
        desArea.setLineWrap(true);
        desArea.setEditable(false);
        tabs.add("Description", desScrollPane);
        desArea.setText(d);
        desScrollPane.setViewportView(desArea);
    }

    private void setWepStats(final Weapon w) {
        tabs.add("Stats", statsList);
        statsList.setModel(new AbstractListModel() {

            @Override
            public int getSize() {
                return 4;
            }

            @Override
            public Object getElementAt(int index) {
                switch (index) {
                    case 0:
                        return "DMG : " + w.stats.getProperty("DMG");
                    case 1:
                        return "ACC : " + w.stats.getProperty("ACC");
                    case 2:
                        return "DEF : " + w.stats.getProperty("DEF");
                    case 3:
                        return "VAL : " + w.stats.getProperty("VAL");
                    default:
                        return null;
                }
            }
        });
    }

    private void setArmStats(final Armour a) {
        tabs.add("Stats", statsList);
        statsList.setModel(new AbstractListModel() {

            @Override
            public int getSize() {
                return 4;
            }

            @Override
            public Object getElementAt(int index) {
                switch (index) {
                    case 0:
                        return "DMG : " + a.stats.getProperty("DMG");
                    case 1:
                        return "ACC : " + a.stats.getProperty("ACC");
                    case 2:
                        return "DEF : " + a.stats.getProperty("DEF");
                    case 3:
                        return "VAL : " + a.stats.getProperty("VAL");
                    default:
                        return null;
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clearCard(this);
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\error.wav"));
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
