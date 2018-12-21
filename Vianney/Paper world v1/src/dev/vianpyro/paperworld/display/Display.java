package dev.vianpyro.paperworld.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Display {

	private JFrame frame; //Initialisation de la fenêtre
	private Canvas canvas; //Objet permetant d'afficher des graphiques sur l'écran	
	private String title; //Variable utilisée pour le nom de la fenêtre
	private int width, height; //Variables utilisées pour les dimentions de la fenêtre
	
	public Display(String title, int width, int height) {
		this.title = title; //Définition du titre de la fenêtre
		this.width = width; //Définition de la largeur de la fenêtre
		this.height = height; //Définition de la hauteur de la fenêtre
		createDisplay(); //Utilisation du constructeur
	}
	
	private void createDisplay() {
		frame = new JFrame(title); //Ajout du titre de la fenêtre à la fenêtre lors de son initialisation
		frame.setSize(width, height); //Définition des dimentions de la fenêtre 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fonction qui défini que quand la fenêtre se ferme le programme s'arrête
		frame.setResizable(false); //Fonction qui empêche de redimsentionner la fenêtre
		frame.setLocationRelativeTo(null); //Positionnement de la fenêtre au milieu de l'écran
		frame.setVisible(true); //Affichage la fenêtre 
		ImageIcon img = new ImageIcon("resources/icon.png"); //Initialisation de l'icon de la fenêtre
		frame.setIconImage(img.getImage()); //Définition de l'icon de la fenêtre
		
		canvas = new Canvas(); //Initialisation du canvas
		canvas.setPreferredSize(new Dimension(width, height)); //Définition de la taille voulue de la fenêtre
		canvas.setMinimumSize(new Dimension(width, height)); //Définition de la taille minimale de la fenêtre pour être certain que la taille du canvas soit la même que celle de la fenêtre
		canvas.setMaximumSize(new Dimension(width, height)); //Définition de la taille maximale de la fenêtre pour être certain que la taille du canvas soit la même que celle de la fenêtre
		canvas.setFocusable(false);
		
		frame.add(canvas); //Ajout du canvas à la fenêtre
		frame.pack(); //Redimentione légèrement la fenêtre pour permettre de voir entièrement le canvas
	}
	
	public Canvas getCanvas() { //Création de la méthode "getCanvas" pour permettre à d'autres classes de dessiner dessus (sur le canvas)
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
