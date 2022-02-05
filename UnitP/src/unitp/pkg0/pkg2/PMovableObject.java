/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitp.pkg0.pkg2;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Yarden zamir
 */
public class PMovableObject extends PDisplayableObject {

    private PWindow win;
    boolean restrictToWindow = false;
    public double x;
    public double y;
    private tempMover t = new tempMover(this);

    public PMovableObject() {
        setup(false);
    }

    public PMovableObject(boolean restrictToWindow) {
        setup(restrictToWindow);
    }

    private void setup(boolean restrictToWindow) {
        this.restrictToWindow = restrictToWindow;

    }

    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y); //To change body of generated methods, choose Tools | Templates.
        x = getX();
        y = getY();
    }

    public boolean leavesTrail() {
        return win != null;
    }

    public void setTrail(PWindow w) {
        this.win = w;
//        w.drawLine(0, 0, 0, 0);
//        w.repaint();
    }

    public PWindow getTrailWindow() {
        return win;
    }

    public void onTick() {
//        moveObj(0, 9999, 9999*999);
    }

    public void onFinishMove() {

    }

    public boolean intersect(PDisplayableObject d) {

        return false;
    }

    public void moveObjTo(int x, int y, int time) {
//        moveObj((int) (x - getX()), (int) (y - getY()), time);
        UnitP02.move(this, getLocation(), new Point(x, y), 100);
    }

    public void moveObjTo(int x, int y) {
        int time = Math.max((int) (x - getX()), (int) (x + getX()));
        time += Math.max((int) (y - getY()), (int) (y + getY()));
        moveObj((int) (x - getX()), (int) (y - getY()), time / 10);
    }

    public void moveObj(double x, double y, int time) {
        t.addAction(new moveAction(x / time, y / time, time));
    }

    public void moveObj(double x, double y, int time, boolean stack) {
        t.addAction(new moveAction(x / time, y / time, time), stack);
    }

    public void moveObj(double x, double y) {
        double time = Math.max(x, -x);
        time += Math.max(y, -y);
        time = time / 10;
        moveObj(x / time, y / time, (int) time);
    }
}

class tempMover extends Thread implements ActionListener {

    private Timer t = new Timer(0, this);
    private PMovableObject m;
    ArrayList<moveAction> actions = new ArrayList<>();
    ArrayList<moveAction> StackedActions = new ArrayList<>();

    public tempMover(PMovableObject m) {
        this.m = m;
        t.start();
    }

    public void addAction(moveAction a) {
        StackedActions.add(a);
    }

    public void addAction(moveAction a, boolean toStack) {
        if (toStack)
            StackedActions.add(a);
        else
            actions.add(a);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        m.onTick();
        if (!StackedActions.isEmpty())
            runAction(StackedActions.get(0));
        for (int i = 0; i < actions.size(); i++) {
            runAction(actions.get(i));
            System.err.println("par");
        }
    }

    private void runAction(moveAction a) {
        a.setLapsed(System.currentTimeMillis() - a.getLast());
        a.setCtime(a.getCtime() + a.getLapsed());
        if (a.getLapsed() == 0)
            return;
        a.setLast(System.currentTimeMillis());
        if (a.getCtime() >= a.getTime()) {
            System.out.println("done");
            actions.remove(a);
            StackedActions.remove(a);
            m.onFinishMove();
            a.setLapsed(a.getTime() - (a.getCtime() - a.getLapsed()));
        }
        System.out.println("time:" + a.getCtime());
        System.out.println(a.getLapsed());
        Point l1 = new Point((int) m.x, (int) m.y);
        m.x += (a.getLapsed() * a.getxPerTick());
        m.y += (a.getLapsed() * a.getyPerTick());
        m.setLocation((int) m.x, (int) m.y);
        System.out.println("x: " + m.getX());
        System.out.println("y: " + m.getY());
        if (m.leavesTrail()) {
            m.getTrailWindow().drawLine(l1.x + m.getWidth() / 2, l1.y + m.getHeight() / 2, m.x + m.getWidth() / 2, m.y + m.getHeight() / 2);
        }
    }

}

class moveAction {

    private double xPerTick;
    private double yPerTick;
    private int time;
    private long last = 0;
    private long lapsed;
    private long ctime = 0;
    private int smothStart = 0;

    public int getSmothStart() {
        return smothStart;
    }

    public int getTime() {
        return time;
    }

    public double getxPerTick() {
        return xPerTick;
    }

    public double getyPerTick() {
        return yPerTick;
    }

    public long getCtime() {
        return ctime;
    }

    public long getLast() {
        if (last == 0) {
            last = System.currentTimeMillis();
        }
        return last;
    }

    public long getLapsed() {
        return lapsed;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public void setLast(long last) {
        this.last = last;
    }

    public void setLapsed(long lapsed) {
        this.lapsed = lapsed;
    }

    public moveAction(double xPerTick, double yPerTick, int time) {
        this.time = time;
        this.xPerTick = xPerTick;
        this.yPerTick = yPerTick;
//        this.smothStart = time/4;
    }

}
