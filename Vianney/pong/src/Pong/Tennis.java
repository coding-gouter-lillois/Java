package Pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;

	//On choisi la taille de la fen�tre
	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	
	//Ajout d'une raquette
	PlayerPaddle p1;
	
	public void init(){
		//On cr�e la fen�tre selon les tailles choisies pr�cedemment
		this.resize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		
		p1 = new PlayerPaddle(1);
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g){
		//On choisi la couleur du font d'�cran
		g.setColor(Color.black);
		//On choisi l'espace rempli par la couleur choisie pour le font d'�cran
		g.fillRect(0, 0, WIDTH, HEIGHT);
		p1.draw(g);
	}
	
	public void update(Graphics g){
		paint(g);
	}

	@Override
	public void run() {
		for(;;){
			
			p1.move();
			
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
		if(e.getKeyCode() == KeyEvent.VK_UP){
			p1.setUpAccel(true);
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			p1.setDownAccel(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
			p1.setUpAccel(false);
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			p1.setDownAccel(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
