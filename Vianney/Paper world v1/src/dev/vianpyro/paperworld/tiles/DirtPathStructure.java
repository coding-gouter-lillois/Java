package dev.vianpyro.paperworld.tiles;

import dev.vianpyro.paperworld.graphics.Assets;

public class DirtPathStructure extends Tile {

	public DirtPathStructure(int id) {
		super(Assets.dirt_path, id);
	}
	
	public boolean isStructure() {
		return true;
	}
}
