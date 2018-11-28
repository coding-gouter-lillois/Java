package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		//Affichage et options de la fenêtre
		JFrame frame = new JFrame("Snake");
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
