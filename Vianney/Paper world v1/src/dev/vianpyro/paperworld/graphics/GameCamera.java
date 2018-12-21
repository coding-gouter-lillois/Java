package dev.vianpyro.paperworld.graphics;

import dev.vianpyro.paperworld.Game;
import dev.vianpyro.paperworld.entities.Entity;

public class GameCamera {

	private Game game;
	private float xOffset, yOffset;
	
	public GameCamera(Game game, float xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.game = game;
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = (e.getX() - game.getWidth() / 2) + (e.getWidth() / 2);
		yOffset = (e.getY() - game.getHeight() / 2) + (e.getHeight() / 2);
	}
	
	public void move(float xAmount, float yAmount) {
		xOffset += xAmount;
		yOffset += yAmount;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
}
