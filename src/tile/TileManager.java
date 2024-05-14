package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	String[] gameTilesImages = {
			"/tiles/grass01.png",
			"/tiles/wall.png",
			"/tiles/water01.png",
			"/tiles/earth.png",
			"/tiles/tree.png",
			"/tiles/sand.png"
	};
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		fillTileWithImages(gameTilesImages);
		loadMapFromFile("/maps/map01.txt");
	}
	
	public BufferedImage getImage(String filePath) {
		try {
			BufferedImage img = ImageIO.read(getClass().getResourceAsStream(filePath));
			return img;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Tile[] fillTileWithImages(String[] gameImages) {
		for(int i = 0; i < gameImages.length; i++) {
			tile[i] = new Tile();
			tile[i].image = getImage(gameImages[i]);
		}
		/*** 
		 * Maybe we need add one by one...	
		 * if(tile.length == 0) {
			tile[0] = new Tile();
			tile[0].image = getImage(filePath);
			return tile;
		}**/
		return tile;
	}
	
	public void loadMapFromFile(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			InputStreamReader mapFile = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(mapFile);	
		
			int col = 0;
			int row = 0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
						
				while(col < gp.maxScreenCol) {
					String[] arrayOfStringNumbers = line.split(" ");
					int num = Integer.parseInt(arrayOfStringNumbers[col]);
	
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
			
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
			
			int tileBlockNumber = mapTileNum[blockCol][blockRow];
			g2.drawImage(tile[tileBlockNumber].image, axisX, axisY, gp.tileSize, gp.tileSize, null);
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



















