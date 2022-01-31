/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babeltwoergame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 *
 * @author Yarden zamir
 */
public class BabelTwoerGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        game g = new game("Babel Tower", new File("C:\\Projects -  Clusters\\Get a job\\old stuff\\babelTwoerGame\\src\\test.txt"), 1, 1024, 768);
        g.readQuestion();

    }

}

class game extends JFrame {

    private File lvlRep = new File("C:\\Projects -  Clusters\\Get a job\\old stuff\\babelTwoerGame\\src\\tower");
    private File soundRep = new File("C:\\Projects -  Clusters\\Get a job\\old stuff\\babelTwoerGame\\src\\winSound");
    private int difficulty;
    private File qestionsLoc;
    private Scanner answerReader = new Scanner(System.in);
    private BufferedReader br;
    private String gameName;
    private Random rnd = new Random();
    private JPanel mainPane = new JPanel(null);
    private JPanel towerPane;
    private JPanel questionPane;
    private JTextField questionField = new JTextField();
    private JButton nextButton = new BasicArrowButton(SwingConstants.EAST);
    private JButton a = new JButton(), b = new JButton(), c = new JButton(), d = new JButton();
    private JLabel tower = new JLabel();
    private int lvl = 1;

    public game(String gameName, File qestionsLoc, int difficulty, int width, int hight) {

        towerPane = new JPanel();
        questionPane = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width - 10, hight);
        setResizable(false);
        setVisible(true);
        setTitle(gameName);
        this.add(mainPane);
        mainPane.add(towerPane);
        mainPane.add(questionPane);
        towerPane.setSize((width / 2), hight - 40);
        questionPane.setSize(width / 2, hight - 40);
        tower = new JLabel(new ImageIcon(lvlRep + "\\1.jpg"));
        towerPane.add(tower);
        tower.setSize(towerPane.getSize());
        tower.setLocation(0, 0);
        questionPane.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (hasQuestion()) {
                    readQuestion();
                    a.setEnabled(true);
                    b.setEnabled(true);
                    c.setEnabled(true);
                    d.setEnabled(true);
                    a.setBackground(new JButton().getBackground());
                    b.setBackground(new JButton().getBackground());
                    c.setBackground(new JButton().getBackground());
                    d.setBackground(new JButton().getBackground());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        nextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                questionPane.setVisible(true);
            }
        });
        towerPane.setLocation(width / 2, 0);
        questionPane.setLocation(0, 0);
        questionPane.setBorder(new BevelBorder(BevelBorder.RAISED));
        towerPane.setBorder(new BevelBorder(BevelBorder.RAISED));
        questionPane.add(questionField);
        questionField.setLocation(25, 15);
        questionField.setSize(questionField.getParent().getSize().width - 50, 50);
        questionField.setBorder(new BevelBorder(BevelBorder.RAISED));
        questionField.setHorizontalAlignment(SwingConstants.CENTER);
        questionField.setFont(questionField.getFont().deriveFont(questionField.getFont().getStyle(), questionField.getFont().getSize() + 10));
        questionField.setEditable(false);
        questionPane.setLayout(null);
        a.setSize(questionPane.getWidth() - 50, questionPane.getHeight() / 5);
        b.setSize(questionPane.getWidth() - 50, questionPane.getHeight() / 5);
        c.setSize(questionPane.getWidth() - 50, questionPane.getHeight() / 5);
        d.setSize(questionPane.getWidth() - 50, questionPane.getHeight() / 5);
        questionPane.add(a);
        questionPane.add(b);
        questionPane.add(c);
        questionPane.add(d);
        a.setFont(questionField.getFont().deriveFont(questionField.getFont().getStyle(), questionField.getFont().getSize() + 1));
        b.setFont(questionField.getFont().deriveFont(questionField.getFont().getStyle(), questionField.getFont().getSize() + 1));
        c.setFont(questionField.getFont().deriveFont(questionField.getFont().getStyle(), questionField.getFont().getSize() + 1));
        d.setFont(questionField.getFont().deriveFont(questionField.getFont().getStyle(), questionField.getFont().getSize() + 1));
        a.setLocation(25, 80);
        b.setLocation(25, 80 + questionPane.getHeight() / 5 + 15);
        c.setLocation(25, 80 + (questionPane.getHeight() / 5 + 15) * 2);
        d.setLocation(25, 80 + (questionPane.getHeight() / 5 + 15) * 3);
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(a.getText());
            }
        });
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(b.getText());
            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(c.getText());
            }
        });
        d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(d.getText());
            }
        });
        this.gameName = gameName;
        this.qestionsLoc = qestionsLoc;
        this.difficulty = difficulty;
        try {
            br = new BufferedReader(new FileReader(qestionsLoc));
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void checkAnswer(String answer) {
        a.setEnabled(false);
        b.setEnabled(false);
        c.setEnabled(false);
        d.setEnabled(false);
        try {
//            System.out.println(br.readLine());
            String P = br.readLine();
            if (P.contains(answer.substring(1))) {
                System.out.println(answer.substring(1) + "Right!");
                lvl++;
                if (a.getText().contains(answer.substring(1))) {
                    a.setBackground(Color.decode("#8fff57"));
                }
                if (b.getText().contains(answer.substring(1))) {
                    b.setBackground(Color.decode("#8fff57"));
                }
                if (c.getText().contains(answer.substring(1))) {
                    c.setBackground(Color.decode("#8fff57"));
                }
                if (d.getText().contains(answer.substring(1))) {
                    d.setBackground(Color.decode("#8fff57"));
                }
				SoundPlayer.playSound(soundRep.listFiles()[rnd.nextInt(soundRep.listFiles().length)]);

            } else {
                lvl--;
                if (a.getText().contains(answer.substring(1))) {
                   a.setBackground(Color.decode("#ff5c5c"));
                }
                if (b.getText().contains(answer.substring(1))) {
                    b.setBackground(Color.decode("#ff5c5c"));
                }
                if (c.getText().contains(answer.substring(1))) {
                    c.setBackground(Color.decode("#ff5c5c"));
                }
                if (d.getText().contains(answer.substring(1))) {
                    d.setBackground(Color.decode("#ff5c5c"));
                }
                System.out.println(answer.substring(1) + "Wrong!");
                //
                if (P.contains(a.getText().substring(1))) {
                    a.setBackground(Color.decode("#8fff57"));
                }
                if (P.contains(b.getText().substring(1))) {
                    b.setBackground(Color.decode("#8fff57"));
                }
                if (P.contains(c.getText().substring(1))) {
                    c.setBackground(Color.decode("#8fff57"));
                }
                if (P.contains(d.getText().substring(1))) {
                    d.setBackground(Color.decode("#8fff57"));
                }
            }
            if (lvl > 10)
                lvl = 10;
            if (lvl < 1)
                lvl = 1;
            tower.setIcon(new ImageIcon(lvlRep + "\\" + lvl + ".jpg"));

        } catch (IOException ex) {
            Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
        }

//        questionPane.setVisible(false);
//        resultPane.setVisible(true);
//        if (hasQuestion())
//            readQuestion();
    }

    public boolean hasQuestion() {
        try {
            return (!br.readLine().contains("end"));
        } catch (Exception e) {
        }
        return false;
    }

    public void readQuestion() {
        try {
            String qestion = br.readLine();
            for (int i = 0; i < difficulty; i++) {
                int cut = rnd.nextInt(qestion.length() - 2);
                cut++;
                String a = qestion.substring(0, cut);
                if (qestion.substring(cut, cut + 1).equals(" ")
                        || qestion.substring(cut, cut + 1).equals("-")
                        || qestion.substring(cut, cut + 1).equals("?")) {
                    i--;
                } else {
                    int A = qestion.length();
                    qestion = a + "-" + qestion.substring(cut + 1);
                    qestion.substring(0, A);
                }
            }
            System.out.println(qestion);
            questionField.setText(qestion.replaceAll(" ", "  "));
            a.setText(br.readLine());
            b.setText(br.readLine());
            c.setText(br.readLine());
            d.setText(br.readLine());
            difficulty++;
        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
