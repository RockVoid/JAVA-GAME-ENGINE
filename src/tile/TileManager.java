package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	Tile[] gameTilesImages = {
			new Tile("/tiles/grass01.png", false),
			new Tile("/tiles/wall.png", true),
			new Tile("/tiles/water01.png", true),
			new Tile("/tiles/earth.png", false),
			new Tile("/tiles/tree.png", true),
			new Tile("/tiles/sand.png", false)
	};
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		fillTileWithImages(gameTilesImages);
		loadMapFromFile("/maps/world01.txt");
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
	
	public Tile[] fillTileWithImages(Tile[] gameImages) {
		for(int i = 0; i < gameImages.length; i++) {
			tile[i] = new Tile(gameImages[i].filePath, gameImages[i].collision);
			tile[i].image = getImage(gameImages[i].filePath);
		}
		return tile;
	}
	
	public void loadMapFromFile(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			InputStreamReader mapFile = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(mapFile);	
		
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
						
				while(col < gp.maxWorldCol) {
					String[] arrayOfStringNumbers = line.split(" ");
					int num = Integer.parseInt(arrayOfStringNumbers[col]);
	
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.maxWorldCol) {
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
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileBlockNumber = mapTileNum[worldCol][worldRow];
			
			// Mounting the map block schema - where the map must be projected?
			int worldX = worldCol * gp.tileSize; // Here
			int worldY = worldRow * gp.tileSize; // and here
			// Ups, the player moves, so...
			int screenX = worldX - gp.player.worldX + gp.player.middleScreenX; 
			int screenY = worldY - gp.player.worldY + gp.player.middleScreenY; 
			
			
			// As long as X grow or shrink...
			boolean leftLimitLoad = worldX + gp.tileSize > (gp.player.worldX - gp.player.middleScreenX);
			boolean rightLimitLoad = worldX - gp.tileSize < (gp.player.worldX + gp.player.middleScreenX);

			// As long as Y grow or shrink...
			boolean upLimitLoad = worldY + gp.tileSize > (gp.player.worldY - gp.player.middleScreenY);
			boolean downLimitLoad = worldY - gp.tileSize < (gp.player.worldY + gp.player.middleScreenY);
			
			if(leftLimitLoad && rightLimitLoad && upLimitLoad && downLimitLoad) {
				g2.drawImage(tile[tileBlockNumber].image, screenX, screenY, gp.tileSize, gp.tileSize, null);					
			}
			
			
			worldCol++;
			 
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}

