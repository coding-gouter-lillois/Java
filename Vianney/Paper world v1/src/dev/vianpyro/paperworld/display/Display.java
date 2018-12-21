package dev.vianpyro.paperworld.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Display {

	private JFrame frame; //Initialisation de la fen�tre
	private Canvas canvas; //Objet permetant d'afficher des graphiques sur l'�cran	
	private String title; //Variable utilis�e pour le nom de la fen�tre
	private int width, height; //Variables utilis�es pour les dimentions de la fen�tre
	
	public Display(String title, int width, int height) {
		this.title = title; //D�finition du titre de la fen�tre
		this.width = width; //D�finition de la largeur de la fen�tre
		this.height = height; //D�finition de la hauteur de la fen�tre
		createDisplay(); //Utilisation du constructeur
	}
	
	private void createDisplay() {
		frame = new JFrame(title); //Ajout du titre de la fen�tre � la fen�tre lors de son initialisation
		frame.setSize(width, height); //D�finition des dimentions de la fen�tre 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fonction qui d�fini que quand la fen�tre se ferme le programme s'arr�te
		frame.setResizable(false); //Fonction qui emp�che de redimsentionner la fen�tre
		frame.setLocationRelativeTo(null); //Positionnement de la fen�tre au milieu de l'�cran
		frame.setVisible(true); //Affichage la fen�tre 
		ImageIcon img = new ImageIcon("resources/icon.png"); //Initialisation de l'icon de la fen�tre
		frame.setIconImage(img.getImage()); //D�finition de l'icon de la fen�tre
		
		canvas = new Canvas(); //Initialisation du canvas
		canvas.setPreferredSize(new Dimension(width, height)); //D�finition de la taille voulue de la fen�tre
		canvas.setMinimumSize(new Dimension(width, height)); //D�finition de la taille minimale de la fen�tre pour �tre certain que la taille du canvas soit la m�me que celle de la fen�tre
		canvas.setMaximumSize(new Dimension(width, height)); //D�finition de la taille maximale de la fen�tre pour �tre certain que la taille du canvas soit la m�me que celle de la fen�tre
		canvas.setFocusable(false);
		
		frame.add(canvas); //Ajout du canvas � la fen�tre
		frame.pack(); //Redimentione l�g�rement la fen�tre pour permettre de voir enti�rement le canvas
	}
	
	public Canvas getCanvas() { //Cr�ation de la m�thode "getCanvas" pour permettre � d'autres classes de dessiner dessus (sur le canvas)
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
