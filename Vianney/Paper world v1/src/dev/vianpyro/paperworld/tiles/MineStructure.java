package dev.vianpyro.paperworld.tiles;

import dev.vianpyro.paperworld.graphics.Assets;

public class MineStructure extends Tile {

	public MineStructure(int id) {
		super(Assets.mine, id);
	}

	public boolean isWalkable() {
		return false;
	}
	
	public boolean isStructure() {
		return true;
	}
}
