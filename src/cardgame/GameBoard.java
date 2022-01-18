package cardgame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/*
 * @author Yarden zamir
 */
public class GameBoard extends JLayeredPane implements ActionListener,KeyListener{

    static Inventory inv;
    public static Journal journal = new Journal();
    public static CardDeck cardDeck = new CardDeck();
    public static StatsBar statsBar = new StatsBar();
    public static JLayeredPane hotbar = new JLayeredPane();
    public static JButton attackButton = new JButton("Attack");
    public static boolean isHotbarEnabled = false;

    public GameBoard() {
        setLayout(null);
        setOpaque(true);
        addKeyListener(this);
    }

    public void setGame() {
        //reset or sets the game
        setBackground(Color.DARK_GRAY);
        Player.CON = 10;
        Player.DEX = 10;
        Player.INT = 10;
        Player.LCK = 10;
        Player.STR = 10;
        Player.WIS = 10;
        Player.LVL = 1;
        setSize(getParent().getSize());
        setUpUi();
    }

    public void setUpUi() {
        //inv
        setUpInv();
        //decks
        setUpDecks();
        //stats
        setUpStats();
        //jornal
        setUpJornal();
        //hotbar
        setUpHotBar();
    }

    private void setUpInv() {
        inv = new Inventory();
        add(inv);
        inv.Up();
        setLayer(inv, 1000);
    }
    public static skillSlot[] skillSlots = new skillSlot[9];

    public static void disableHotbar() {
        isHotbarEnabled = false;
        for (int i = 0; i < hotbar.getComponentCount(); i++) {
            hotbar.getComponent(i).setEnabled(false);
        }
    }

    public static void enableHotbat() {
        isHotbarEnabled = true;
        for (int i = 0; i < hotbar.getComponentCount(); i++) {
            hotbar.getComponent(i).setEnabled(true);
        }
    }

    private void setUpHotBar() {
        add(hotbar);
        hotbar.setSize(975, 125);
        hotbar.setLocation(25, 550);
        hotbar.setBorder(new BevelBorder(BevelBorder.RAISED));
        hotbar.setBackground(Color.lightGray);
        hotbar.setOpaque(true);
        hotbar.add(attackButton);
        attackButton.setSize(75, 75);
        attackButton.setLocation(25, 25);
        attackButton.setActionCommand("Attack");
        attackButton.addActionListener(this);
        for (int i = 0; i < 9; i++) {
            skillSlots[i] = new skillSlot((i * 95), 25);
            hotbar.add(skillSlots[i]);
        }
        disableHotbar();
    }

    private void setUpDecks() {
        add(cardDeck);
    }

    private void setUpStats() {
        add(statsBar);
        statsBar.UpDateStatsBar();
    }

    private void setUpJornal() {
        add(journal);
    }

    public void Show() {
        setVisible(true);
    }

    public void Hide() {
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Skill s=new starterSkill();
        Battle.cardDisplay.loadSkill(s);
        Battle.selectSkill(s);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.err.println(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

class skillSlot extends JPanel implements MouseListener {

    boolean isEmpty = true;

    public skillSlot(int x, int y) {
        setSize(75, 75);
        setBorder(new BevelBorder(BevelBorder.RAISED));
        setLocation(x + 115, y);
        setBackground(Color.LIGHT_GRAY.darker());
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //start skill
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\slotSound.wav"));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorder(new BevelBorder(BevelBorder.RAISED));
    }

}

class starterSkill extends Skill {

    public starterSkill() {
        setOpaque(true);
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setSize(75, 75);
        addMouseListener(this);
        meFolder = new File(Player.RsrcFolder + "\\Normal attack");
        setIcon(new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(meFolder + "\\Icon.png"))));
        setToolTipText(meFolder.getName());
        try {
            stats.load(new FileInputStream(new File(meFolder + "\\Stats.ini")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
