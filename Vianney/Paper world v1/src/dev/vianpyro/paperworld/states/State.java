package dev.vianpyro.paperworld.states;

import java.awt.Graphics;

import dev.vianpyro.paperworld.Game;

public abstract class State {
	
	private static State currentState = null; //On d�finit l'�tat du jeu comme null par d�faut
	
	public static State getCurrentState() {
		return currentState;
	}
	public static void setCurrentState(State currentState) {
		State.currentState = currentState;
	}
	
	//Class
	protected Game game;
	
	public abstract void tick();
	public abstract void render(Graphics g); //Permission � la classe "State" de dessiner
	
	public State(Game game) {
		this.game = game;
	}
}
