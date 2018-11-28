package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;

	//Debug mode
	private boolean debug;
	
	//Boucle du jeu
	private Thread thread;
	private boolean running;
	private long targetTime, startTime, elapsedTime, waitTime;
	
	//Rendu du jeu
	private Graphics2D g2d;
	private BufferedImage img;
	
	//Inventaire du jeu
	private final int SIZE = 10;
	private Entity head, apple;
	private ArrayList<Entity> snake;
	private int score;
	private int level;
	private boolean gameover;
	
	//Mouvement
	private int dx, dy;
	private boolean up, down, right, left, start; 
	
	//Taille de la fenêtre
	public static final int WIDTH = 600, HEIGHT = 600;
	public GamePanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	}
	
	public void addNotify() {
		super.addNotify();
		thread = new Thread(this);
		thread.start();
	}
	
	private void setFPS(int fps){
		targetTime = 1000 / fps;
	}
	
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k == KeyEvent.VK_UP || k == KeyEvent.VK_W){up = true;}
		if(k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S){down = true;}
		if(k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A){left = true;}
		if(k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D){right = true;}
		if(k == KeyEvent.VK_ENTER || k == KeyEvent.VK_SPACE){start = true;}
		if(k == KeyEvent.VK_CONTROL){
			if(debug){
				debug = false;
			} else {
			debug = true;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		
		if(k != KeyEvent.VK_UP || k != KeyEvent.VK_W){up = false;}
		if(k != KeyEvent.VK_DOWN || k != KeyEvent.VK_S){down = false;}
		if(k != KeyEvent.VK_LEFT || k != KeyEvent.VK_A){left = false;}
		if(k != KeyEvent.VK_RIGHT || k != KeyEvent.VK_D){right = false;}
		if(k != KeyEvent.VK_ENTER || k != KeyEvent.VK_SPACE){start = false;}
	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void run() {
		if(running){
			return;
		}
		init();
		
		while(running){
			startTime = System.nanoTime();
			elapsedTime = System.nanoTime() - startTime;
			waitTime = targetTime - elapsedTime / 1000000;
			
			update();
			requestRender();
			
			if(waitTime > 0){
				try {
					Thread.sleep(waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	private void init() {
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g2d = img.createGraphics();
		running = true;
		debug = false;
		setGrowUp();
	}
	
	private void setGrowUp(){
		snake = new ArrayList<Entity>();
		head = new Entity(SIZE);
		head.setPos(WIDTH / 2, HEIGHT / 2);
		snake.add(head);
		
		
		for(int i = 1; i < 3; i++){
			Entity e = new Entity(SIZE);
			e.setPos(head.getX() + (i * SIZE), head.getY());
			snake.add(e);
		}
		
		apple = new Entity(SIZE);
		setApple();
		score = 0;
		gameover = false;
		level = 1;
		dx = dy = 0;
		setFPS(level * 10);
	}
	
	public void setApple(){
		int x = (int)(Math.random() * (WIDTH - SIZE));
		int y = (int)(Math.random() * (HEIGHT - SIZE));
		x = x - (x % SIZE);
		y = y - (y % SIZE);
		apple.setPos(x, y);
	}
	
	private void requestRender() {
		render(g2d);
		Graphics g = getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
	}

	private void update() {
		if(gameover){
			if(start){
				setGrowUp();
			}
			
			return;
		}
		if(up && dy == 0){
			dy = -SIZE;
			dx = 0;
		}
		
		if(down && dy == 0){
			dy = SIZE;
			dx = 0;
		}
		
		if(left && dx == 0){
			dx = -SIZE;
			dy = 0;
		}
		
		if(right && dx == 0 && dy != 0){
			dx = SIZE;
			dy = 0;
		}
		
		if(dx != 0 || dy != 0){
			
			for(int i = snake.size() - 1; i > 0; i--){
				snake.get(i).setPos(snake.get(i - 1).getX(), snake.get(i - 1).getY());
			}
			
			head.move(dx, dy);
		}
		
		for(Entity e : snake){
			if(e.checkCollision(head)){
				gameover = true;
				break;
			}
		}
		
		if(apple.checkCollision(head)){
			score++;
			setApple();
			
			Entity e = new Entity(SIZE);
			e.setPos(-100, -100);
			snake.add(e);
			
			if(score % 10 == 0){
				level++;
				if(level > 10){level = 10;}
				setFPS(level * 10);
			}
		}
		
		if(head.getX() < 0){head.setX(WIDTH);}
		if(head.getX() > WIDTH){head.setX(0);}
		if(head.getY() < 0){head.setY(HEIGHT);}
		if(head.getY() > HEIGHT){head.setY(0);}
	}
	
	public void render(Graphics2D g2d){
		g2d.clearRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.green);
		
		for(Entity e : snake){
			e.render(g2d);
		}
		
		g2d.setColor(Color.red);
		apple.render(g2d);
		if(gameover){
			g2d.drawString("Game Over!", (WIDTH / 2) - 10, (HEIGHT / 2));
		}
		
		g2d.setColor(Color.white);
		g2d.drawString("Score : " + score + " Speed : " + level, 10, 10);
		
		if(dx == 0 && dy == 0){
			g2d.drawString("Press any arrow to start", (WIDTH / 2) - 30, (HEIGHT / 2));
		}
		
		if(debug){
		g2d.setColor(Color.darkGray);
		g2d.drawLine(head.getX() + 5, head.getY() + 5, apple.getX() + 5, apple.getY() + 5);
		}
	}
}
