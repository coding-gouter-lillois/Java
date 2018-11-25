package Pong;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener {

    private static final long serialVersionUID = 1L;

    //On choisi la taille de la fenêtre
    private final int WIDTH = 700, HEIGHT = 500;

    //Ajout d'une raquette
    private PlayerPaddle leftPlayer;

    public void init() {
        //On crée la fenêtre selon les tailles choisies précedemment
        this.resize(WIDTH, HEIGHT);
        this.addKeyListener(this);

        leftPlayer = new PlayerPaddle(1);

        Thread thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g) {
        //On choisi la couleur du font d'écran
        g.setColor(Color.black);
        //On choisi l'espace rempli par la couleur choisie pour le font d'écran
        g.fillRect(0, 0, WIDTH, HEIGHT);
        leftPlayer.draw(g);
    }

    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void run() {
        while (true) {

            leftPlayer.move();

            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            leftPlayer.setUpAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            leftPlayer.setDownAccel(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Call when you release a key, not necessary to implement now.
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }
}
