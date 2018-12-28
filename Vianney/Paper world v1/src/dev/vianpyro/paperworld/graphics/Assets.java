package dev.vianpyro.paperworld.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 16, height = 16; //Définition de la taille des textures
	
	public static BufferedImage water, wood, stone, iron, food, dirt, port, sawmill, career, mine, farm, dirt_path; //Création des objets ayants chacuns une texture propre
	public static BufferedImage[] playerAnim, playerBlack, border;
	
	public static void initialisation() { //Créatoin de la méthode qui permet de séparer les textures

		//Bordure de la map
		SpriteSheet borderImages = new SpriteSheet(ImageLoader.loadImage("/textures/border.png")); //Importation des textures
		border = new BufferedImage[2];
		border[0] = borderImages.crop(0, 0, width, height);
		border[1] = borderImages.crop(width, 0, width, height);
		
		//Joueur
		SpriteSheet playerImages = new SpriteSheet(ImageLoader.loadImage("/textures/player.png")); //Importation des textures
		playerBlack = new BufferedImage[1];
		playerBlack[0] = playerImages.crop(0, 0, width, height); //Création de la texture de base du joueur
		playerAnim = new BufferedImage[15];
		playerAnim[0] = playerImages.crop(width, 0, width, height); //Création de la texture animée 1 du joueur
		playerAnim[1] = playerImages.crop(width * 2, 0, width, height); //Création de la texture animée 2 du joueur
		playerAnim[2] = playerImages.crop(width * 3, 0, width, height); //Création de la texture animée 3 du joueur
		playerAnim[3] = playerImages.crop(width * 4, 0, width, height); //Création de la texture animée 4 du joueur
		playerAnim[4] = playerImages.crop(width * 5, 0, width, height); //Création de la texture animée 5 du joueur
		playerAnim[5] = playerImages.crop(width * 6, 0, width, height); //Création de la texture animée 6 du joueur
		playerAnim[6] = playerImages.crop(width * 7, 0, width, height); //Création de la texture animée 7 du joueur
		playerAnim[7] = playerImages.crop(0, height, width, height); //Création de la texture animée 8 du joueur
		playerAnim[8] = playerImages.crop(width, height, width, height); //Création de la texture animée 9 du joueur
		playerAnim[9] = playerImages.crop(width * 2, height, width, height); //Création de la texture animée 10 du joueur
		playerAnim[10] = playerImages.crop(width * 3, height, width, height); //Création de la texture animée 11 du joueur
		playerAnim[11] = playerImages.crop(width * 4, height, width, height); //Création de la texture animée 12 du joueur
		playerAnim[12] = playerImages.crop(width * 5, height, width, height); //Création de la texture animée 13 du joueur
		playerAnim[13] = playerImages.crop(width * 6, height, width, height); //Création de la texture animée 14 du joueur
		playerAnim[14] = playerImages.crop(width * 7, height, width, height); //Création de la texture animée 15 du joueur

		//Cases
		SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("/textures/tiles.png")); //Importation des textures
		water = tiles.crop(0, 0, 1, 1); //Création de la texture unique de l'eau
		wood = tiles.crop(1, 0, 1, 1); //Création de la texture unique de l'arbre (bois)
		stone = tiles.crop(2, 0, 1, 1); //Création de la texture unique de la pierre
		iron = tiles.crop(3, 0, 1, 1); //Création de la texture unique du fer
		food = tiles.crop(4, 0, 1, 1); //Création de la texture unique de la nourriture
		dirt = tiles.crop(5, 0, 1, 1); //Création de la texture unique de la terre
		
		//Structures
		SpriteSheet structures = new SpriteSheet(ImageLoader.loadImage("/textures/structures.png")); //Importation des textures
		port = structures.crop(0, 0, width, height); //Création de la texture unique du port
		sawmill = structures.crop(1, 0, width, height); //Création de la texture unique de la scierie
		career = structures.crop(width * 2, 0, width, height); //Création de la texture unique de la carrière
		mine = structures.crop(width * 3, 0, width, height); //Création de la texture unique de la mine
		farm = structures.crop(width * 4, 0, width, height); //Création de la texture unique de la ferme
		dirt_path = structures.crop(width * 5, 0, width, height); //Création de la texture unique du chemin (boost de vitesse *1.25)
	}
}
