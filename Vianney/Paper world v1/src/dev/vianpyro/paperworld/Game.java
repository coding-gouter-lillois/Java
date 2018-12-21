package dev.vianpyro.paperworld;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.vianpyro.paperworld.display.Display;
import dev.vianpyro.paperworld.graphics.Assets;
import dev.vianpyro.paperworld.graphics.GameCamera;
import dev.vianpyro.paperworld.inputs.KeyManager;
import dev.vianpyro.paperworld.states.GameState;
import dev.vianpyro.paperworld.states.MenuState;
import dev.vianpyro.paperworld.states.SettingsState;
import dev.vianpyro.paperworld.states.State;

public class Game implements Runnable { //"implements Runnable" pour permettre � cette classe de faire tourner un programme en boucle 
		
	public String title; //Initialisation de la variable du titre de la fen�tre
	
	private int width, height; //Initialisation des variables de hauteur et largeur de la fen�tre
	private Display display; //Cr�ation de l'objet d'affichage
	private Thread thread; //Cr�ation d'un mini programme autonome qui fonctionne s�paremment de la calsse principale (main)
	private boolean running = false; //Initialisation de l'�tat de jeu comme arr�t�
	private BufferStrategy bs; //Initialisation d'une fonction permettant de dire � l'ordinateur comment afficher des choses � l'�cran, genre d' "�cran invisible" qui permet d'�viter l'effet de clignotement
	private Graphics g; //Initialisation d'une fonction permettant de dessiner
	
	//�tats
	private State gameState;
	private State menuState;
	private State settingsState;
	
	//Camera
	private GameCamera gameCamera;
	
	//Inputs
	private KeyManager keyManager;
	
	public Game(String title, int width, int height) {
		this.width = width; //D�finir la variable largeur de la m�thode �gale � celle de la classe
		this.height = height; //D�finir la variable hauteur de la m�thode �gale � celle de la classe
		this.title = title; //D�finir la variable du titre de la fen�tre de la m�thode �gale � celle de la classe
		keyManager = new KeyManager();
		initialization(); //Appelle la m�thode d'initialisation du jeu
	}
	
	public void initialization() { //Cr�ation de la m�thode d'initilaisation du jeu
		display = new Display(title, width, height); //Fonction qui appelle la construction de la fen�tre
		display.getFrame().addKeyListener(keyManager); //On donne l'acc�s au clavier � la fen�tre
		Assets.initialisation(); //Initialise les textures du jeu et tous les objets qui en ont une
		
		gameCamera = new GameCamera(this, 0, 0);
		gameState = new GameState(this);
		menuState = new MenuState(this);
		settingsState = new SettingsState(this);
		State.setCurrentState(gameState); //D�fini l'�tat du jeu comme "en jeu"
	}

	public void run() { //Cr�ation de la m�thode principal du jeu		
		
		final int maxFramesPerSecond = 60; //Cr�ation de la limite d'images par secondes affich�es dans le jeu ; le nombre de fois qu'on veut lancer le code par seconde
		double timePerTick = 1000000000 / maxFramesPerSecond; //D�finition du temps � attendre en nano secondes entre chaque execution du code
		double delta = 0; //D�finition du temps avant le rappel des m�thodes "tick" et "render"
		long now;
		long lastTime = System.nanoTime(); //Donne le nombre de nano secondes �coul�es depuis le d�but du jeu
		long timer = 0; //Temps en nano secondes qui permet de savoir combien de temps s'est �coul�
		int ticks = 0; //Compteur d'images par secondes
		
		while(running) { //Cr�ation de la boucle de jeu
			now = System.nanoTime(); //D�fini la valeur de maintenant �gale au nombre de nano secondes �coul�es depuis le d�but du jeu
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now; //D�fini la valeur du dernier temps �gale a maintenant car la derni�re execution vient de se produire 
			
			if(delta >= 1) { //Quand delta est superieur ou �gal � un, il faut executer le programme 
				tick(); //Execution de la m�thode "tick"
				render(); //Execution de la m�thode "render"
				ticks++;
				delta--; //On enl�ve un � delta pour ne pas rendre ce code innutile
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks : " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop(); //Arr�t au cas o� running est faux
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void tick() { //Cr�ation de la m�thode "tick"
		keyManager.tick();

		if(State.getCurrentState() != null) {State.getCurrentState().tick();} //Si l'�tat acctuel du jeu est non-nul on execute la m�thode "tick" de la classe "State"
	}
	
	private void render() { //Cr�ation de la m�thode "render"
		bs = display.getCanvas().getBufferStrategy(); //Initialise le(s) "buffers"
		if(bs == null) { //Si il n'y a pas de "buffers"
			display.getCanvas().createBufferStrategy(3); //3 est le nombre de "buffers" maximum
			return;
		}
		
		g = bs.getDrawGraphics(); //Choix de l'emplacement o� dessiner en initialisant "g" qui sera le "pinceau"
		g.clearRect(0, 0, width, height); //Efface tout ce qui est affich� sur la fen�tredu jeu
		//Draw under!
		
		if(State.getCurrentState() != null) {State.getCurrentState().render(g);} //Si l'�tat acctuel du jeu est non-nul on execute la m�thode "render" de la classe "State"
		
		//End drawing!
		bs.show(); //Afficher le resultat du(es) dessin(s) � l'�cran 
		g.dispose(); //Sert � arr�ter � coup s�r le(s) dessin(s)
	}
	
	public synchronized void start() { //Cr�ation de la m�thode synchronized (en lien directe avec un thread) de commencement du jeu
		if(running) {return;} //Ne rien faire si le jeu est d�j� d�fini comme lanc�
		running = true; //D�finir le jeu comme lanc�
		thread = new Thread(this); //Initialisation du mini programme autonome qui fonctionne s�paremment de la calsse principale (main), de cette classe (this)
		thread.start(); //Activation du mini programme autonome qui fonctionne s�paremment de la calsse principale (main) qui appelle la m�thode "run"
	}
	
	public synchronized void stop() { //Cr�ation de la m�thode synchronized (en lien directe avec un thread) d'arr�t du jeu
		if(!running) {return;} //Ne rien faire si le jeu est d�j� arr�t�
		running = false; //D�finir le jeu commme arr�t�
		try {
			thread.join(); //Arr�te simplement le mini programme autonome qui fonctionne s�paremment de la calsse principale (main)
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
	}
}
