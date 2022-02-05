/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitp.pkg0.pkg2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * a class to control movable objects
 *
 * @author Yarden zamir
 */
public class PControlUnit implements KeyListener {

    private controlScheme c;
    private PMovableObject M;
    private int xV, yV;
    private int time;

    /**
     *
     * @param M
     * @param c
     * @param window the window to listen to
     */
    public PControlUnit(PMovableObject M, controlScheme c, PWindow window) {
        setUp(M, c, window, 50, 50, 100);
    }

    public PControlUnit(PMovableObject M, controlScheme c, PWindow window, int xV, int yV) {
        setUp(M, c, window, xV, yV, 100);
    }

    public PControlUnit(PMovableObject M, controlScheme c, PWindow window, int xV, int yV, int time) {
        setUp(M, c, window, xV, yV, time);
    }

    private void setUp(PMovableObject M, controlScheme c, PWindow window, int xV, int yV, int time) {
        this.c = c;
        this.M = M;
        window.addKeyListener(this);
        this.xV = xV;
        this.yV = yV;
        this.time = time;
    }

    public controlScheme getControlScheme() {
        return this.c;
    }

    public void setControlScheme(controlScheme c) {
        this.c = c;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (c) {
            case arrowKeys:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        M.moveObj(0, -yV, time, false);
                        break;
                    case KeyEvent.VK_DOWN:
                        M.moveObj(0, yV, time, false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        M.moveObj(xV, 0, time, false);
                        break;
                    case KeyEvent.VK_LEFT:
                        M.moveObj(-xV, 0, time, false);
                        break;
                    default:
                        break;
                }
                break;
            case wasd:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        M.moveObj(0, -yV, time, false);
                        break;
                    case KeyEvent.VK_S:
                        M.moveObj(0, yV, time, false);
                        break;
                    case KeyEvent.VK_D:
                        M.moveObj(xV, 0, time, false);
                        break;
                    case KeyEvent.VK_A:
                        M.moveObj(-xV, 0, time, false);
                        break;
                    default:
                        break;
                }
                break;
            case numPadArrowKeys:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_NUMPAD8:
                        M.moveObj(0, -yV, time, false);
                        break;
                    case KeyEvent.VK_NUMPAD2:
                        M.moveObj(0, yV, time, false);
                        break;
                    case KeyEvent.VK_NUMPAD6:
                        M.moveObj(xV, 0, time, false);
                        break;
                    case KeyEvent.VK_NUMPAD4:
                        M.moveObj(-xV, 0, time, false);
                        break;
                    default:
                        break;
                }
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
