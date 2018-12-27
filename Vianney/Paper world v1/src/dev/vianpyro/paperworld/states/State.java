package dev.vianpyro.paperworld.states;

import java.awt.Graphics;

import dev.vianpyro.paperworld.Handler;

public abstract class State {
	
	private static State currentState = null; //On définit l'état du jeu comme null par défaut
	
	public static State getCurrentState() {
		return currentState;
	}
	public static void setCurrentState(State currentState) {
		State.currentState = currentState;
	}
	
	//Class
	protected Handler handler;
	
	public abstract void tick();
	public abstract void render(Graphics g); //Permission à la classe "State" de dessiner
	
	public State(Handler handler) {
		this.handler = handler;
	}
}
