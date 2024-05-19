package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public final int middleScreenY;
	public final int middleScreenX;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		middleScreenY = (gp.screenHeight / 2) - (gp.tileSize/2);
		middleScreenX = (gp.screenWidth / 2) - (gp.tileSize/2);
		
		setDefaultValues();
		getPlayerImages();
	}
	
	public void setDefaultValues() {
		int initialPlayerPositionY = 21;
		int initialPlayerPositionX = 23;
		
		worldY = gp.tileSize * initialPlayerPositionY;
		worldX = gp.tileSize * initialPlayerPositionX;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImages() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/br_boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/br_boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/br_boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/br_boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/br_boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/br_boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/br_boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/br_boy_right_2.png"));
		
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void movePlayer() {
		int framesPassed = 12;
		
		spriteCounter++;
		if(spriteCounter == framesPassed) {
			spriteNum = !spriteNum;
			spriteCounter = 0;
		}
	}
	// 2REFACTOR
	public void update() {		
		if(keyH.rightPressed) { 
			worldX += speed; 
			direction = "right";
			movePlayer();
		}
		if(keyH.leftPressed) { 
			worldX -= speed; 
			direction = "left";
			movePlayer();
		}
		if(keyH.upPressed) { 
			worldY -= speed; 
			direction = "up";
			movePlayer();
		}
		if(keyH.downPressed) { 
			worldY += speed; 
			direction = "down";
			movePlayer();
		} 
	}
	
	public BufferedImage setDirectionImage(BufferedImage movement, BufferedImage patternImg1, BufferedImage patternImg2) {
		if(spriteNum) {
			movement = patternImg1;
			return movement;
		}
		movement = patternImg2;
		return movement;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
			case "up":
				image = setDirectionImage(image, up1, up2);
				break;
			case "down":
				image = setDirectionImage(image, down1, down2);
				break;
			case "right":
				image = setDirectionImage(image, right1, right2);
				break;
			case "left":
				image = setDirectionImage(image, left1, left2);
				break;
		}
		
		g2.drawImage(image, middleScreenX, middleScreenY, gp.tileSize, gp.tileSize, null);
	}
}
