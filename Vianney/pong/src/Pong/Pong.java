package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;

	//On choisi la taille de la fenêtre
	public final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	
	//Ajout des raquettes
	PlayerPaddle leftPlayer;
	PlayerPaddle rightPlayer;
	
	//Ajout de la balle
	Ball ball;
	
	//Démarrage de la partie
	boolean gameStarted;
	
	//On enlève le clignotement
	Graphics gfx;
	Image img;
	
	public void init(){
		//On crée la fenêtre selon les tailles choisies précedemment
		this.resize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		gameStarted = false;
		leftPlayer = new PlayerPaddle(1);
		rightPlayer = new PlayerPaddle(2);
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		ball = new Ball();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g){
		//On choisi la couleur du font d'écran
		gfx.setColor(Color.black);
		//On choisi l'espace rempli par la couleur choisie pour le font d'écran
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		
		//Game over
		if(ball.getX() < -10 || ball.getX() > 710){
			gfx.setColor(Color.red);
			gfx.drawString("Game Over", 340, 250);
		} else{
			//On dessine la raquette
			leftPlayer.draw(gfx);
			rightPlayer.draw(gfx);
			//On dessine la balle
			ball.draw(gfx);
		}
		
		if(!gameStarted){
			gfx.setColor(Color.white);
			gfx.drawString("PONG!", 340, 100);
			gfx.drawString("Press space bar to begin...", 290, 130);
		}
		g.drawImage(img, 0, 0, this);
	}
	
	public void update(Graphics g){
		paint(g);
	}

	@Override
	public void run() {
		for(;;){
			if(gameStarted){
				//On ajoute le mouvement des raquettes
				leftPlayer.move();
				rightPlayer.move();
				//On ajoute le mouvement de la balle
				ball.move();
				
				//On ajoute les collisions
				ball.checkPaddleCollision(leftPlayer, rightPlayer);
				
			}
			
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
		if(e.getKeyCode() == KeyEvent.VK_W){
			leftPlayer.setUpAccel(true);
		} else if(e.getKeyCode() == KeyEvent.VK_S){
			leftPlayer.setDownAccel(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			rightPlayer.setUpAccel(true);
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			rightPlayer.setDownAccel(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
			gameStarted = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
			leftPlayer.setUpAccel(false);
		} else if(e.getKeyCode() == KeyEvent.VK_S){
			leftPlayer.setDownAccel(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP){
			rightPlayer.setUpAccel(false);
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			rightPlayer.setDownAccel(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
