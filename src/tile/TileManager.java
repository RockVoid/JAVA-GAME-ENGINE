package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMapFromFile();
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMapFromFile() {
		
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
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



















