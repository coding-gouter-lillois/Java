package dev.vianpyro.paperworld.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 16, height = 16; //D�finition de la taille des textures
	
	public static BufferedImage water, wood, stone, iron, food, dirt, port, sawmill, career, mine, farm, dirt_path; //Cr�ation des objets ayants chacuns une texture propre
	public static BufferedImage[] playerAnim, playerBlack, border, button_start, button_settings, button_help, button_feedback; //Cr�ation des objets ayants des textures anim�es
	
	public static void initialisation() { //Cr�atoin de la m�thode qui permet de s�parer les textures

		//Importation des textures
		SpriteSheet playerImages = new SpriteSheet(ImageLoader.loadImage("/textures/player.png")); //Importation des textures du joueur
		SpriteSheet structures = new SpriteSheet(ImageLoader.loadImage("/textures/structures.png")); //Importation des textures des structures
		SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("/textures/tiles.png")); //Importation des textures des cases
		SpriteSheet ui = new SpriteSheet(ImageLoader.loadImage("/textures/user_interfaces/menu.png")); //Importation des textures du menu

		//Cr�ation des buffers
		playerBlack = new BufferedImage[1];
		playerAnim = new BufferedImage[8];
		button_start = new BufferedImage[2];
		button_settings = new BufferedImage[2];
		button_help = new BufferedImage[2];
		button_feedback = new BufferedImage[2];
		
		//G�n�ration des textures du joueur
		playerBlack[0] = playerImages.crop(0, 0, width, height); //Cr�ation de la texture de base du joueur
		playerAnim[0] = playerImages.crop(width, 0, width, height); //Cr�ation de la texture anim�e 1 du joueur
		playerAnim[1] = playerImages.crop(width * 2, 0, width, height); //Cr�ation de la texture anim�e 2 du joueur
		playerAnim[2] = playerImages.crop(width * 3, 0, width, height); //Cr�ation de la texture anim�e 3 du joueur
		playerAnim[3] = playerImages.crop(width * 4, 0, width, height); //Cr�ation de la texture anim�e 4 du joueur
		playerAnim[4] = playerImages.crop(width * 5, 0, width, height); //Cr�ation de la texture anim�e 5 du joueur
		playerAnim[5] = playerImages.crop(width * 6, 0, width, height); //Cr�ation de la texture anim�e 6 du joueur
		playerAnim[6] = playerImages.crop(width * 7, 0, width, height); //Cr�ation de la texture anim�e 7 du joueur
		playerAnim[7] = playerImages.crop(width * 8, 0, width, height); //Cr�ation de la texture anim�e 8 du joueur
	
		//G�n�ration des textures des structures
		port = structures.crop(0, 0, width, height); //Cr�ation de la texture unique du port
		sawmill = structures.crop(1, 0, width, height); //Cr�ation de la texture unique de la scierie
		career = structures.crop(width * 2, 0, width, height); //Cr�ation de la texture unique de la carri�re
		mine = structures.crop(width * 3, 0, width, height); //Cr�ation de la texture unique de la mine
		farm = structures.crop(width * 4, 0, width, height); //Cr�ation de la texture unique de la ferme
		dirt_path = structures.crop(width * 5, 0, width, height); //Cr�ation de la texture unique du chemin (boost de vitesse *1.25)
	
		//G�n�ration des textures des cases
		water = tiles.crop(0, 0, 1, 1); //Cr�ation de la texture unique de l'eau
		wood = tiles.crop(1, 0, 1, 1); //Cr�ation de la texture unique de l'arbre (bois)
		stone = tiles.crop(2, 0, 1, 1); //Cr�ation de la texture unique de la pierre
		iron = tiles.crop(3, 0, 1, 1); //Cr�ation de la texture unique du fer
		food = tiles.crop(4, 0, 1, 1); //Cr�ation de la texture unique de la nourriture
		dirt = tiles.crop(5, 0, 1, 1); //Cr�ation de la texture unique de la terre
		
		//G�n�ration des textures du menu
		button_start[0] = ui.crop(0, 0, width * 8, height * 2); //Cr�ation de la texture grise du bouton de d�marrage
		button_start[1] = ui.crop(0, height * 2, width * 8, height * 2); //Cr�ation de la texture bleue du bouton de d�marrage
		button_settings[0] = ui.crop(0, height * 4, width * 8, height * 2); //Cr�ation de la texture grise du bouton d'options
		button_settings[1] = ui.crop(0, height * 6, width * 8, height * 2); //Cr�ation de la texture bleue du bouton d'options	
		button_help[0] = ui.crop(0, height * 8, width * 2, height * 2); //Cr�ation de la texture bleue du bouton d'aide
		button_help[1] = ui.crop(width * 2, height * 8, width * 2, height * 2); //Cr�ation de la texture bleue du bouton d'aide
		button_feedback[0] = ui.crop(width * 4, height * 8, width * 2, height * 2); //Cr�ation de la texture bleue du bouton de feedback
		button_feedback[1] = ui.crop(width * 6, height * 8, width * 2, height * 2); //Cr�ation de la texture bleue du bouton de feedback
	}
	
}
