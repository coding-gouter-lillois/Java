package dev.vianpyro.paperworld.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.vianpyro.paperworld.Handler;
import dev.vianpyro.paperworld.graphics.Animation;
import dev.vianpyro.paperworld.graphics.Assets;
import dev.vianpyro.paperworld.tiles.Tile;

public class Player extends Creature {
	
	//Animation
	private Animation playerAnim, playerBlack;
		
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_ENTITY_WIDTH, Creature.DEFAULT_ENTITY_HEIGHT);
		
		bounds.x = (int)(Tile.DEFAULT_TILE_WIDTH / 25);
		bounds.y = (int)(Tile.DEFAULT_TILE_HEIGHT / 25);
		bounds.width = (int)(Tile.DEFAULT_TILE_WIDTH - (bounds.x * 2));
		bounds.height = (int)(Tile.DEFAULT_TILE_HEIGHT - (bounds.y * 2));
		
		//Animation
		playerBlack = new Animation(0, Assets.playerBlack);
		playerAnim = new Animation(75, Assets.playerAnim);
	}

	public void tick() {
		//Animation
		playerAnim.tick();
		
		//Mouvement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}
	
	public void getInput() {
		xMove = yMove = 0;
		
		if(handler.getKeyManager().up) {yMove = -speed;}
		if(handler.getKeyManager().down) {yMove = speed;}
		if(handler.getKeyManager().left) {xMove = -speed;}
		if(handler.getKeyManager().right) {xMove = speed;}
	}

	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove != 0 || yMove != 0) {
			return playerBlack.getCurrentFrame();
		} else {
			return playerAnim.getCurrentFrame();
		}
	}
}
