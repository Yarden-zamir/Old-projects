package cardgame;

import java.awt.Color;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JPanel;

/* 
 * @author Yarden zamir
 */
public class Edior extends JPanel {

    public Edior() {
        setVisible(false);

    }

    public void showFrame() {
        setSize(1000, 500);
        setLocation(10, 10);
//        setLayout(null);
        File F = new File(Player.RsrcFolder + "\\Tiles");
        setBackground(Color.red);
        System.out.println(F.listFiles()[0].getName());
        JButton Test1 = new JButton("test");
        Test1.setSize(200, 100);
        add(Test1);
        Test1.setLocation(25, 25);
        setVisible(true);
    }

}
