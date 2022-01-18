package cardgame;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicArrowButton;

/*
 * @author Yarden zamir
 */
public class Entity extends JLabel implements ActionListener, MouseListener {

    public Enemy me;
    public JButton up = new BasicArrowButton(SwingConstants.NORTH);
    public JButton right = new BasicArrowButton(SwingConstants.EAST);
    public JButton left = new BasicArrowButton(SwingConstants.WEST);
    public JButton down = new BasicArrowButton(SwingConstants.SOUTH);
    public int x = 0;
    public Turn entTurn;
    public Random rnd = new Random();
    public static int GenedTiles = 0;
    public Timer upTimer = new Timer(1, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            x++;
            if (x == 31) {
                upTimer.stop();
                x = 0;
                Battle.takenLocs.add(getLocation());
                if (getY() <= 31) {
                    Battle.takenLocs.remove(getLocation());
//                    setLocation(getX(), 336);
                    Battle.takenLocs.add(getLocation());
                    GenedTiles++;
                    Battle.gameTiles.add(new Tile());

                    Battle.gameTiles.get(Battle.gameTiles.size() - 1).genMe(3);
                    Battle.tileSpace.add(Battle.gameTiles.get(Battle.gameTiles.size() - 1));
                    if (getToolTipText().equals(Player.name)) {
                        Enemy.tileLoc.setLocation(96, 96);
                        setLocation(getX(), 336);
                        for (int i = 0; i < Battle.gameTiles.size(); i++) {
                            Battle.gameTiles.get(i).setLocation(Battle.gameTiles.get(i).getX(), Battle.gameTiles.get(i).getY() + 360);
                        }
                        for (int i = 0; i < Battle.takenLocs.size(); i++) {
                            Battle.takenLocs.get(i).setLocation(Battle.takenLocs.get(i).getX(), Battle.takenLocs.get(i).getY() + 360);
                        }
                        for (int i = 0; i < Battle.enemys.size(); i++) {
                            Battle.enemys.get(i).ent.setLocation(Battle.enemys.get(i).ent.getX(), Battle.enemys.get(i).ent.getY() + 360);
                        }

                    } else {
                        me.tileLoc.setLocation(me.tileLoc.getX(), me.tileLoc.getY() - 360);
                    }

                    Battle.genEncunter();
                }
                if (getToolTipText().equals(Player.name)) {
                    Battle.useAction(1);
                    showMovmentAction();
                }
            } else {
                setLocation(getX(), getY() - 2);
            }
        }
    });
    public Timer rightTimer = new Timer(1, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            x++;
            if (x == 31) {
                rightTimer.stop();
                x = 0;
                Battle.takenLocs.add(getLocation());
                if (getX() >= 337) {
                    Battle.takenLocs.remove(getLocation());
//                    setLocation(36, getY());
                    Battle.gameTiles.add(new Tile());
                    Battle.gameTiles.get(Battle.gameTiles.size() - 1).genMe(2);
                    Battle.tileSpace.add(Battle.gameTiles.get(Battle.gameTiles.size() - 1));
                    if (getToolTipText().equals(Player.name)) {
                        Enemy.tileLoc.setLocation(96, 96);
                        setLocation(36, getY());
                        for (int i = 0; i < Battle.gameTiles.size(); i++) {
                            Battle.gameTiles.get(i).setLocation(Battle.gameTiles.get(i).getX() - 360, Battle.gameTiles.get(i).getY());
                        }
                        for (int i = 0; i < Battle.takenLocs.size(); i++) {
                            Battle.takenLocs.get(i).setLocation(Battle.takenLocs.get(i).getX() - 360, Battle.takenLocs.get(i).getY());
                        }
                        for (int i = 0; i < Battle.enemys.size(); i++) {
                            Battle.enemys.get(i).ent.setLocation(Battle.enemys.get(i).ent.getX() - 360, Battle.enemys.get(i).ent.getY());
                        }

                    } else {
                        me.tileLoc.setLocation(me.tileLoc.getX() + 360, me.tileLoc.getY());
                    }
                    Battle.genEncunter();
                }
                if (getToolTipText().equals(Player.name)) {
                    Battle.useAction(1);
                    showMovmentAction();
                }
            } else {
                setLocation(getX() + 2, getY());
            }

        }
    });
    public Timer leftTimer = new Timer(1, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            x++;
            if (x == 31) {
                leftTimer.stop();
                x = 0;
                Battle.takenLocs.add(getLocation());
                if (getX() <= 31) {
                    Battle.takenLocs.remove(getLocation());
//                    setLocation(336, getY());
                    Battle.gameTiles.add(new Tile());
                    Battle.gameTiles.get(Battle.gameTiles.size() - 1).genMe(4);
                    Battle.tileSpace.add(Battle.gameTiles.get(Battle.gameTiles.size() - 1));
                    if (getToolTipText().equals(Player.name)) {
                        Enemy.tileLoc.setLocation(96, 96);
                        setLocation(336, getY());
                        for (int i = 0; i < Battle.gameTiles.size(); i++) {
                            Battle.gameTiles.get(i).setLocation(Battle.gameTiles.get(i).getX() + 360, Battle.gameTiles.get(i).getY());
                        }
                        for (int i = 0; i < Battle.takenLocs.size(); i++) {
                            Battle.takenLocs.get(i).setLocation(Battle.takenLocs.get(i).getX() + 360, Battle.takenLocs.get(i).getY());
                        }
                        for (int i = 0; i < Battle.enemys.size(); i++) {
                            Battle.enemys.get(i).ent.setLocation(Battle.enemys.get(i).ent.getX() + 360, Battle.enemys.get(i).ent.getY());
                        }
                    } else {
                        me.tileLoc.setLocation(me.tileLoc.getX() - 360, me.tileLoc.getY());
                    }
                    Battle.genEncunter();
                }
                if (getToolTipText().equals(Player.name)) {
                    Battle.useAction(1);
                    showMovmentAction();
                }
            } else {
                setLocation(getX() - 2, getY());
            }
        }
    });
    public Timer downTimer = new Timer(1, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            x++;
            if (x == 31) {
                downTimer.stop();
                x = 0;
                Battle.takenLocs.add(getLocation());
                if (getY() >= 337) {
//                    setLocation(getX(), 36);
                    Battle.gameTiles.add(new Tile());
                    Battle.gameTiles.get(Battle.gameTiles.size() - 1).genMe(1);
                    Battle.tileSpace.add(Battle.gameTiles.get(Battle.gameTiles.size() - 1));
                    if (getToolTipText().equals(Player.name)) {
                        Enemy.tileLoc.setLocation(96, 96);
                        setLocation(getX(), 36);
                        for (int i = 0; i < Battle.gameTiles.size(); i++) {
                            Battle.gameTiles.get(i).setLocation(Battle.gameTiles.get(i).getX(), Battle.gameTiles.get(i).getY() - 360);
                        }
                        for (int i = 0; i < Battle.takenLocs.size(); i++) {
                            Battle.takenLocs.get(i).setLocation(Battle.takenLocs.get(i).getX(), Battle.takenLocs.get(i).getY() - 360);
                        }

                        for (int i = 0; i < Battle.enemys.size(); i++) {
                            Battle.takenLocs.remove(Battle.enemys.get(i).ent.getLocation());
                            Battle.enemys.get(i).ent.setLocation(Battle.enemys.get(i).ent.getX(), Battle.enemys.get(i).ent.getY() - 360);
                            Battle.takenLocs.add(Battle.enemys.get(i).ent.getLocation());

                        }

                    } else {
                        Enemy.tileLoc.setLocation(me.tileLoc.getX(), me.tileLoc.getY() + 360);
                    }
                    Battle.genEncunter();

                }
                if (getToolTipText().equals(Player.name)) {
                    Battle.useAction(1);
                    showMovmentAction();
                }
            } else {
                setLocation(getX(), getY() + 2);
            }
        }
    });

    public Entity(String name) {

        setSize(50, 50);
        setToolTipText(name);
        setOpaque(true);
        up.setActionCommand("up");
        down.setActionCommand("down");
        right.setActionCommand("right");
        left.setActionCommand("left");
        up.addActionListener(this);
        down.addActionListener(this);
        right.addActionListener(this);
        left.addActionListener(this);
        up.setSize(50, 50);
        down.setSize(50, 50);
        left.setSize(50, 50);
        right.setSize(50, 50);
        entTurn = new Turn(this);

    }

    public Entity(Enemy e) {
        setSize(50, 50);
        setToolTipText(e.name);
        setOpaque(true);
        up.setActionCommand("up");
        down.setActionCommand("down");
        right.setActionCommand("right");
        left.setActionCommand("left");
        up.addActionListener(this);
        down.addActionListener(this);
        right.addActionListener(this);
        left.addActionListener(this);
        up.setSize(50, 50);
        down.setSize(50, 50);
        left.setSize(50, 50);
        right.setSize(50, 50);
        entTurn = new Turn(this);
        me = e;
        addMouseListener(this);
    }

    public void prevMe() {
        if (getToolTipText().equals(Player.name)) {

        } else {
            me.prevMe();
        }
    }

    public void showMovmentAction() {
        System.gc();
        GameBoard.disableHotbar();
        GameOperator.B.tileSpace.setLayer(up, 1010);
        GameOperator.B.tileSpace.setLayer(down, 1001);
        GameOperator.B.tileSpace.setLayer(right, 1001);
        GameOperator.B.tileSpace.setLayer(left, 1010);
        if (!(Battle.takenLocs.contains(new Point(getX(), getY() - 60)))) {
            getParent().add(up);
            up.setLocation(getX(), getY() - 60);
            GameOperator.B.tileSpace.setLayer(up, 100);
            up.setVisible(true);
        }
        if (!(Battle.takenLocs.contains(new Point(getX(), getY() + 60)))) {
            getParent().add(down);
            down.setLocation(getX(), getY() + 60);
            down.setVisible(true);
            GameOperator.B.tileSpace.setLayer(down, 100);
        }
        if (!(Battle.takenLocs.contains(new Point(getX() + 60, getY())))) {
            getParent().add(right);
            right.setLocation(getX() + 60, getY());
            right.setVisible(true);
            GameOperator.B.tileSpace.setLayer(right, 100);
        }
        if (!(Battle.takenLocs.contains(new Point(getX() - 60, getY())))) {
            getParent().add(left);
            left.setLocation(getX() - 60, getY());
            left.setVisible(true);
            GameOperator.B.tileSpace.setLayer(left, 100);
        }

    }

    public void hideMovementAction() {
        getParent().remove(up);
        getParent().remove(down);
        getParent().remove(right);
        getParent().remove(left);
        getParent().setLocation(getParent().getX() + 1, getParent().getY());
        getParent().setLocation(getParent().getX() - 1, getParent().getY());
        up.setVisible(false);
        down.setVisible(false);
        right.setVisible(false);
        left.setVisible(false);

    }

    public void moveUp() {
//        setLocation(up.getLocation());
        if (!(Battle.takenLocs.contains(new Point(getX(), getY() - 60)))) {
            Battle.takenLocs.remove(getLocation());
            upTimer.start();

        } else {
            if (rnd.nextBoolean()) {
                moveLeft();
            } else {
                moveRight();
            }
        }

    }

    public void moveDown() {
        if (!(Battle.takenLocs.contains(new Point(getX(), getY() + 60)))) {
            Battle.takenLocs.remove(getLocation());
            downTimer.start();
        } else {
            if (rnd.nextBoolean()) {
                moveLeft();
            } else {
                moveRight();
            }
        }

    }

    public void moveRight() {
        if (!(Battle.takenLocs.contains(new Point(getX() + 60, getY())))) {
            Battle.takenLocs.remove(getLocation());
            rightTimer.start();
        } else {
            if (rnd.nextBoolean()) {
                moveUp();
            } else {
                moveDown();
            }
        }
    }

    public void moveLeft() {
        if (!(Battle.takenLocs.contains(new Point(getX() - 60, getY())))) {
            Battle.takenLocs.remove(getLocation());
            leftTimer.start();
        } else {
            if (rnd.nextBoolean()) {
                moveUp();
            } else {
                moveDown();
            }
        }
    }

    public double getDistanceFrom(Entity e) {
        return e.getLocation().distance(this.getLocation());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\clicks\\click" + (rnd.nextInt(3) + 1) + ".ogg"));
        hideMovementAction();
        Battle.takenLocs.remove(getLocation());
        switch (e.getActionCommand()) {
            case "up":
                moveUp();
                break;
            case "down":
                moveDown();
                break;
            case "right":
                moveRight();
                break;
            case "left":
                moveLeft();
                break;
        }

    }

    @Override
    public Icon getIcon() {
        return super.getIcon(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (getDistanceFrom(Player.ent) == 60.0 && Battle.isPlayerAttacking) {
            //use skill
            Battle.attackEnemy(me);
            GameBoard.disableHotbar();
            Battle.isPlayerAttacking = false;
        } else {

            setBorder(null);
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
        CardDeck.cardPrev.loadEnemy(me);
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
