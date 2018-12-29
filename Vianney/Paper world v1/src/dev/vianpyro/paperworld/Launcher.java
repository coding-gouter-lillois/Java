package dev.vianpyro.paperworld;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Launcher {

	public static int SQUARE; //Note pour le futur r�glage : taille case (SQUARE) = (largeur fen�tre / 25) * zoom
	
	private static final String versionType = "inDev"; //Variable qui indique si le jeu est en version "inDev", "Alpha", "Beta" ou jouable : ""
	private static final float version = 1.0f; //Variable qui indique le num�ro de la version
	public static int WIDTH, HEIGHT;
	
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Fonction qui detecte les dimentions de l'�cran de l'utilisateur
		WIDTH = Math.round((int)screenSize.getWidth() / 9) * 8; //D�finition de la largeur de la fen�re en fonction des dimentions de l'�cran de l'utilisateur
		HEIGHT = Math.round((int)screenSize.getHeight() / 9) * 8; //D�finition de la hauteur de la fen�re en fonction des dimentions de l'�cran de l'utilisateur
		SQUARE = WIDTH / 25; //D�finition de la taille d'une case du jeu en fonction de la largeur de la largeur de la fen�tre 
		
		Game game = new Game("Paper world V:" + versionType + "-" + version, WIDTH, HEIGHT); //Fonction qui appelle la construction du jeu
		game.start(); //Appelle la m�thode "start" de a classe "game" ; qui lancera le jeu
	}
}
