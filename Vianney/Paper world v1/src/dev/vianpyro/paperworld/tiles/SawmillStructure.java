package dev.vianpyro.paperworld.tiles;

import dev.vianpyro.paperworld.graphics.Assets;

public class SawmillStructure extends Tile {

	public SawmillStructure(int id) {
		super(Assets.sawmill, id);
	}

	public boolean isWalkable() {
		return false;
	}
	
	public boolean isStructure() {
		return true;
	}
}
