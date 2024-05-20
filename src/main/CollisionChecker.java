package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void verifyCollision(Entity entity, int tileNum1, int tileNum2) {
		if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
			entity.collisionOn = true;
		}
	}
	
	public void checkTile(Entity entity) {
		// solidArea = Solid Area Coordinates
		int solidAreaLeftSide = entity.worldX + entity.solidArea.x;
		int solidAreaRightSide = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int solidAreaTopSide = entity.worldY + entity.solidArea.y;
		int solidAreaBottomSide = entity.worldY + entity.solidArea.y + entity.solidArea.height;

		// Get the col and row numbers
		int solidAreaLeftCol = solidAreaLeftSide/gp.tileSize;
		int solidAreaRightCol = solidAreaRightSide/gp.tileSize;
		int solidAreaTopRow = solidAreaTopSide/gp.tileSize;
		int solidAreaBottomRow = solidAreaBottomSide/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			// When the entity goes up, the axis Y decreases,
			// so to predict where player will be, we decrease the point.
			solidAreaTopRow = (solidAreaTopSide - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[solidAreaLeftCol][solidAreaTopRow];
			tileNum2 = gp.tileManager.mapTileNum[solidAreaRightCol][solidAreaTopRow];			
			
			verifyCollision(entity, tileNum1, tileNum2);
			break;
		case "down":
			solidAreaBottomRow = (solidAreaBottomSide + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[solidAreaLeftCol][solidAreaBottomRow];
			tileNum2 = gp.tileManager.mapTileNum[solidAreaRightCol][solidAreaBottomRow];
			
			verifyCollision(entity, tileNum1, tileNum2);
			break;
		case "right":
			solidAreaRightCol = (solidAreaRightSide + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[solidAreaRightCol][solidAreaTopRow];
			tileNum2 = gp.tileManager.mapTileNum[solidAreaRightCol][solidAreaBottomRow];
			
			verifyCollision(entity, tileNum1, tileNum2);
			break;
		case "left":
			solidAreaLeftCol = (solidAreaLeftSide - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[solidAreaLeftCol][solidAreaTopRow];
			tileNum2 = gp.tileManager.mapTileNum[solidAreaLeftCol][solidAreaBottomRow];
			
			verifyCollision(entity, tileNum1, tileNum2);
			break;
		}
	}
}
