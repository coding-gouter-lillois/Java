package dev.vianpyro.paperworld.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.vianpyro.paperworld.Launcher;

public class Tile {
	
	//TRUCS STATIQUES 
	public static Tile[] tiles = new Tile[256]; 
	public static Tile waterTile = new Water(0),
						woodTile = new Wood(1),
						stoneTile = new Stone(2),
						ironTile = new Iron(3),
						foodTile = new Food(4),
						dirtTile = new Dirt(5),
						portStructure = new PortStructure(128),
						sawmillStructure = new SawmillStructure(129),
						mineStruture = new MineStructure(131),
						farmStructure = new FarmStructure(132),
						dirt_pathStructure = new DirtPathStructure(133);
	
	//CLASSES
	public static final int DEFAULT_TILE_WIDTH = Launcher.SQUARE, DEFAULT_TILE_HEIGHT = Launcher.SQUARE;

	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, DEFAULT_TILE_WIDTH, DEFAULT_TILE_HEIGHT, null);
	}
	
	public boolean isWalkable() {
		return true;
	}
	
	public boolean isStructure() {
		return false;
	}
	
	public int getId() {
		return id;
	}
}
