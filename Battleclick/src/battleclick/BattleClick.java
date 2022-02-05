/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Animation | Templates
 * and open the template in the editor.
 */
package battleclick;

import battleclick.cards.Hero;
import battleclick.cards.Item;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import net.panda.ActionConsole.tools.Animation;
import org.jdesktop.swingx.JXFrame;

/**
 *
 * @author hrsid
 */
public class BattleClick {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        settings.setUp();
        gamePane f = new gamePane();

    }

}

class gamePane extends JXFrame {

    public int itemCardsToDraw = settings.starterItemCards;
    public int heroCardsToDraw = settings.starterHeroCards;
    public ArrayList<Hero> heroCards = new ArrayList<>();
    public ArrayList<Item> itemCards = new ArrayList<>();
    public int width = 1280;

    public int hight = 820;

    private boolean isHeroDeckShown = false;
    private boolean isItemDeckShown = false;
    heroDeck hd;
    itemDeck id;
    private JLayeredPane layeredPane = new JLayeredPane();
    private actionButton heroDrawButton = new actionButton(300, "cardDraw", "Draw " + settings.starterHeroCards + " Hero cards");
    private actionButton itemDrawButton = new actionButton(300, "cardDraw", "Draw " + settings.starterItemCards + " Item cards");

    public gamePane() {
        init();
        gameStart();
    }

