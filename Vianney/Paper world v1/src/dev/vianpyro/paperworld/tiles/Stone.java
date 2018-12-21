package dev.vianpyro.paperworld.tiles;

import dev.vianpyro.paperworld.graphics.Assets;

public class Stone extends Tile {

	public Stone(int id) {
		super(Assets.stone, id);
	}
	
	public boolean isWalkable() {
		return false;
	}
}
