/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Yarden zamir
 */
public class Skill extends JLabel implements MouseListener {

    public static ArrayList<Skill> shownSkills = new ArrayList<>();
    private static Point P = new Point(300, 270);
    public boolean Ready = false;
    public boolean learned = false;
    public static Skill displayedSkill;
    Random rnd = new Random();
    File skillsFolder = new File(Player.RsrcFolder + "\\Cards\\Skill");
    File meFolder;
    public Properties stats = new Properties();

    public Skill() {
        setOpaque(true);
        setEnabled(false);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setSize(75, 75);
        setLocation(P);
        addMouseListener(this);
        meFolder = (skillsFolder.listFiles()[rnd.nextInt(skillsFolder.listFiles().length)]);
        for (int i = 0; i < shownSkills.size(); i++) {

            if (meFolder.getName().equals(shownSkills.get(i).meFolder.getName())) {
                meFolder = (skillsFolder.listFiles()[rnd.nextInt(skillsFolder.listFiles().length)]);
                i = 0;
            }
        }
        setIcon(new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(meFolder + "\\Icon.png"))));
        setToolTipText(meFolder.getName());

        try {
            stats.load(new FileInputStream(new File(meFolder + "\\Stats.ini")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setShown() {
        setEnabled(true);

    }

    public static void loadDisabledSkills() {

        for (int i = 0; i < 8; i++) {
            P = new Point(P.x, P.y - 100);
            shownSkills.add(new Skill());
            Inventory.skillsPane.add(shownSkills.get(shownSkills.size() - 1));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.isEnabled()) {

//            setLocation(300, 270);
            int restX = getX() - 300;
            int restY = getY() - 270;
            for (int i = 0; i < shownSkills.size(); i++) {
                shownSkills.get(i).setLocation(shownSkills.get(i).getX() - restX, shownSkills.get(i).getY() - restY);
            }
            
            displayedSkill = this;
            Inventory.skillsCardD.loadSkill(this);
            if (Player.skillpoints >= Integer.parseInt(stats.getProperty("LVL")) && !learned) {
                Inventory.learnSkill.setEnabled(true);
                Inventory.equipSkill.setEnabled(false);
                System.out.println("Stats");
            }
            if (learned) {
                Inventory.learnSkill.setEnabled(false);
                Inventory.equipSkill.setEnabled(true);
            }else {
                Inventory.equipSkill.setEnabled(false);
            }
            if (stats.getProperty("type") == "@permAttackSkill" || stats.getProperty("type") == "@permAntiSkill") {
                Inventory.equipSkill.setEnabled(false);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (Ready) {
            setBorder(new BevelBorder(BevelBorder.RAISED, Color.RED, Color.BLUE));
        } else {
            setBorder(new BevelBorder(BevelBorder.RAISED));

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (Ready) {
            setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, Color.BLUE));
        } else {
            setBorder(new BevelBorder(BevelBorder.LOWERED));

        }
    }

}

class SkillTray extends JButton implements MouseListener {

    Skill sorce;

    public SkillTray(Skill s) {
        sorce = s;
        setSize(75, 75);
        setIcon(sorce.getIcon());
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        addMouseListener(this);
        setToolTipText(sorce.meFolder.getName());
        setEnabled(GameBoard.isHotbarEnabled);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isEnabled()&&Player.stamina>=Integer.parseInt(sorce.stats.getProperty("cost"))) {
            Journal.append("\n" + sorce.meFolder.getName() + " selected");
            //load to display (battle)
            Battle.cardDisplay.loadSkill(sorce);
            Battle.selectSkill(sorce);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Random rnd = new Random();
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\clicks\\click" + (rnd.nextInt(3) + 1) + ".ogg"));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\slotSound.wav"));
        setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

}
