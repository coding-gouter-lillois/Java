package game;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerPaddle implements Paddle{

	double y;
	double yVel;
	boolean upAccel, downAccel;
	int x;
	final double INERTIA = 0.94;
	
	public PlayerPaddle(int playerId){
		upAccel = false;
		downAccel = false;
		y = 210; yVel = 0;
		
		if(playerId == 1){
			x = 20;
		} else {
			x = 660;
		}
		
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int)y, 20, 80);
	}
	

	@Override
	public void move() {
		if(upAccel){
			yVel -= 2;
		} else if(downAccel){
			yVel += 2;
		} else if(!upAccel && !downAccel){
			yVel *= INERTIA;
		}
		
		if(yVel >= 5){
			yVel =5;
		} else if(yVel <= -5){
			yVel = -5;
		}
		
		//La raquette bouge
		y += yVel;
		
		//On empêche la raquette de dépasser son cadre
		if(y <= 0){
			y = 0;
		}
		if(y >= 420){
			y = 420;
		}
		
	}

	
	public void setUpAccel(boolean input){
		upAccel = input;
	}
	
	public void setDownAccel(boolean input){
		downAccel = input;
	}

	@Override
	public int getY(){
		return (int)y;
	}
}
