package dev.vianpyro.paperworld.states;

import java.awt.Desktop;
import java.awt.Graphics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
		
		uiManager.addObject(new UIImageButton((Launcher.WIDTH / 2) - (width / 2), (Launcher.HEIGHT / 2) - ((height / 2) * 3), width, height, Assets.button_start, new ClickListener() {
			
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setCurrentState(handler.getGame().gameState);
			}
		}));
		
		uiManager.addObject(new UIImageButton((Launcher.WIDTH / 2) - (width / 2), (Launcher.HEIGHT / 2) - ((height / 2) * 1), width, height, Assets.button_settings, new ClickListener() {
			
			public void onClick() {
//				handler.getMouseManager().setUIManager(null);
//				State.setCurrentState(handler.getGame().settingsState);
			}
		}));
		
		uiManager.addObject(new UIImageButton((Launcher.WIDTH / 2) - (width / 2), (Launcher.HEIGHT / 2) + (height / 2), width / 2, height, Assets.button_help, new ClickListener() {
			
			public void onClick() {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://github.com/WV-Lab/Paper-world/wiki/Help-page"));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
		}));

		uiManager.addObject(new UIImageButton((Launcher.WIDTH / 2), (Launcher.HEIGHT / 2) + (height / 2), width / 2, height, Assets.button_feedback, new ClickListener() {
			
			public void onClick() {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://github.com/WV-Lab/Paper-world/issues"));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
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
