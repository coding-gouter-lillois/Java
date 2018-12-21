package dev.vianpyro.paperworld.states;

import java.awt.Graphics;

import dev.vianpyro.paperworld.Game;
import dev.vianpyro.paperworld.Launcher;
import dev.vianpyro.paperworld.entities.creatures.Player;
import dev.vianpyro.paperworld.tiles.Tile;
import dev.vianpyro.paperworld.worlds.World;

public class GameState extends State {
	
	private Player player;
	private World world;

	public GameState(Game game) {
		super(game);
		player = new Player(game, 100, 100);
		world = new World(game, "resources/worlds/world1.lvl");
	}
	
	public void tick() {
		world.tick();
		player.tick();
	}

	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}

}
