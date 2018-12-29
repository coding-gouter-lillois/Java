package dev.vianpyro.paperworld.worlds;

import java.awt.Graphics;

import dev.vianpyro.paperworld.Handler;
import dev.vianpyro.paperworld.Launcher;
import dev.vianpyro.paperworld.entities.EntityManager;
import dev.vianpyro.paperworld.entities.creatures.Player;
import dev.vianpyro.paperworld.entities.statics.structures.Career;
import dev.vianpyro.paperworld.tiles.Tile;
import dev.vianpyro.paperworld.utils.Utils;

public class World {

	private Handler handler;
	private int width, height, spawnX = (Launcher.WIDTH / (2 * Tile.DEFAULT_TILE_WIDTH)) * Tile.DEFAULT_TILE_WIDTH, 
								spawnY = (Launcher.HEIGHT / (2 * Tile.DEFAULT_TILE_HEIGHT)) * Tile.DEFAULT_TILE_HEIGHT;
	private int[][] tiles;
	//Entitées
	private EntityManager entityManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, spawnX, spawnY));
		entityManager.addEntity(new Career(handler, 100, 250));
		
		loadWorld(path);
		
//		entityManager.getPlayer().setX(spawnX);
//		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick() {
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		int xStart = (int)Math.max(0, handler.getGameCamera().getxOffset() / Tile.DEFAULT_TILE_WIDTH),
			xEnd = (int)Math.min(width, ((handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.DEFAULT_TILE_WIDTH) + 1),
			yStart = (int)Math.max(0, handler.getGameCamera().getyOffset() / Tile.DEFAULT_TILE_HEIGHT),
			yEnd = (int)Math.min(height, ((handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.DEFAULT_TILE_HEIGHT) +1 );
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int)(x * Tile.DEFAULT_TILE_WIDTH - handler.getGameCamera().getxOffset()), (int)(y * Tile.DEFAULT_TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {return Tile.dirtTile;} //Si le joueur arrive à sortir de la map, il "sera sur de la terre" pour ne par faire planter ni bugger le jeu
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {return Tile.waterTile;} //Au cas où une case est vide, on définit que c'est de l'eau par défaut
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
