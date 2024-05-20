package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
	
		// Window close when "X"'s clicked 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("NeyGame!");
		
		GamePanel gamePanel = new GamePanel();
		
		window.add(gamePanel);
		window.pack();
		
		// Put the game on the center of screen
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.setupObjects();
		gamePanel.startGameThread();
	}
}
