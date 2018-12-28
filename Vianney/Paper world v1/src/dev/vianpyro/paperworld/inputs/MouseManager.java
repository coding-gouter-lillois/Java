package dev.vianpyro.paperworld.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

	public boolean leftPressed, rightPressed, middlePressed;
	private int mouseX, mouseY;
	
	public MouseManager() {
		
	}
	
	//Getters
	
	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public boolean isMiddlePressed() {
		return middlePressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
	
	//Méthodes d'implémentation
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
		} else if(e.getButton() == MouseEvent.BUTTON2) {
			rightPressed = true;
		} else if(e.getButton() == MouseEvent.BUTTON3) {
			middlePressed = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = false;
		} else if(e.getButton() == MouseEvent.BUTTON2) {
			rightPressed = false;
		} else if(e.getButton() == MouseEvent.BUTTON3) {
			middlePressed = false;
		}
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
}
