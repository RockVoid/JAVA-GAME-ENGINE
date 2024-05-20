package main;

import gameObjects.OBJ_key;

public class AssetSetter {
	GamePanel gp;
	
	AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setupGameObjects() {
		gp.gameObjects[0] = new OBJ_key();
		gp.gameObjects[0].worldX = 23 * gp.tileSize;
		gp.gameObjects[0].worldY = 7 * gp.tileSize;
	}
}
