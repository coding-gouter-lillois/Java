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

public class Game implements Runnable { //"implements Runnable" pour permettre à cette classe de faire tourner un programme en boucle 
		
	public String title; //Initialisation de la variable du titre de la fenêtre
	
	private int width, height; //Initialisation des variables de hauteur et largeur de la fenêtre
	private Display display; //Création de l'objet d'affichage
	private Thread thread; //Création d'un mini programme autonome qui fonctionne séparemment de la calsse principale (main)
	private boolean running = false; //Initialisation de l'état de jeu comme arrêté
	private BufferStrategy bs; //Initialisation d'une fonction permettant de dire à l'ordinateur comment afficher des choses à l'écran, genre d' "écran invisible" qui permet d'éviter l'effet de clignotement
	private Graphics g; //Initialisation d'une fonction permettant de dessiner
	
	//États
	private State gameState;
	private State menuState;
	private State settingsState;
	
	//Camera
	private GameCamera gameCamera;
	
	//Inputs
	private KeyManager keyManager;
	
	public Game(String title, int width, int height) {
		this.width = width; //Définir la variable largeur de la méthode égale à celle de la classe
		this.height = height; //Définir la variable hauteur de la méthode égale à celle de la classe
		this.title = title; //Définir la variable du titre de la fenêtre de la méthode égale à celle de la classe
		keyManager = new KeyManager();
		initialization(); //Appelle la méthode d'initialisation du jeu
	}
	
	public void initialization() { //Création de la méthode d'initilaisation du jeu
		display = new Display(title, width, height); //Fonction qui appelle la construction de la fenêtre
		display.getFrame().addKeyListener(keyManager); //On donne l'accès au clavier à la fenêtre
		Assets.initialisation(); //Initialise les textures du jeu et tous les objets qui en ont une
		
		gameCamera = new GameCamera(this, 0, 0);
		gameState = new GameState(this);
		menuState = new MenuState(this);
		settingsState = new SettingsState(this);
		State.setCurrentState(gameState); //Défini l'état du jeu comme "en jeu"
	}

	public void run() { //Création de la méthode principal du jeu		
		
		final int maxFramesPerSecond = 60; //Création de la limite d'images par secondes affichées dans le jeu ; le nombre de fois qu'on veut lancer le code par seconde
		double timePerTick = 1000000000 / maxFramesPerSecond; //Définition du temps à attendre en nano secondes entre chaque execution du code
		double delta = 0; //Définition du temps avant le rappel des méthodes "tick" et "render"
		long now;
		long lastTime = System.nanoTime(); //Donne le nombre de nano secondes écoulées depuis le début du jeu
		long timer = 0; //Temps en nano secondes qui permet de savoir combien de temps s'est écoulé
		int ticks = 0; //Compteur d'images par secondes
		
		while(running) { //Création de la boucle de jeu
			now = System.nanoTime(); //Défini la valeur de maintenant égale au nombre de nano secondes écoulées depuis le début du jeu
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now; //Défini la valeur du dernier temps égale a maintenant car la dernière execution vient de se produire 
			
			if(delta >= 1) { //Quand delta est superieur ou égal à un, il faut executer le programme 
				tick(); //Execution de la méthode "tick"
				render(); //Execution de la méthode "render"
				ticks++;
				delta--; //On enlève un à delta pour ne pas rendre ce code innutile
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks : " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop(); //Arrêt au cas où running est faux
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
	
	private void tick() { //Création de la méthode "tick"
		keyManager.tick();

		if(State.getCurrentState() != null) {State.getCurrentState().tick();} //Si l'état acctuel du jeu est non-nul on execute la méthode "tick" de la classe "State"
	}
	
	private void render() { //Création de la méthode "render"
		bs = display.getCanvas().getBufferStrategy(); //Initialise le(s) "buffers"
		if(bs == null) { //Si il n'y a pas de "buffers"
			display.getCanvas().createBufferStrategy(3); //3 est le nombre de "buffers" maximum
			return;
		}
		
		g = bs.getDrawGraphics(); //Choix de l'emplacement où dessiner en initialisant "g" qui sera le "pinceau"
		g.clearRect(0, 0, width, height); //Efface tout ce qui est affiché sur la fenêtredu jeu
		//Draw under!
		
		if(State.getCurrentState() != null) {State.getCurrentState().render(g);} //Si l'état acctuel du jeu est non-nul on execute la méthode "render" de la classe "State"
		
		//End drawing!
		bs.show(); //Afficher le resultat du(es) dessin(s) à l'écran 
		g.dispose(); //Sert à arrêter à coup sûr le(s) dessin(s)
	}
	
	public synchronized void start() { //Création de la méthode synchronized (en lien directe avec un thread) de commencement du jeu
		if(running) {return;} //Ne rien faire si le jeu est déjà défini comme lancé
		running = true; //Définir le jeu comme lancé
		thread = new Thread(this); //Initialisation du mini programme autonome qui fonctionne séparemment de la calsse principale (main), de cette classe (this)
		thread.start(); //Activation du mini programme autonome qui fonctionne séparemment de la calsse principale (main) qui appelle la méthode "run"
	}
	
	public synchronized void stop() { //Création de la méthode synchronized (en lien directe avec un thread) d'arrêt du jeu
		if(!running) {return;} //Ne rien faire si le jeu est déjà arrêté
		running = false; //Définir le jeu commme arrêté
		try {
			thread.join(); //Arrête simplement le mini programme autonome qui fonctionne séparemment de la calsse principale (main)
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
	}
}
