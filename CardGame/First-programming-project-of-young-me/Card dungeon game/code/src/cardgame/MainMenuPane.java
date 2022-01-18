package cardgame;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * @author Yarden zamir
 */
public class MainMenuPane extends JPanel {

    MenuButton play, options, about, editor, help;

    public MainMenuPane(ActionListener a) {
        setBackground(Color.DARK_GRAY);
        setLayout(null);
        play = new MenuButton("Play", a);
        options = new MenuButton("Options", a);
        about = new MenuButton("About", a);
        editor = new MenuButton("Editor", a);
        help = new MenuButton("Help", a);
        add(play);
        add(options);
        add(about);
        add(editor);
        add(help);
    }
}

class MenuButton extends JButton implements MouseListener {

    private Random rnd = new Random();
    public static int y = 25;

    public MenuButton(String name, ActionListener a) {
        setSize(400, 100);
        setText(" " + name);
        setLocation(25, y);
        y += 115;
        setFont(getFont().deriveFont(0, 20));
        setActionCommand(name);
        addActionListener(a);
        setHorizontalAlignment(SwingConstants.LEFT);
        setIcon(new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(Player.RsrcFolder + "\\icons\\Menu\\" + name + ".png"))));
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\clicks\\click" + (rnd.nextInt(3) + 1) + ".ogg"));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Systems.SoundSystem.SoundPlayer.playSound(new File(Player.RsrcFolder + "\\Sound\\Effects\\slotSound.wav"));
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
