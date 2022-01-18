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
public class Armour extends Item {

    private File arm = new File(Player.RsrcFolder + "\\Cards\\Armor");
    public File myFolder;
    private Random rnd = new Random();
    public Properties stats = new Properties();

    public Armour() {
        name = (arm.listFiles()[rnd.nextInt(arm.listFiles().length)].getName());
        System.out.println(name);
        myFolder = new File(arm + "\\" + name);
        setIcon(new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(myFolder + "\\Icon.png"))));
        setToolTipText(name);
        loadStats();

    }

    private void loadStats() {
        try {
            stats.load(new FileInputStream(new File(myFolder + "\\Stats.ini")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (stats.containsKey("@chestPlate")) {
            canChest = true;
        }
        if (stats.containsKey("@boots")) {
            canFeet = true;
        }
        if (stats.containsKey("@helm")) {
            canHelm = true;
        }
        if (stats.containsKey("@legs")) {
            canLegs = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!(e.getClickCount() >= 2 || e.isControlDown())) {
            CardGame07.GO.gameBoard.inv.cardDisplay.loadArmor(this);
        }
        super.mouseClicked(e);
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
