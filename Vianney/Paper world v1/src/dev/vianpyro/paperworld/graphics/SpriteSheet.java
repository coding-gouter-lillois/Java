package dev.vianpyro.paperworld.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) { //Création de la méthode qui permettra d'avoir accès aux images voulues n'importe quand
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int w, int h) { //Création de la méthode qui permet de découper l'image 
		return sheet.getSubimage(x, y, w, h);
	}
}
