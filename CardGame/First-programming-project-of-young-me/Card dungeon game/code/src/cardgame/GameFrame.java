package cardgame;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/*
 * @author Yarden zamir
 */
public class GameFrame extends JFrame implements KeyListener{

    public MainMenuPane mainMenuPane;
    private ActionListener A;
    private JMenu debugM = new JMenu("Debug");

    public GameFrame(ActionListener a) {
        setSize(getToolkit().getScreenSize());
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(1300, 600));
        setMaximumSize(new Dimension(1366, 768));
        A = a;
        setIconImage(Systems.Graphic.ImgReader.ReadImage(new File(Player.RsrcFolder + "\\icons\\gameIcon.png")));
        setTitle("The runner");
        addKeyListener(this);
        
        
    }

    public void setUpTollBar() {
        JMenuBar tollBar = new JMenuBar();
        setJMenuBar(tollBar);
        JButton reset = new JButton("Reset");
        tollBar.add(reset);
        reset.setActionCommand("Reset");
        reset.addActionListener(A);
        tollBar.add(debugM);
        JButton console = new JButton("Debug console");
        debugM.add(console);
        console.setActionCommand("Console");
        console.addActionListener(A);
    }

    public void start() {
        //toll
        setUpTollBar();
        //setUp menu
        mainMenuPane = new MainMenuPane(A);
        add(mainMenuPane);
    }

    public void ShowMenu() {
        mainMenuPane.setVisible(true);
    }

    public void HideMenu() {
        mainMenuPane.setVisible(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

}
