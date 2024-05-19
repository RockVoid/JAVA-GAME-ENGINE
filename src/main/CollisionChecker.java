package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	CollisionChecker(GamePanel gp) {
		this.gp = gp;
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
			solidAreaLeftCol = (solidAreaLeftSide - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileManager.mapTileNum[solidAreaLeftCol][solidAreaTopRow];
			tileNum2 = gp.tileManager.mapTileNum[solidAreaRightCol][solidAreaTopRow];
			
			if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		}
	}
}
