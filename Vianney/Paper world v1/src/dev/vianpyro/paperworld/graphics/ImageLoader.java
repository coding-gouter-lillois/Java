package dev.vianpyro.paperworld.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) { //Définir le type d'importation en temps qu'une image
		try {
			return ImageIO.read(ImageLoader.class.getResource(path)); //Permet d'importer une image suivant le chemin d'accès "path"
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1); //Si on utilise pas l'image ce programme devient innutile, donc on l'arrête ici
		}
		
		return null; //Empêche la génération d'erreurs dans le cas où l'image n'est pas importée
	}
}
