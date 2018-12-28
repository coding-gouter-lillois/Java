package dev.vianpyro.paperworld.states;

import java.awt.Graphics;

import dev.vianpyro.paperworld.Handler;
import dev.vianpyro.paperworld.worlds.World;

public class GameState extends State {
	
	//private Player player;
	private World world;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "resources/worlds/world1.lvl");
		handler.setWorld(world);
	}
	
	public void tick() {
		world.tick();
	}

	public void render(Graphics g) {
		world.render(g);
	}

}
