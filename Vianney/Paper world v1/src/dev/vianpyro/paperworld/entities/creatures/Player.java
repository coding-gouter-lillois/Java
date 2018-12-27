package dev.vianpyro.paperworld.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import dev.vianpyro.paperworld.Handler;
import dev.vianpyro.paperworld.graphics.Assets;
import dev.vianpyro.paperworld.tiles.Tile;

public class Player extends Creature {
		
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_ENTITY_WIDTH, Creature.DEFAULT_ENTITY_HEIGHT);
		
		bounds.x = (int)(Tile.DEFAULT_TILE_WIDTH / 25);
		bounds.y = (int)(Tile.DEFAULT_TILE_HEIGHT / 25);
		bounds.width = (int)(Tile.DEFAULT_TILE_WIDTH - (bounds.x * 2));
		bounds.height = (int)(Tile.DEFAULT_TILE_HEIGHT - (bounds.y * 2));
	}

	public void tick() {
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
		g.drawImage(Assets.player, (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		
		g.setColor(Color.red);
		g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
				(int)(y + bounds.y - handler.getGameCamera().getyOffset()),
				bounds.width, bounds.height);
	}
}
