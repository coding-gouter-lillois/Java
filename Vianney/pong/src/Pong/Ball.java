package game;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	double xVel, yVel, x, y, speed;
	
	public Ball(){
		x = 350;
		y = 250;
		xVel = 2 * getRandomDirection();
		yVel = 2 * getRandomDirection();
	}
	
	public int getRandomDirection(){
		int random = (int)(Math.random() *2);
		if(random == 1){
			return 1;
		} else {
			return -1;
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.fillOval((int)x - 10, (int)y - 10, 20, 20);
	}
	
	public void checkPaddleCollision(Paddle p1, Paddle p2){
		if(x <= 50){
			if(y >= p1.getY() && y <= p1.getY() + 80){
				xVel *= -1;
			}
		} else if(x >= 650) {
			if(y >= p2.getY() && y <= p2.getY() + 80){
				xVel *= -1;
			}
		}
	}
	
	public void move(){
		x += xVel;
		y += yVel;
		
		if(y <= 10 || y >= 490){
			yVel *= -1;
		}		
	}
	
	public int getX(){
		return (int)x;
	}
	
	public int getY(){
		return (int)y;
	}

}
