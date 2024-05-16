package main;

import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// SCREEN SETTINGS
	final int originalTileSize = 16; // Set the size of character img
	final int scale = 3; // The scale factor
	
	// Set the size of character on screen - tile = "azulejo" = quadrado(our pixel size)
	public final int tileSize = scale * originalTileSize;
	// Set the rows and cols of the game
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	// Set the screen height, width and resolution
	public final int screenWidth = tileSize * maxScreenCol; // Col pixels = 768
	public final int screenHeight = tileSize * maxScreenRow; // Row pixels = 576

	// World settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldRow;
	public final int worldHeight = tileSize * maxWorldCol;
	
	
	Thread gameThread;
	KeyHandler keyH = new KeyHandler(); // Game manager keyHandler
	public Player player = new Player(this, keyH);
	TileManager tileManager = new TileManager(this);
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	int FPS = 60;
	
	public void update() {
		player.update();
	}
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double nextDrawInterval = System.nanoTime() + drawInterval; 
		
		while(gameThread != null) {
			update();
			repaint();
			
			try {
				double remainingTime = nextDrawInterval - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) { 
					remainingTime = 0; 
				}
				
				Thread.sleep((long) remainingTime);	
				
				nextDrawInterval += drawInterval;
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updated() {}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileManager.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
}
