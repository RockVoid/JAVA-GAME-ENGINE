package tile;

import java.awt.image.BufferedImage;

public class Tile {
	BufferedImage image;
	public boolean collision = false;
	String filePath;
	
	public Tile(String filePath, boolean collision) {
		this.filePath = filePath;
		this.collision = collision;
	}
}
