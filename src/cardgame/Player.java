/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Yarden zamir
 */
public class Player implements ActionListener {

    public static Entity ent;
    private static Random rnd = new Random();
    public static File RsrcFolder = new File(".\\.\\Rsrc");
    public static int STR, DEX, CON, WIS, INT, LCK;
    public static int LVL=1;
    public static int DMGb, DEFb, ACCb, AGLb, LOTb;
    public static int DMG, DEF, ACC, LOOT, AGL;
    public static int skillpoints = 1;
    public static ArrayList<Skill> playersSkills = new ArrayList<>();
    public static String name = "";
    public static int HP;
    public static int maxHp;
    public static int XP;
    public static int stamina;
    public static ArrayList<String> immunites = new ArrayList<>();
    public static ArrayList<String> criticBunus = new ArrayList<>();
    public static boolean isPlayerTurn = false;
    public static JLabel hitHp = new JLabel();
    public static Timer T;
    public static int maxActions = 3;
    public static int score=0;

    public static void genIcon() {
        ent = new Entity(Player.name);
        ent.setIcon(new ImageIcon(Systems.Graphic.ImgReader.ResizeImage(new File(RsrcFolder + "\\PlayerIcon.png"))));
    }

    public static void addXp(int xpGain) {
        addScore(1);
        Journal.append("\nyou got: "+xpGain+"XP");
        XP += xpGain;
        StatsBar.UpDateStatsBar();
        if (XP >= StatsBar.XPbar.getMaximum()) {
            XP += -StatsBar.XPbar.getMaximum();
            StatsBar.XPbar.setMaximum(StatsBar.XPbar.getMaximum() + StatsBar.XPbar.getMaximum() / 4);
            StatsBar.UpDateStatsBar();
            lvlUp();
        }
    }
    public static void lvlUp(){
        addScore(2);
        LVL++;
        skillpoints++;
        CardDeck.lvl.setText(Player.LVL+"");
        GameBoard.inv.refreshStats();
        addScore(10+LVL);
    }
    public static void addScore(int scoreToAdd){
        score+=scoreToAdd;
        CardDeck.score.setText(score+"");
    }

    public static void addStamina(int stmG) {
        stamina += stmG;
        StatsBar.UpDateStatsBar();
    }

    public static void addHP(int hp){
        HP+=hp;
        StatsBar.UpDateStatsBar();
    }
    public static void damagePlayer(int dmg) {
        T = new Timer(25, new Player());
        GameBoard.statsBar.add(hitHp);
        GameBoard.statsBar.setLayer(hitHp, 1000);
        if (dmg <= 0) {
            hitHp.setText("miss...");
        } else {
            hitHp.setText(dmg + "");
        }
        hitHp.setForeground(Color.red);
        int x = rnd.nextInt(200) + 50;
        hitHp.setLocation(x, 25);
        hitHp.setSize(50, 25);
        Player.HP -= dmg;
        StatsBar.UpDateStatsBar();
        T.start();
        //deadCheck
        if (Player.HP <= 0) {
            
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        hitHp.setLocation(hitHp.getX(), hitHp.getY() - 1);
        if (hitHp.getY() <= -25) {
            T.stop();
        }
    }
}
