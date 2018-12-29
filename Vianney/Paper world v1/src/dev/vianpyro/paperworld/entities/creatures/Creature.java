package dev.vianpyro.paperworld.entities.creatures;

import dev.vianpyro.paperworld.Handler;
import dev.vianpyro.paperworld.entities.Entity;
import dev.vianpyro.paperworld.tiles.Tile;

public abstract class Creature extends Entity {

	public static final float DEFAULT_HEALTH = 10.0f, DEFAULT_SPEED = 1.0f;
	
	protected float health, speed, xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollision(xMove, 0f)) {moveX();}
		if(!checkEntityCollision(0f, yMove)) {moveY();}
	}
	
	public void moveX() {
		if(xMove > 0) { //Avance à droite
			int dx = (int)(x + xMove + bounds.x + bounds.width) / Tile.DEFAULT_TILE_WIDTH; //Détection de la case en suivant la direction
			
			if(canWalkOnTile(dx, (int)(y + bounds.y) / Tile.DEFAULT_TILE_HEIGHT) && 
				canWalkOnTile(dx, (int)(y + bounds.y + bounds.height) / Tile.DEFAULT_TILE_HEIGHT)) {
				x += xMove * speed;
			} else {
				x = dx * Tile.DEFAULT_TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		} else if(xMove < 0) { //Avance à gauche
			int dx = (int)(x + xMove + bounds.x) / Tile.DEFAULT_TILE_WIDTH; //Détection de la case en suivant la direction
			
			if(canWalkOnTile(dx, (int)(y + bounds.y) / Tile.DEFAULT_TILE_HEIGHT) && 
				canWalkOnTile(dx, (int)(y + bounds.y + bounds.height) / Tile.DEFAULT_TILE_HEIGHT)) {
				x += xMove * speed;
			} else {
				x = dx * Tile.DEFAULT_TILE_WIDTH + Tile.DEFAULT_TILE_WIDTH - bounds.x;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) { //Avance en haut
			int dy = (int)(y + yMove + bounds.y) / Tile.DEFAULT_TILE_HEIGHT;
			
			if(canWalkOnTile((int)(x + bounds.x) / Tile.DEFAULT_TILE_WIDTH, dy) &&
				canWalkOnTile((int)(x + bounds.x + bounds.width) / Tile.DEFAULT_TILE_WIDTH, dy)) {
				y += yMove * speed;
			} else {
				y = dy * Tile.DEFAULT_TILE_HEIGHT + Tile.DEFAULT_TILE_HEIGHT - bounds.y;
			}
		} else if(yMove > 0) { //Avance en bas
			int dy = (int)(y + yMove + bounds.y + bounds.height) / Tile.DEFAULT_TILE_HEIGHT;
			
			if(canWalkOnTile((int)(x + bounds.x) / Tile.DEFAULT_TILE_WIDTH, dy) &&
				canWalkOnTile((int)(x + bounds.x + bounds.width) / Tile.DEFAULT_TILE_WIDTH, dy)) {
				y += yMove * speed;
			} else {
				y = dy * Tile.DEFAULT_TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}
	
	protected boolean canWalkOnTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isWalkable();
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
