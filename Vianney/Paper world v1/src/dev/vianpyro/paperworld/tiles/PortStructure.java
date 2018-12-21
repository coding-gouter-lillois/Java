package dev.vianpyro.paperworld.tiles;

import dev.vianpyro.paperworld.graphics.Assets;

public class PortStructure extends Tile {

	public PortStructure(int id) {
		super(Assets.port, id);
	}
	
	public boolean isWalkable() {
		return false;
	}
	
	public boolean isStructure() {
		return true;
	}
}
