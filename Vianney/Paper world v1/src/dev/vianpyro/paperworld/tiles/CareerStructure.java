package dev.vianpyro.paperworld.tiles;

import dev.vianpyro.paperworld.graphics.Assets;

public class CareerStructure extends Tile {

	public CareerStructure(int id) {
		super(Assets.career, id);
	}
	
	public boolean isWalkable() {
		return false;
	}
	
	public boolean isStructure() {
		return true;
	}
}
