package dev.vianpyro.paperworld.tiles;

import dev.vianpyro.paperworld.graphics.Assets;

public class Water extends Tile {

	public Water(int id) {
		super(Assets.water, id);
	}
	
	public boolean isWalkable() {
		return false;
	}
}
