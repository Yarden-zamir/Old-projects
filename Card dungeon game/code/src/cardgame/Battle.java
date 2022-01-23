package cardgame;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

/*
 * @author Yarden zamir
 */
public class Battle extends JLayeredPane implements ActionListener, KeyListener {

    public static Random rnd = new Random();
    public static ArrayList<Tile> gameTiles = new ArrayList<>();
    public static ArrayList<Point> takenLocs = new ArrayList<>();
    public static JLayeredPane tileSpace = new JLayeredPane();
    public static Card cardDisplay = new Card();
    public static ArrayList<String> turns = new ArrayList<>();
    public static turnListT turnList;
    public static JPanel actions = new JPanel();
    public actionOptionButton choice1 = new actionOptionButton();
    public actionOptionButton choice2 = new actionOptionButton();
    public actionOptionButton choice3 = new actionOptionButton();
    public actionOptionButton choice4 = new actionOptionButton();
    public actionOptionButton choice5 = new actionOptionButton();
    public actionOptionButton choice6 = new actionOptionButton();
    public JButton attack = new JButton("Attack");
    public static JButton move = new JButton("Move");
    public JButton wait = new JButton("Wait");
    public static JLabel actionsCunt = new JLabel();
    public static int numOfActions = 0;
    public static int currentTurn = 0;
    public static ArrayList<Enemy> enemys = new ArrayList<>();
    public static Skill selectedSkill;
    public static boolean isPlayerAttacking = false;

    public Battle() {
        Player.genIcon();
        tileSpace.setName("pane");
        setLayer(tileSpace, 0);
        turnList = new turnListT();
        //y=472
        //x=975
        setSize(975, 472);
        setLocation(25, 80);
        setLayout(null);
        add(tileSpace);
        add(cardDisplay);
        add(turnList);
        cardDisplay.setLocation(25, 25);
        add(choice1);
        add(choice2);
        add(choice3);
        add(choice4);
        add(choice5);
        add(choice6);
        setLayer(choice1, 1);
        setLayer(choice2, 1);
        setLayer(choice3, 1);
        setLayer(choice4, 1);
        setLayer(choice5, 1);
        setLayer(choice6, 1);
        move.addKeyListener(this);
    }

    public static void selectSkill(Skill s) {
        selectedSkill = s;
        if (s.stats.getProperty("type").equals("@dmgAttack")) {
            for (Enemy enemy : enemys) {
                if (enemy.ent.getDistanceFrom(Player.ent) == 60.0) {
                    enemy.ent.setBorder(new BevelBorder(BevelBorder.RAISED));
                } else {
                    enemy.ent.setBorder(null);
                }
            }
        }
    }

    public static void attackEnemy(Enemy e) {
        if (selectedSkill.stats.getProperty("type").equals("@dmgAttack")) {
            int playerRoll = 0;
            Player.addStamina(-Integer.parseInt(selectedSkill.stats.getProperty("cost")));
            if (selectedSkill.stats.getProperty("acc").contains("@normalAttack")) {
                playerRoll = rnd.nextInt(25);
                playerRoll += Player.ACC - 10;
            }
            if (selectedSkill.stats.getProperty("acc").contains("@hit")) {
                playerRoll = Integer.MAX_VALUE;
            }
            numOfActions--;
            actionsCunt.setText(numOfActions + "");
            if (e.checkForHit(playerRoll)) {
                //hit
                int dmg = 1;
                int mod = selectedSkill.stats.getProperty("dmg").split(",").length;
                for (int i = 0; i < mod; i++) {
                    if (selectedSkill.stats.getProperty("dmg").split(",")[i].equals("@normalAttack")) {
                        dmg += rnd.nextInt(Player.DMG);
                    }
                }
                e.damageMe(dmg);
            } else {
                e.damageMe(0);
            }
            if (numOfActions <= 0) {
                nextTurn();
            }
            for (Enemy enemy : enemys) {
                enemy.ent.setBorder(null);
            }
        }
        if (selectedSkill.stats.getProperty("type").equals("@dmgAll")){
            
        }
    }

    public void setUpStart() {
        setUpTileSpace();
        setUpTurnList();
        setUpActions();
        gameTiles.add(new Tile());
        tileSpace.add(gameTiles.get(gameTiles.size() - 1));
        gameTiles.get(gameTiles.size() - 1).genMe(1);
        tileSpace.add(Player.ent);
        Player.ent.setLocation(156, 156);
        tileSpace.setLayer(Player.ent, 100);
        takenLocs.add(Player.ent.getLocation());
    }

    private void setUpTileSpace() {
        tileSpace.setSize(422, 422);
        tileSpace.setBorder(new BevelBorder(BevelBorder.RAISED));
        tileSpace.setLocation(418, 25);
    }

