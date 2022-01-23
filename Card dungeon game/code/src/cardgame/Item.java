package cardgame;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

/*
 * @author Yarden zamir
 */
public abstract class Item extends JLabel implements MouseMotionListener, MouseListener, ActionListener {

    boolean canRightHand;
    boolean canLeftHand;
    boolean canHelm;
    boolean canChest;
    boolean canLegs;
    boolean canFeet;

    boolean stackAble;
    Point mySlot;
    int id;

    String name;

    public Item() {
        setSize(50, 50);
        setOpaque(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        setLocation(-100, -100);
        pop = new JPopupMenu();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setLocation(getParent().getMousePosition().x - 25, getParent().getMousePosition().y - 25);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isControlDown()) {
            setVisible(false);
            if (id < 30) {
                Inventory.Slots[id].isEmpty = true;
            } else {
                unEquip(id);
                Inventory.eqwSlots[id - 30].isEmpty = true;
            }
        } else if (e.getClickCount() >= 2) {
            activate();
            if (id == 31 || id == 30 || id == 32 || id == 33) {
                for (int i = 0; i < 30; i++) {
                    if (Inventory.Slots[i].isEmpty) {
                        Inventory.Slots[i].isEmpty = false;
                        setLocation(Inventory.Slots[i].getLocation());
                        mySlot = Inventory.Slots[i].getLocation();
                        unEquip(id);
                        Inventory.eqwSlots[id - 30].isEmpty = true;
                        id = i;
                        i = 30;
                    }
                }
            } else {
                for (int i = 0; i < 6; i++) { //chang for armoe myb?
                    if (Inventory.eqwSlots[i].isEmpty) {

                        if (i == 0 && canRightHand || i == 1 && canLeftHand || i == 2 && canHelm || i == 3 && canChest || i == 4 && canLegs || i == 5 && canFeet) {

                            if (id < 30) {
                                Inventory.Slots[id].isEmpty = true;
                            } else {
                                Inventory.eqwSlots[id - 30].isEmpty = true;
                                //unequip
                                unEquip(id);
                                System.out.println("un");
                            }
                            id = i + 30;
                            mySlot = Inventory.eqwSlots[i].getLocation();
                            Inventory.eqwSlots[i].isEmpty = false;
                            Equip(i);
                        }
                    }
                }
                setLocation(mySlot);
            }
        }
    }
    JPopupMenu pop;

    public void activate() {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Inventory.drager(this, 1);
        if (e.isPopupTrigger()) {
            pop = new JPopupMenu(name);
            pop.setLabel(name);
            Db = new MenuButton("Delete", this);
            Db.addActionListener(this);
            pop.add(Db);
            add(pop);
            pop.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    MenuButton Db;

    @Override
    public void mouseReleased(MouseEvent e) {

        Inventory.drager(this, -1);
        if (e.isPopupTrigger()) {
            Db = new MenuButton("Delete", this);
            pop.add(Db);
            add(pop);
            pop.show(e.getComponent(), e.getX(), e.getY());
        }
        //check storageSlots
        for (int i = 0; i < 30; i++) {
            if (Inventory.Slots[i].IsInside(getLocation()) && Inventory.Slots[i].isEmpty) {
                if (id < 30) {
                    Inventory.Slots[id].isEmpty = true;

                } else {
                    Inventory.eqwSlots[id - 30].isEmpty = true;
                    unEquip(id);
                }
                id = i;
                mySlot = (Inventory.Slots[id].getLocation());
                Inventory.Slots[id].isEmpty = false;
                i = 30;
            }
        }
        //check eqwSlots
        for (int i = 0; i < 6; i++) {
            if (Inventory.eqwSlots[i].IsInside(getLocation()) && Inventory.eqwSlots[i].isEmpty) {
                System.out.println("in " + i);
                if (i == 0 && canRightHand || i == 1 && canLeftHand || i == 2 && canHelm || i == 3 && canChest || i == 4 && canLegs || i == 5 && canFeet) {
                    if (id < 30) {
                        Inventory.Slots[id].isEmpty = true;
                    } else {
                        Inventory.eqwSlots[id - 30].isEmpty = true;
                        //unequip
                        unEquip(id);
                    }
                    id = i + 30;
                    mySlot = Inventory.eqwSlots[i].getLocation();
                    Inventory.eqwSlots[i].isEmpty = false;
                    Equip(i);

                }
            }
        }
        setLocation(mySlot);
    }

    private Random rnd = new Random();

    public void Equip(int slot) {
        cardgame.Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\equip").listFiles()[rnd.nextInt(new File(Player.RsrcFolder + "\\Sound\\Effects\\equip").listFiles().length)]);
    }

    public void unEquip(int slot) {
        System.out.println("UnEquip");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        del();

    }

    public void del() {
        cardgame.Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\error.wav"));
        setVisible(false);
        pop.setVisible(false);
        if (id < 30) {
            Inventory.Slots[id].isEmpty = true;
        } else {
            unEquip(id);
            Inventory.eqwSlots[id - 30].isEmpty = true;
            CardGame07.GO.gameBoard.inv.refreshStats();
        }
    }

}
