/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitp.pkg0.pkg2;

import com.sun.javafx.util.Utils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;

/**
 *
 * @author Yarden zamir
 */
public class UnitP02 {

    /**
     * @param args the command line arguments
     */
    public static PMovableObject d = new PMovableObject();
    public static PMovableObject E = new PMovableObject();
    public static PWindow w = new PWindow();
    static Timer t;
    static boolean held = false;
    static long baseTime;
    static double l;
    static double y = E.getY();
    static double x = E.getX();
    static double v = 0;
    static double vx;
    static Timer grav = new Timer(0, new ActionListener() {
        double g = 9.8;
        double last;
        double del;

        @Override
        public void actionPerformed(ActionEvent e) {

            //
            if (lst > 1) {
                lst = lst - 0.01;
            } else if (lst < 0) {
                lst = lst + 0.01;
            } else {
                lst = 0;
            }
            vx = vx * 0.999;
            if (x <= 0) {
                vx = -vx;
            }
            if (x >= w.getWidth()) {
                vx = -vx;
            }
            if (y <= 0) {
                v = -v;
            }
            System.out.println("lst: " + vx);
            if (!(held || E.getY() >= w.getHeight() - E.getHeight() - 50)) {
                l = (System.currentTimeMillis() - del) / 10000;
                del = System.currentTimeMillis();
//                vx += (g * l);

                x = x + vx;
                last = (System.currentTimeMillis() - baseTime);
                double time = last / 1000;
                y = y + v;
                E.setLocation((int) x, (int) y);
                v += (g * l);
            } else {
                l = (System.currentTimeMillis() - del) / 1000;
                del = System.currentTimeMillis();

                if (v != 0) {
                    System.out.println("f: " + v);
                }
                if (E.getY() >= w.getHeight() - E.getHeight() - 50) {
                    E.setLocation((int) x, w.getHeight() - E.getHeight() - 50);
                    if (v <= 0 || v < 0.01) {
                        v = 0;
                    } else {
                        v = -0.675 * v;
                        System.out.println("low: " + v);
                        y = w.getHeight() - E.getHeight() - 51;
                        E.setLocation((int) x, (int) y);
                    }

                    if (v == 0) {
                        v = 0;
                    }

                } else {
                    v = 0;
                }

            }
        }
    });
    static Point c;
    static double lst;

    public static void main(String[] args) {
        // TODO code application logic here
//        w.addDisplayableObject(new Card());
//        d.setColor(Color.red);
//        d.setTrail(w);
        E.setColor(Color.blue);
        E.setLocation(200, 400);
        w.addDisplayableObject(d);
        w.addDisplayableObject(E);
//        d.setTrail(w);
//        d.setSize(120, 120);
        d.setLocation(0, 0);
//        d.setLocation(200, 200);
//        d.moveObj(100, 0, 500);
//        d.rotateObject(90);
//        d.moveObj(-100, 100, 500);
//        d.moveObj(0, -100, 500);
        grav.start();
        PControlUnit P = new PControlUnit(E, controlScheme.arrowKeys, w);
//        PControlUnit p = new PControlUnit(d, controlScheme.wasd, w);
        E.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                E.setLocation((int) (E.getX() + e.getX() - c.getX()), (int) (E.getY() + e.getY() - c.getY()));
                lst = (e.getY() - c.getY());
                vx = (e.getX() - c.getX());

            }

            @Override
            public void mouseMoved(MouseEvent e) {
//                move(E, E.getLocation(), e.getPoint());
            }
        });
        E.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                Random rnd = new Random();
//                move(E, E.getLocation(), new Point(rnd.nextInt(400), rnd.nextInt(400)));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                held = true;
                c = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                held = false;
                baseTime = System.currentTimeMillis();
                l = baseTime;
                y = E.getY();
                x = E.getX();
                v = lst;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    static boolean moving = false;//(float) Math.max(rangeX, -rangeX) + (float) Math.max(rangeY, -rangeY)
    static int ser = 0;

    public static void move(Component c, Point from, Point to) {
        double rangeX = to.getX() - from.getX();
        double rangeY = to.getY() - from.getY();
        move(c, from, to, (float) Math.max(rangeX, -rangeX) + (float) Math.max(rangeY, -rangeY));
    }

    public static void move(Component c, Point from, Point to, float time) {
        if (t != null) {
            t.stop();
        }
        c.setLocation(from);
        moving = true;
        System.out.println("from " + from + " to " + to);
        t = new Timer(0, new ActionListener() {
            long baseTime = System.currentTimeMillis();
            long last = System.currentTimeMillis() - baseTime;
            float lastProg = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                last = System.currentTimeMillis() - baseTime;
                double rangeX = to.getX() - from.getX();
                double rangeY = to.getY() - from.getY();
                float prog = smoothstep(0, time, last);
                prog = smootherstep(0, time, last);
                int x = (int) (from.getX() + rangeX * prog);
                int y = (int) (from.getY() + rangeY * prog);
                c.setLocation(x, y);
                if (prog != lastProg) {
                    System.out.println("prog: " + prog * 100 + " range: " + (from.getX() + rangeX * prog));
                }
                lastProg = prog;
                if (prog >= 1) {
                    t.stop();
                    System.out.println("timer stoped");
                }
            }
        });
        t.start();
    }

    public static float smoothstep(float edge0, float edge1, float x) {
        // Scale, bias and saturate x to 0..1 range
        x = (float) Utils.clamp((x - edge0) / (edge1 - edge0), 0.0, 1.0);
        // Evaluate polynomial
        return x * x * (3 - 2 * x);
    }

    public static float smootherstep(float edge0, float edge1, float x) {
        // Scale, and clamp x to 0..1 range
        x = (float) Utils.clamp((x - edge0) / (edge1 - edge0), 0.0, 1.0);
        // Evaluate polynomial
        return x * x * x * (x * (x * 6 - 15) + 10);
    }
}
