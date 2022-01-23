package cardgame;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import javax.swing.ImageIcon;

/*
 * @author Yarden zamir
 */
public class Weapon extends Item {

    private File wepFolder = new File(Player.RsrcFolder + "\\Cards\\Weapon");
    public File myFolder;
    private Random rnd = new Random();
    public Properties stats = new Properties();

    public Weapon() {
        canRightHand = true;
        canLeftHand = true;
        stackAble = false;
        name = (wepFolder.listFiles()[rnd.nextInt(wepFolder.listFiles().length)].getName());
        myFolder = new File(wepFolder + "\\" + name);
        setIcon(new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(myFolder + "\\icon.png"))));
        setToolTipText(name);
        loadStats();

    }

    private void loadStats() {

        try {
            stats.load(new FileInputStream(new File(myFolder + "\\Stats.ini")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private long E = 1000;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!(e.getClickCount() >= 2 || e.isControlDown())) {
            CardGame07.GO.gameBoard.inv.cardDisplay.loadWeapon(this);
        }
        super.mouseClicked(e);
        //load wepon to card
    }

    @Override
    public void Equip(int slot) {
        super.Equip(slot); //To change body of generated methods, choose Tools | Templates.
        System.out.println(name + " equiped on slot : " + slot);
        Player.DMGb += Integer.parseInt(stats.getProperty("DMG"));
        Player.DEFb += Integer.parseInt(stats.getProperty("DEF"));
        Player.ACCb += Integer.parseInt(stats.getProperty("ACC"));
        CardGame07.GO.gameBoard.inv.refreshStats();

    }

    @Override
    public void unEquip(int slot) {
        super.unEquip(slot); //To change body of generated methods, choose Tools | Templates.
        Player.DMGb -= Integer.parseInt(stats.getProperty("DMG"));
        Player.DEFb -= Integer.parseInt(stats.getProperty("DEF"));
        Player.ACCb -= Integer.parseInt(stats.getProperty("ACC"));
        CardGame07.GO.gameBoard.inv.refreshStats();
    }

}
