package main;

import gameObjects.GameObject;

public class AssetSetter {
	GamePanel gp;
	
	AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setupGameObjects() {
		gp.gameObjects[0] = new GameObject("key", "/objects/key.png");
		gp.gameObjects[0].worldX = 23 * gp.tileSize;
		gp.gameObjects[0].worldY = 7 * gp.tileSize;
	}
}
