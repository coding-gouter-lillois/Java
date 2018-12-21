package dev.vianpyro.paperworld.entities.creatures;

import dev.vianpyro.paperworld.Game;
import dev.vianpyro.paperworld.entities.Entity;

public abstract class Creature extends Entity {

	public static final float DEFAULT_HEALTH = 10.0f, DEFAULT_SPEED = 1.0f;
	
	protected float health, speed, xMove, yMove;
	
	public Creature(Game game,float x, float y, int width, int height) {
		super(game, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = yMove = 0;
	}
	
	public void move() {
		x += xMove;
		y += yMove;
	}
	
	//GETTERS & SETTERS

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	
}
