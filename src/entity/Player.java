package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
		
		solidArea = new Rectangle();
//		solidArea.x = middleScreenX + 8;
//		solidArea.y = middleScreenY + 16;
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
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
	// BTW, SORRY BY THE AMOUNT OF IFS, NEEDS REFACTOR HEHE
	public void update() {		
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		if(keyH.rightPressed) {  
			direction = "right";
			movePlayer();
		}
		if(keyH.leftPressed) { 
			direction = "left";
			movePlayer();
		}
		if(keyH.upPressed) { 
			direction = "up";
			movePlayer();
		}
		if(keyH.downPressed) { 
			direction = "down";
			movePlayer();
		} 

		if(keyH.upPressed && !collisionOn) { 
			worldY -= speed; 
		}
		
		if(keyH.rightPressed && !collisionOn) { 
			worldX += speed; 
		}
		if(keyH.leftPressed && !collisionOn) { 
			worldX -= speed; 
		}
		if(keyH.downPressed && !collisionOn) { 
			worldY += speed; 
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
