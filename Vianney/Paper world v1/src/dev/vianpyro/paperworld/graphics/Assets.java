package dev.vianpyro.paperworld.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 16, height = 16; //Définition de la taille des textures
	
	public static BufferedImage player, water, wood, stone, iron, food, dirt, port, sawmill, career, mine, farm, dirt_path; //Création des objets ayants chacuns une texture propre
	
	public static void initialisation() { //Créatoin de la méthode qui permet de séparer les textures
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/basic_textures.png")); //Importation des textures
		
		player = sheet.crop(width * 0, height * 0, width * 1, height * 1); //Création de la texture unique du joueur
		
		//Resources
		water = sheet.crop(width + 0, height * 0, 1, 1); //Création de la texture unique de l'eau
		wood = sheet.crop(width + 1, height * 0, 1, 1); //Création de la texture unique de l'arbre (bois)
		stone = sheet.crop(width + 2, height * 0, 1, 1); //Création de la texture unique de la pierre
		iron = sheet.crop(width + 3, height * 0, 1, 1); //Création de la texture unique du fer
		food = sheet.crop(width + 4, height * 0, 1, 1); //Création de la texture unique de la nourriture
		dirt = sheet.crop(width + 5, height * 0, 1, 1); //Création de la texture unique de la terre
		
		//Structures
		port = sheet.crop(width * 2, height * 0, width * 1, height * 1); //Création de la texture unique du port
		sawmill = sheet.crop(width * 3, height * 0, width * 1, height * 1); //Création de la texture unique de la scierie
		career = sheet.crop(width * 4, height * 0, width * 1, height * 1); //Création de la texture unique de la carrière
		mine = sheet.crop(width * 5, height * 0, width * 1, height * 1); //Création de la texture unique de la mine
		farm = sheet.crop(width * 6, height * 0, width * 1, height * 1); //Création de la texture unique de la ferme
		dirt_path = sheet.crop(width * 7, height * 0, width * 1, height * 1); //Création de la texture unique du chemin (boost de vitesse *1.25)
	}
}
