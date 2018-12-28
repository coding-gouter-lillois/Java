package dev.vianpyro.paperworld.states;

import java.awt.Graphics;

import dev.vianpyro.paperworld.Handler;
import dev.vianpyro.paperworld.Launcher;
import dev.vianpyro.paperworld.graphics.Assets;
import dev.vianpyro.paperworld.user_interfaces.ClickListener;
import dev.vianpyro.paperworld.user_interfaces.UIImageButton;
import dev.vianpyro.paperworld.user_interfaces.UIManager;

public class MenuState extends State {

	private UIManager uiManager;
	private int sizeMultiplicator = 2, width = 128 * sizeMultiplicator, height = 32 * sizeMultiplicator;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton((Launcher.WIDTH / 2) - (width / 2), (Launcher.HEIGHT / 2) - (height / 2), width, height, Assets.startButton, new ClickListener() {
			
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setCurrentState(handler.getGame().gameState);
			}
		}));
	}
	
	public void tick() {
		uiManager.tick();
	}

	public void render(Graphics g) {
		uiManager.render(g);
	}
}
