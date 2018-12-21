package dev.vianpyro.paperworld.entities.creatures;

import java.awt.Graphics;

import dev.vianpyro.paperworld.Game;
import dev.vianpyro.paperworld.graphics.Assets;

public class Player extends Creature {
		
	public Player(Game game, float x, float y) {
		super(game, x, y, Creature.DEFAULT_ENTITY_WIDTH, Creature.DEFAULT_ENTITY_HEIGHT);
	}

	public void tick() {
		getInput();
		move();
		game.getGameCamera().centerOnEntity(this);
	}
	
	public void getInput() {
		xMove = yMove = 0;
		
		if(game.getKeyManager().up) {yMove = -speed;}
		if(game.getKeyManager().down) {yMove = speed;}
		if(game.getKeyManager().left) {xMove = -speed;}
		if(game.getKeyManager().right) {xMove = speed;}
	}

	public void render(Graphics g) {
		g.drawImage(Assets.player, (int)(x - game.getGameCamera().getxOffset()), (int)(y - game.getGameCamera().getyOffset()), width, height, null);
	}
}
