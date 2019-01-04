package dev.vianpyro.paperworld.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.vianpyro.paperworld.Handler;
import dev.vianpyro.paperworld.entities.Entity;
import dev.vianpyro.paperworld.graphics.Animation;
import dev.vianpyro.paperworld.graphics.Assets;
import dev.vianpyro.paperworld.inputs.KeyManager;
import dev.vianpyro.paperworld.tiles.Tile;

public class Player extends Creature {
	
	//Animation
	private Animation playerAnim, playerBlack;
	
	//Temps d'attaque
	private long lastAttackTimer, attackCooldown = DEFAULT_ATTACK_COOLDOWN, attackTimer = attackCooldown;
		
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Tile.DEFAULT_TILE_WIDTH, Tile.DEFAULT_TILE_HEIGHT);
		
		bounds.x = (int)(Tile.DEFAULT_TILE_WIDTH / 25);
		bounds.y = (int)(Tile.DEFAULT_TILE_HEIGHT / 25);
		bounds.width = (int)(Tile.DEFAULT_TILE_WIDTH - (bounds.x * 2));
		bounds.height = (int)(Tile.DEFAULT_TILE_HEIGHT - (bounds.y * 2));
		
		//Animation
		playerBlack = new Animation(0, Assets.playerBlack);
		playerAnim = new Animation(200, Assets.playerAnim);
	}

	public void tick() {
		//Animation
		playerAnim.tick();
		
		//Mouvement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		//Attack
		checkAttacks();
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) {
			return;
		}
		
		Rectangle collisionBounds = getCollisionBounds(0, 0);		
		Rectangle attackRectangle = new Rectangle();
		attackRectangle.width = Tile.DEFAULT_TILE_WIDTH / 2;
		attackRectangle.height = Tile.DEFAULT_TILE_HEIGHT / 2;
		
		if(handler.getKeyManager().attack && KeyManager.lookingDirection == 0) {
			attackRectangle.x = collisionBounds.x + (collisionBounds.width / 2) - (attackRectangle.width / 2);
			attackRectangle.y = collisionBounds.y - attackRectangle.height;
		} else if(handler.getKeyManager().attack && KeyManager.lookingDirection == 90) {
			attackRectangle.x = collisionBounds.x + collisionBounds.width;
			attackRectangle.y = collisionBounds.y + (collisionBounds.height / 2) - (attackRectangle.height / 2);
		} else if(handler.getKeyManager().attack && KeyManager.lookingDirection == 180) {
			attackRectangle.x = collisionBounds.x + (collisionBounds.width / 2) - (attackRectangle.width / 2);
			attackRectangle.y = collisionBounds.y + collisionBounds.height;
		} else if(handler.getKeyManager().attack && KeyManager.lookingDirection == 270) {
			attackRectangle.x = collisionBounds.x - attackRectangle.width;
			attackRectangle.y = collisionBounds.y + (collisionBounds.height / 2) - (attackRectangle.height / 2);
		} else {
			return;
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) {continue;}
			if(e.getCollisionBounds(0, 0).intersects(attackRectangle)) {
				e.hurt(1);
				return;
			}
		}
	}
	
	public void die() {
		System.out.println("You died");
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
