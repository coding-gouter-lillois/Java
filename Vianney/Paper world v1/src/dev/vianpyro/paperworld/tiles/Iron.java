package dev.vianpyro.paperworld.tiles;

import dev.vianpyro.paperworld.graphics.Assets;

public class Iron extends Tile {

	public Iron(int id) {
		super(Assets.iron, id);
	}
	
	public boolean isWalkable() {
		return false;
	}
}