    private void setUpTurnList() {
        Player.genIcon();
        turnList.setSize(100, 422);
        turnList.setLocation(850, 25);
        turnList.add(Player.ent.entTurn);

    }

    private void setUpActions() {
        add(actions);
        playerTurn();
        actionsCunt.setBorder(new TitledBorder("actions"));
        actionsCunt.setSize(100, 30);
        actionsCunt.setPreferredSize(actionsCunt.getSize());
        actions.setSize(385, 50);
        actions.setLocation(25, 395);
        actions.setBorder(new TitledBorder("Actions"));
        actions.add(actionsCunt);
        actions.add(attack);
        attack.setToolTipText("attack the enemy with a skill or a simple blow");
        attack.setActionCommand("attack");
        attack.addActionListener(this);
        actions.add(move);
        move.setActionCommand("move");
        move.addActionListener(this);
        move.setToolTipText("move");
        actions.add(wait);
        wait.setToolTipText("rest and recover some stamina");
        wait.addActionListener(this);
        wait.setActionCommand("wait");
    }

    public static void playerTurn() {
        Player.ent.entTurn.myTurn();
        numOfActions = Player.maxActions;
        actionsCunt.setText(numOfActions + "");
        actions.setEnabled(true);
        for (int i = 0; i < actions.getComponentCount(); i++) {
            actions.getComponent(i).setEnabled(true);
        }
        move.requestFocus();
    }

    public static void nextTurn() {
        Player.ent.hideMovementAction();
        GameBoard.disableHotbar();
        turnList.getComponent(currentTurn).setBorder(new BevelBorder(BevelBorder.LOWERED));
        currentTurn++;
        if (currentTurn >= turnList.getComponentCount()) {
            currentTurn = 0;
            playerTurn();
        } else {
            Player.ent.hideMovementAction();
            turnList.getComponent(currentTurn).myTurn();
        }
    }

    public static void useAction(int ac) {
        if (numOfActions - ac >= 1) {
            numOfActions -= ac;
            actionsCunt.setText(numOfActions + "");
        } else {
            numOfActions -= ac;
            actionsCunt.setText(numOfActions + "");
            actions.setEnabled(false);
            for (int i = 0; i < actions.getComponentCount(); i++) {
                actions.getComponent(i).setEnabled(false);
            }
            nextTurn();
        }

    }

    public static void genEncunter() {
        if (!Battle.gameTiles.get(Battle.gameTiles.size() - 1).isBad) {
            GameOperator.drawEnemyCard();
            if (rnd.nextBoolean()) {
                GameOperator.drawEnemyCard();
                if (rnd.nextBoolean() && rnd.nextBoolean()) {
                    GameOperator.drawEnemyCard();
                }
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "move":
                Player.ent.showMovmentAction();
                for (Enemy enemy : enemys) {
                    enemy.ent.setBorder(null);
                }

                break;
            case "attack":
                GameBoard.enableHotbat();
                Player.ent.hideMovementAction();
                isPlayerAttacking = true;
                for (Enemy enemy : enemys) {
                    enemy.ent.setBorder(null);
                }
                break;
            case "wait":
                useAction(1);
                Player.addStamina(5 + rnd.nextInt(11));
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        Battle.takenLocs.remove(Player.ent.getLocation());
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\clicks\\click" + (rnd.nextInt(3) + 1) + ".ogg"));
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (Player.ent.up.isVisible()) {
                Player.ent.moveUp();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (Player.ent.down.isVisible()) {
                Player.ent.moveDown();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (Player.ent.right.isVisible()) {
                Player.ent.moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (Player.ent.left.isVisible()) {
                Player.ent.moveLeft();
            }
        }

        Player.ent.hideMovementAction();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

class actionOptionButton extends JButton implements ActionListener, MouseListener {

    private static int Y = 25;
    private int T = 0;

    public actionOptionButton() {
        setEnabled(false);
        setToolTipText(getText());
        setSize(155, 50);
        setLocation(255, Y);
        Y += 62;
        setActionCommand(T + "");
        addActionListener(this);
        setHorizontalTextPosition(SwingConstants.LEFT);
        setFont(getFont().deriveFont(getFont().getStyle(), getFont().getSize() - 1));
        addMouseListener(this);
    }

    public void setButton(String name) {
        setText(name);
        setToolTipText(getText());
        setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isEnabled()) {
            setSize(255, 52);
            Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\slotSound.wav"));

        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setSize(155, 50);
    }

}

class turnListT extends JPanel {

    public turnListT() {
        super();
    }

    @Override
    public Turn getComponent(int n) {
        return (Turn) super.getComponent(n);
    }

}
