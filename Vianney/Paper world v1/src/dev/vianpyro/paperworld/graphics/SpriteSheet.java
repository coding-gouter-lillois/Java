package dev.vianpyro.paperworld.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) { //Cr�ation de la m�thode qui permettra d'avoir acc�s aux images voulues n'importe quand
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int w, int h) { //Cr�ation de la m�thode qui permet de d�couper l'image 
		return sheet.getSubimage(x, y, w, h);
	}
}
