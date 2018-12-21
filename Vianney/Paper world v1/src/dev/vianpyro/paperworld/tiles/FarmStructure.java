package dev.vianpyro.paperworld.tiles;

import dev.vianpyro.paperworld.graphics.Assets;

public class FarmStructure extends Tile {

	public FarmStructure(int id) {
		super(Assets.farm, id);
	}

	public boolean isWalkable() {
		return false;
	}
	
	public boolean isStructure() {
		return true;
	}
}
