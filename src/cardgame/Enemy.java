package cardgame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/*
 * @author Yarden zamir
 */
public class Enemy implements ActionListener {

    public Entity ent;
    public String name;
    private Random rnd = new Random();
    public File me = new File(Player.RsrcFolder + "\\Cards\\Enemy");
    public Properties stats = new Properties();
    public int hp;

    public Enemy() {
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\enemy\\enm" + (rnd.nextInt(6)) + ".wav"));
        T.setActionCommand("Tick");
        tick.setActionCommand("m");
        me = me.listFiles()[rnd.nextInt(me.listFiles().length)];
        genStats();
        genEnt();
        PlaceEnt();
        genTurn();
    }

    public boolean checkForHit(int playerRoll) {
        int myRoll = rnd.nextInt(20);
        myRoll += Integer.parseInt(stats.getProperty("agl"));
        return playerRoll >= myRoll;
    }

    JLabel hitHp = new JLabel();
    Timer T = new Timer(3, this);
    public Timer tick = new Timer(10, this);

    public void damageMe(int dmg) {
        hp -= dmg;
//        prevMe();

        //show hit anim
        if (dmg <= 0) {
            hitHp.setText("miss...");
        } else {
            Player.addScore(dmg/2+rnd.nextInt(25));
            hitHp.setText(dmg + "");
        }
        hitHp.setSize(100, 25);
        hitHp.setForeground(Color.red);
        Battle.tileSpace.add(hitHp);
        Battle.tileSpace.setLayer(hitHp, 1001);
        hitHp.setLocation(ent.getX() + rnd.nextInt(49), ent.getY());
        T.start();
        CardDeck.cardPrev.loadEnemy(this);
        if (hp <= 0) {
            int xpGain = 0;
            xpGain += Integer.parseInt(stats.getProperty("hp"));
            xpGain += Integer.parseInt(stats.getProperty("dmg"));
            xpGain += Integer.parseInt(stats.getProperty("def"));
            xpGain += Integer.parseInt(stats.getProperty("agl"));
            xpGain += Integer.parseInt(stats.getProperty("acc"));
            xpGain += rnd.nextInt(25);
            Player.addXp(xpGain);
            die();
        }
    }

    public void die() {
        Battle.tileSpace.remove(ent);
        Battle.enemys.remove(this);
        Battle.tileSpace.setLocation(Battle.tileSpace.getX() + 1, Battle.tileSpace.getY());
        Battle.tileSpace.setLocation(Battle.tileSpace.getX() - 1, Battle.tileSpace.getY());
        Battle.takenLocs.remove(ent.getLocation());
        Battle.turnList.remove(ent.entTurn);
        Battle.turnList.setLocation(Battle.turnList.getX() + 1, Battle.turnList.getY());
        Battle.turnList.setLocation(Battle.turnList.getX() - 1, Battle.turnList.getY());
        GameOperator.drawLootCard(ent.getLocation());
    }

    public void prevMe() {
        Player.ent.hideMovementAction();
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\enm" + (rnd.nextInt(6)) + ".wav"));
        T.setActionCommand("Tick");
        tick.setActionCommand("m");
        CardDeck.cardPrev.loadEnemy(this);
        //think
        int xDist = Player.ent.getX() - ent.getX();
        int yDist = Player.ent.getY() - ent.getY();
        if (stats.getProperty("type").equals("ranged")) {
            if (Player.ent.getDistanceFrom(ent) <= 120) {
                if (xDist < 0) {
                    ent.moveRight();
                } else if (xDist > 0) {
                    ent.moveLeft();
                } else if (yDist < 0) {
                    ent.moveDown();
                } else if (yDist > 0) {
                    ent.moveUp();
                } else {
                    Battle.nextTurn();
                }

            } else if (Player.ent.getDistanceFrom(ent) > 240) {
                if (xDist < 0) {
                    ent.moveLeft();
                } else if (xDist > 0) {
                    ent.moveRight();
                } else {
                    if (yDist < 0) {
                        ent.moveUp();
                    }
                    if (yDist > 0) {
                        ent.moveDown();
                    }
                }
            } else {
                rangeAttackPlayer();
            }
        } else {
            if (Player.ent.getDistanceFrom(ent) <= 60) {
                //attack
                attackPlayer();
            } else if (xDist < 0) {
                ent.moveLeft();
            } else if (xDist > 0) {
                ent.moveRight();
            } else {
                if (yDist < 0) {
                    ent.moveUp();
                }
                if (yDist > 0) {
                    ent.moveDown();
                }
            }

        }
        Player.ent.hideMovementAction();

        //wait 31*10
    }

    private void genStats() {
        try {
            stats.load(new FileInputStream(new File(me + "\\Stats.ini")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
        }
        hp = Integer.parseInt(stats.getProperty("hp"));
    }

    private void genEnt() {
        name = me.getName();
        ent = new Entity(this);
        Battle.tileSpace.add(ent);
        Battle.enemys.add(this);
        ent.setIcon(new ImageIcon(Systems.Graphic.ImgReader.ResizeImage(new File(me + "\\icon.png"))));
    }

    private void genTurn() {
        Battle.turnList.add(ent.entTurn);
    }

    public static Point tileLoc = new Point(96, 96);

    private void PlaceEnt() {
        ent.setLocation(rnd.nextInt(4) * 60 + tileLoc.x, rnd.nextInt(4) * 60 + tileLoc.y);
        while (Battle.takenLocs.contains(ent.getLocation())) {
            ent.setLocation(rnd.nextInt(4) * 60 + 96, rnd.nextInt(4) * 60 + 96);
        }
        Battle.tileSpace.setLayer(ent, 1000);
        Battle.takenLocs.add(ent.getLocation());
    }

    private void attackPlayer() {
        Player.ent.hideMovementAction();
        int playerRoll = rnd.nextInt(20);
        int myRoll = rnd.nextInt(20);
        playerRoll += Player.AGL - 10;
        myRoll += Integer.parseInt(stats.getProperty("acc"));
        if (playerRoll <= myRoll) {
            int dmg = Integer.parseInt(stats.getProperty("dmg"));
            Player.damagePlayer(rnd.nextInt(dmg));
        } else {
            Player.damagePlayer(0);
        }

    }

    private void rangeAttackPlayer() {
        int playerRoll = rnd.nextInt(20);
        int myRoll = rnd.nextInt(20);
        playerRoll += Player.AGL - 10;
        myRoll += Integer.parseInt(stats.getProperty("acc"));
        int dmg = Integer.parseInt(stats.getProperty("dmg"));
        Player.damagePlayer(rnd.nextInt(dmg));
    }

    int x = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Tick")) {
            hitHp.setLocation(hitHp.getX(), hitHp.getY() - 1);
            if (hitHp.getY() <= -100) {
                T.stop();
            }
        } else {
            x++;
            if (x == 1) {
                Player.ent.hideMovementAction();
            }
            if (x >= 35) {
                x = 0;
                tick.stop();
                prevMe();
                Battle.nextTurn();
            }
        }
    }

}
