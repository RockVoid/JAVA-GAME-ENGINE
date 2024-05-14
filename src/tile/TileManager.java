package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		
		getTileImage();
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int blockRow = 0;
		int blockCol = 0;
		int axisX = 0;
		int axisY = 0;
		
		while(blockCol < gp.maxScreenCol && blockRow < gp.maxScreenRow) {
			g2.drawImage(tile[0].image, axisX, axisY, gp.tileSize, gp.tileSize, null);
			blockCol++;
			axisX += gp.tileSize;
			
			if(blockCol == gp.maxScreenCol) {
				blockCol = 0;
				blockRow++;
				axisX = 0;
				axisY += gp.tileSize;
			}
		}
	}
}



