    private void init() {
        setLayeredPane(layeredPane);
        layeredPane.setLayout(null);
        layeredPane.setOpaque(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(this.width, this.hight);
        setVisible(true);
        getContentPane().setLayout(null);

        //
        getContentPane().setBackground(MaterialColors.BLUE_GREY);
        layeredPane.setBackground(MaterialColors.BLUE_GREY);

    }

    private void orgItemCards() {
        itemCards.trimToSize();
        itemCards.forEach((itm) -> {
            itm.moveToPlaceH(itemCards.indexOf(itm) + 1);
        });
        prevCard.equiped.forEach((itm) -> {
            itm.moveAnim(new Point(300 + 15 + (prevCard.equiped.indexOf(itm)) * (settings.vCardWidth - 35), prevCard.itemBar.getY() + 10), 300);

        });
    }

    private void gameStart() {
        {
            layeredPane.add(heroDrawButton);
            heroDrawButton.setLocation(this.width / 2 - heroDrawButton.getWidth() / 2, this.hight / 2 - heroDrawButton.getHeight() / 2);
            action bt1 = (MouseEvent e) -> {
                showHeroDeck();
                drawHeroCard();
            };
            heroDrawButton.addAction(bt1);
        }
        {
            layeredPane.add(itemDrawButton);
            itemDrawButton.setLocation(this.width, -itemDrawButton.getHeight());
            action bt2 = (MouseEvent e) -> {
                drawItemCard();
            };
            itemDrawButton.addAction(bt2);
        }
        //
        id = new itemDeck(this.width - settings.hCardWidth - 40, layeredPane.getBackground());
        layeredPane.add(id);
        id.setLocation(settings.hCardWidth + 20, this.hight);
    }

    private void showHeroDeck() {
        if (!isHeroDeckShown) {
            isHeroDeckShown = true;
            Animation t1 = new Animation();
            Animation t2 = new Animation();
            t1.move(heroDrawButton, heroDrawButton.getLocation(), new Point(this.width - heroDrawButton.getWidth(), this.hight - heroDrawButton.getHeight() - 50), 300);

            hd = new heroDeck(this.hight - 40, layeredPane.getBackground());
            layeredPane.add(hd);
            layeredPane.setPosition(hd, -1);
            hd.setLocation(-hd.getWidth(), 0);
            t2.move(hd, hd.getLocation(), new Point(0, 0), 400);
        }
    }

    private void showHeroDrawButton() {
        if (heroCardsToDraw > 0) {
            Animation a = new Animation();
            a.move(heroDrawButton, heroDrawButton.getLocation(), new Point(this.width - heroDrawButton.getWidth(), this.hight - heroDrawButton.getHeight() - 50), 300);
        }
    }

    private void hideHeroDrawButton() {
        Animation a = new Animation();
        a.move(heroDrawButton, heroDrawButton.getLocation(), new Point(getWidth(), getHeight()), 300);
    }
    private int heroCardNum = 0;
    private int itemCardNum = 0;

    private void showItemBar() {
        Animation t1 = new Animation();
        t1.move(id, id.getLocation(), new Point(settings.hCardWidth + 20, this.hight - id.getHeight() - 40), 300);
        for (Item itm : itemCards) {
            itm.moveAnim(new Point(itm.getX(), itm.getY() - itm.getHeight()), 300);
        }
    }

    private void hideItemBar() {
        Animation t1 = new Animation();
        t1.move(id, id.getLocation(), new Point(settings.hCardWidth + 20, this.hight), 300);
        for (Item itm : itemCards) {
            itm.moveAnim(new Point(itm.getX(), itm.getY() + itm.getHeight()), 300);
        }
    }

    private void showDrawItemButton() {
        Animation a1 = new Animation();
        a1.move(itemDrawButton, itemDrawButton.getLocation(), new Point(this.width - itemDrawButton.getWidth(), -10), 300);
    }

    private void hideItemDrawButton() {
        Animation a1 = new Animation();
        a1.move(itemDrawButton, itemDrawButton.getLocation(), new Point(this.width, -itemDrawButton.getHeight()), 300);
    }

    private void drawItemCard() {
        if (!(itemCardsToDraw > 0)) {
            hideItemDrawButton();
            return;
        }
        itemCardsToDraw--;
        itemDrawButton.setText("Draw " + itemCardsToDraw + " Item cards");
        Item c = new Item(itemCardNum);
        c.addAction(((e) -> {
            if (prevCard == null) {
                return;
            } else if (prevCard.equiped.contains(c)) { //remove item from selected hero
                c.becomeCard();
                itemCards.add(c);
                c.moveToPlaceH(itemCards.size());
                prevCard.removeItem(c);
            } else {  //add the item to the selected hero
                c.becomeThumbnail();
                prevCard.addItem(c);
                itemCards.remove(c);
            }
            orgItemCards();
        }));

        layeredPane.add(c);
        c.setLocation(new Point(itemDrawButton.getX() - 10, itemDrawButton.getY() + 30));
        layeredPane.setPosition(c, 0);

        itemCards.add(c);
        c.moveToPlaceH(itemCards.size());
    }

    private void drawHeroCard() {
        if (!(heroCardsToDraw > 0)) {
            hideHeroDrawButton();
            return;
        }
        heroCardsToDraw--;
        heroDrawButton.setText("Draw " + heroCardsToDraw + " Hero cards");
        Hero c = new Hero(heroCardNum);
        c.addAction((e) -> {
            if (c.equals(prevCard)) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    hideItemBar();
                    hideItemDrawButton();
                    showHeroDrawButton();
                    hideEquipedItems();
                    prevCard.isPrev = false;
                    prevCard = null;
                    heroCards.add(c);
                    c.moveToPlaceVert(heroCards.indexOf(c));
                    c.aResize(c.normalSize);
                    c.setBackground(settings.HomeColor.darker());
                }
            } else {
                prevHeroCard(c);
                showEquipedItems();
            }
        });
        layeredPane.add(c);
        layeredPane.setPosition(c, 0);
        c.setLocation(new Point(heroDrawButton.getX() - 90, heroDrawButton.getY() + 30));
        c.moveAnim(new Point(10, 10 + heroCards.size() * (c.getHeight() + 5)), 400);
        heroCards.add(c);
        heroCardNum++;
        if (heroCardsToDraw <= 0) {
            hideHeroDrawButton();
        }
    }

    private Hero prevCard;

    private void showEquipedItems() {
        prevCard.equiped.forEach((itm) -> {
//            layeredPane.add(itm);
            layeredPane.setPosition(itm, 0);
            itm.setLocation(prevCard.getX(), prevCard.getY());
            itm.becomeThumbnail();

        });
        orgItemCards();
    }

    private void hideEquipedItems() {
        prevCard.equiped.forEach((itm) -> {
            Random rnd = new Random();
            int var = 75;
            itm.moveAnim(new Point(itm.getX() - var + rnd.nextInt(var * 2), itm.getY() - var + rnd.nextInt(var * 2)), 400);
            itm.beGone();
        });
    }

    private void prevHeroCard(Hero tc) {
        showItemBar();
        showDrawItemButton();
        hideHeroDrawButton();
        if (prevCard == null) { //prev when no prev is there
            prevCard = tc;
            prevCard.isPrev = true;
            heroCards.remove(tc);
            prevCard.moveAnim(new Point(hd.getWidth(), 0), 300);
            prevCard.aResize(new Dimension(prevCard.normalSize.width * 2, prevCard.normalSize.height * 2));
            prevCard.setBackground(settings.HomeColor);

        } else {                       //prev when there is another prev

        }
        heroCards.forEach((c) -> {
            c.moveToPlaceVert(heroCards.indexOf(c));
        });

    }

}
