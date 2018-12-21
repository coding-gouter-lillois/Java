package dev.vianpyro.paperworld.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) { //D�finir le type d'importation en temps qu'une image
		try {
			return ImageIO.read(ImageLoader.class.getResource(path)); //Permet d'importer une image suivant le chemin d'acc�s "path"
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1); //Si on utilise pas l'image ce programme devient innutile, donc on l'arr�te ici
		}
		
		return null; //Emp�che la g�n�ration d'erreurs dans le cas o� l'image n'est pas import�e
	}
}
