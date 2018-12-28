package dev.vianpyro.paperworld.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.vianpyro.paperworld.Handler;

public class MenuState extends State {

	public MenuState(Handler handler) {
		super(handler);
	}
	
	public void tick() {
		if(handler.getMouseManager().isLeftPressed()) {
			State.setCurrentState(handler.getGame().gameState);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
	}
}
