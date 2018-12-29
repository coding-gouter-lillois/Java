package dev.vianpyro.paperworld.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	
	public static int lookingDirection = 0; //0 = haut, 90 = droite, 180 = bas, 270 = gauche
	public boolean up, down, left, right, attack;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		attack = keys[KeyEvent.VK_SPACE];
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
		if(up) {
			lookingDirection = 0;
		} else if(right) {
			lookingDirection = 90;
		} else if(down) {
			lookingDirection = 180;
		} else if(left) {
			lookingDirection = 270;
		}
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
