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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * @author Yarden zamir
 */
public class GameOperator implements ActionListener {

    private GameFrame gameFrame = new GameFrame(this);
    public static GameBoard gameBoard = new GameBoard();
    public static Adventure adventure = new Adventure();
    public static Battle B = new Battle();
    public static Edior E = new Edior();

    public GameOperator() {
        gameFrame.add(gameBoard);
        gameFrame.start();
    }

    public void startGame() {
        gameBoard.Hide();
        gameFrame.ShowMenu();
        MenuButton.y = 25;
        gameBoard.add(adventure);
        //addBattleBoard
        gameBoard.add(B);
        gameBoard.add(E);
        Systems.SoundSystem.SoundPlayer.loopSound(new File(Player.RsrcFolder + "\\Sound\\Music\\A_Journey_Awaits.wav"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Play":
                play("Panda");
                break;
            case "Editor":
                openEditor();
                break;
            case "Reset": {
                System.gc();
                System.out.println("not yet implemented");
            }
            break;
            case "Adventure":
                drawAdventureCard();
                break;
            case "Enemy":
                drawEnemyCard();
                break;
            case "Loot":
                drawLootCard(Player.ent.getLocation());
                break;
        }
    }

    public void play(String name) {
        Player.name = name;
        gameFrame.setTitle("The runner - " + name);
        Player.HP = 100;
        Player.XP = 0;
        Player.stamina = 100;
        gameFrame.HideMenu();
        gameBoard.Show();
        gameBoard.setGame();
        CardDeck.adDrawer = 1;
        B.setUpStart();

    }

    public void openEditor() {
        gameFrame.HideMenu();
        E.showFrame();
    }

    public static void drawAdventureCard() {
        CardDeck.adDrawer--;
        if (CardDeck.adDrawer <= 0) {

        }
        adventure.draw();
    }

    public static void drawEnemyCard() {
        //
        CardDeck.enDrawer--;
        Enemy e = new Enemy();
        CardDeck.cardPrev.loadEnemy(e);
        if (CardDeck.enDrawer <= 0) {
//            CardDeck.EneB.setEnabled(false);
        }
    }

    static Random rnd = new Random();

    private static String genLootType() {
        String outP = "nothing";
        int loot = (rnd.nextInt(100) + 1);
        if (loot <= 10) {
            if (Player.LCK >= 10 + rnd.nextInt(20)) {
                outP = genLootType();
            } else {
                //nothing
            }
        } else if (loot <= 20) {
            if (Player.LCK >= 10 + rnd.nextInt(15)) {
                outP = genLootType();
            } else {
                //nothing
            }
        } else if (loot <= 30) {
            if (Player.LCK >= 10 + rnd.nextInt(10)) {
                outP = genLootType();
            } else {
                //nothing
            }
        } else if (loot <= 40) {
            if (Player.LCK >= rnd.nextInt(15)) {
                //consumable
                outP = "consumable";
            } else {
                //nothing
            }
        } else if (loot <= 50) {
            if (Player.LCK >= rnd.nextInt(5)) {
                //consumable
                outP = "consumable";
            } else {
                //nothing
                outP = genLootType();
            }
        } else if (loot <= 60) {
            outP = "weapon";
        } else if (loot <= 70) {
            outP = "weapon";
        } else if (loot <= 80) {
            outP = "armour";
        } else if (loot <= 90) {
            //money
            outP = "money";
        } else if (loot <= 100) {
            //money
            outP = "money";
        }
        return outP;
    }

    public static void drawLootCard(Point loc) {
        CardDeck.loDrawer--;
        if (CardDeck.loDrawer <= 0) {
        }
        String lootType = genLootType();
        System.out.println(lootType);
        if (lootType.equals("weapon")) {
            Inventory.addItem(new Weapon());
        } else if (lootType.equals("armour")) {
            Inventory.addItem(new Armour());
        } else if (lootType.equals("consumable")) {
            if (rnd.nextBoolean()) {
                Inventory.addItem(new Food());
            } else if (rnd.nextBoolean()) {
                Inventory.addItem(new Potion());
            } else {
                Inventory.addItem(new Scroll());
            }
        }
    }

}

class Adventure extends JPanel {

    public Random rnd = new Random();
    public File me;
    public Properties stats = new Properties();

    public Adventure() {
        setLayout(null);
        setSize(975, 472);
        setLocation(25, 80);
        setVisible(false);
        setBackground(Color.darkGray);
        removeAll();
    }

    public void draw() {
        me = new File(Player.RsrcFolder + "\\Cards\\Adventure");
        me = me.listFiles()[rnd.nextInt(me.listFiles().length)];
        setVisible(true);
        try {
            stats.load(new FileInputStream(new File(me + "\\stats.ini")));
        } catch (IOException ex) {
            Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
        }
        Card c = new Card();
        add(c);
        c.setLocation(377, 26);
        c.loadAdventure(this);
        File choiceFolder = new File(me + "\\Choices");
        for (int i = 0; i < 6; i++) {
            if (choiceFolder.listFiles().length > i) {
                add(new choicButton(choiceFolder.listFiles()[i]));
            } else {
                add(new choicButton());
            }
        }
    }

}

class choicButton extends JButton implements ActionListener {

    static int y = 26;
    static int x = 147;

    public choicButton() {
        constract("");
    }

    private void constract(String name) {
        setSize(220, 110);
        setText(name.replace(".txt", ""));
        setLocation(x, y);
        y += 125;
        if (y >= 277) {
            y = 26;
            x = 605;
        }
        if (name.isEmpty()) {
            setEnabled(false);
            setText("...");
        }
    }
    File s;

    public choicButton(File sorce) {
        constract(sorce.getName());
        s = sorce;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Scanner in = null;
        try {
            in = new Scanner(s);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(choicButton.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tag = in.nextLine();
        switch (tag) {
            case "@battle":
                Journal.append(in.nextLine().replace("textL:", "\n") + "\n");
                int enemys = Integer.parseInt(in.nextLine().replace("numOfEnem:", ""));
                Journal.append("draw " + enemys + " Enemy cards");
                GameOperator.adventure.setVisible(false);
                CardDeck.enDrawer = enemys;
                break;
        }
    }
}
