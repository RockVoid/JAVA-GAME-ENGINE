package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	// The position of entity on the map
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public int spriteCounter;
	public boolean spriteNum;
	
	// Collision
	public Rectangle solidArea;
	public boolean collisionOn = false;
}
