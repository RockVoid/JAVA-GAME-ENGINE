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
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImages();
	}
	
	public void setDefaultValues() {
		y = 100;
		x = 100;
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
	
	public void update() {
		if(keyH.rightPressed == true) { 
			x += speed; 
			direction = "right";
		}
		if(keyH.leftPressed == true) { 
			x -= speed; 
			direction = "left";
		}
		if(keyH.upPressed == true) { 
			y -= speed; 
			direction = "up";
		}
		if(keyH.downPressed == true) { 
			y += speed; 
			direction = "down";
		}
		
		spriteCounter++;
		if(spriteCounter == 12) {
			spriteNum = !spriteNum;
			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
			case "up":
				if(spriteNum) {
					image = up1;
					break;
				}
				image = up2;
				break;
			case "down":
				if(spriteNum) {
					image = down1;
					break;
				}
				image = down2;
				break;
			case "right":
				if(spriteNum) {
					image = right1;
					break;
				}
				image = right2;
				break;
			case "left":
				if(spriteNum) {
					image = left1;
					break;
				}
				image = left2;
				break;
		}
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
