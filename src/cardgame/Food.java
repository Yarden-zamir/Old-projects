package cardgame;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;

/* 
 * @author Yarden zamir
 */
public class Food extends consumable {

    private File foodFolder = new File(Player.RsrcFolder + "\\Cards\\Food");
    public File myFolder;
    private Random rnd = new Random();
    public Properties stats = new Properties();

    public Food() {
        name = (foodFolder.listFiles()[rnd.nextInt(foodFolder.listFiles().length)].getName());
        myFolder = new File(foodFolder + "\\" + name);
        setIcon(new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(myFolder + "\\icon.png"))));
        setToolTipText(name);
        loadStats();
        Scanner in = new Scanner(System.in);
    }

    private void loadStats() {

        try {
            stats.load(new FileInputStream(new File(myFolder + "\\Stats.ini")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activate() {
        super.activate(); //To change body of generated methods, choose Tools | Templates.
        Player.addStamina(Integer.parseInt(stats.getProperty("STM")));
        Player.addHP(Integer.parseInt(stats.getProperty("HP")));
        del();

    }

}
