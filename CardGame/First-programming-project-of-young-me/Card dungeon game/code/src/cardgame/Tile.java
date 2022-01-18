package cardgame;

import java.awt.Point;
import java.io.File;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * @author Yarden zamir
 */
public class Tile extends JPanel {

    public static int tiles = 0;
    public boolean isBad = false;
    public static Point lastTile = new Point(31, 31);
    public boolean pathUp=false, pathDown=false, pathRight=false, pathLeft=false;
    public JLabel backTex = new JLabel();
    private Random rnd = new Random();

    public Tile() {
        setLayout(null);
        setSize(360, 360);
        add(backTex);
        backTex.setSize(360, 360);
    }

    public void genMe(int entrence) {
        if (tiles <= 0) {
            tiles++;
            backTex.setIcon(new ImageIcon(Systems.Graphic.ImgReader.ReadImage(new File(Player.RsrcFolder + "\\Tiles\\Start.jpg"))));
            pathLeft = true;
            pathDown = true;
            pathRight = true;
            pathUp = true;
            setLocation(31, 31);
            addBlockedTiles();
        } else {
            Player.addScore(5);
            tiles++;
            File f = null;
            switch (entrence) {
                case 1:
                    //up
                    
                    f = new File(Player.RsrcFolder + "\\tiles\\up");
                    f = f.listFiles()[rnd.nextInt(f.listFiles().length)];
                    setLocation(lastTile.x, lastTile.y + 360);
                    break;
                case 2:
                    f = new File(Player.RsrcFolder + "\\tiles\\left");
                    f = f.listFiles()[rnd.nextInt(f.listFiles().length)];
                    setLocation(lastTile.x + 360, lastTile.y);
                    break;
                case 3:
                    f = new File(Player.RsrcFolder + "\\tiles\\down");
                    f = f.listFiles()[rnd.nextInt(f.listFiles().length)];
                    setLocation(lastTile.x, lastTile.y - 360);
                    break;
                case 4:
                    f = new File(Player.RsrcFolder + "\\tiles\\right");
                    f = f.listFiles()[rnd.nextInt(f.listFiles().length)];
                    setLocation(lastTile.x - 360, lastTile.y);
                    break;
            }
            if (f.getName().contains("Up") || f.getName().contains("up")) {
                pathUp = true;
            }
            if (f.getName().contains("down") || f.getName().contains("Down")) {
                pathDown = true;
            }
            if (f.getName().contains("right") || f.getName().contains("Right")) {
                pathLeft = true;
            }
            if (f.getName().contains("left") || f.getName().contains("Left")) {
                pathRight = true;
            }
            backTex.setIcon(new ImageIcon(Systems.Graphic.ImgReader.ReadImage(f)));
        }
        GameOperator.B.tileSpace.setLayer(this, 0);
        addBlockedTiles();
    }

    private void addBlockedTiles() {
        if (Battle.takenLocs.contains(new Point(getX() + 5, getY() + 5))){
            isBad=true;
        }else {
            Battle.takenLocs.add(new Point(getX() + 5, getY() + 5));
            Battle.takenLocs.add(new Point(getX() + 65, getY() + 5));
            Battle.takenLocs.add(new Point(getX() + 5, getY() + 65));

            Battle.takenLocs.add(new Point(getX() + 5, getY() + 305));
            Battle.takenLocs.add(new Point(getX() + 65, getY() + 305));
            Battle.takenLocs.add(new Point(getX() + 5, getY() + 245));

            Battle.takenLocs.add(new Point(getX() + 305, getY() + 5));
            Battle.takenLocs.add(new Point(getX() + 245, getY() + 5));
            Battle.takenLocs.add(new Point(getX() + 305, getY() + 65));

            Battle.takenLocs.add(new Point(getX() + 305, getY() + 305));
            Battle.takenLocs.add(new Point(getX() + 305, getY() + 245));
            Battle.takenLocs.add(new Point(getX() + 245, getY() + 305));

            if (!pathDown) {
                Battle.takenLocs.add(new Point(getX() + 125, getY() + 305));
                Battle.takenLocs.add(new Point(getX() + 185, getY() + 305));
            } else {

            }
            if (!pathLeft) {
                Battle.takenLocs.add(new Point(getX() + 305, getY() + 185));
                Battle.takenLocs.add(new Point(getX() + 305, getY() + 125));
            }
            if (!pathRight) {
                Battle.takenLocs.add(new Point(getX() + 5, getY() + 185));
                Battle.takenLocs.add(new Point(getX() + 5, getY() + 125));
            }
            if (!pathUp) {
                Battle.takenLocs.add(new Point(getX() + 125, getY() + 5));
                Battle.takenLocs.add(new Point(getX() + 185, getY() + 5));
            }
        }
    }
}
