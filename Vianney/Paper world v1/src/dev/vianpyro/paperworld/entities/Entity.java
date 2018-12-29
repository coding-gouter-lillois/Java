package dev.vianpyro.paperworld.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.vianpyro.paperworld.Handler;

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y, health;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean active = true;
	
	public static final float DEFAULT_HEALTH = 10.0f;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void die();
	
	public void hurt(int damages) {
		health -= damages;
		if(health <= 0) {
			active = false;
			die();
		}
	}
	
	public boolean checkEntityCollision(float xOffset, float yOffset) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) {continue;}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {return true;}
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
