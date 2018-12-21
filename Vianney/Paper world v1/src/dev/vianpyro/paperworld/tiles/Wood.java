package dev.vianpyro.paperworld.tiles;

import dev.vianpyro.paperworld.graphics.Assets;

public class Wood extends Tile {

	public Wood(int id) {
		super(Assets.wood, id);
	}
	
	public boolean isWalkable() {
		return false;
	}
}
