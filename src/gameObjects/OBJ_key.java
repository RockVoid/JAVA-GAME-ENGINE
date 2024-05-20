package gameObjects;

import utils.Utils;

public class OBJ_key extends SuperObject {
	
	String filePath = "/objects/key.png";
	Utils util = new Utils();
	
	public OBJ_key() {
		name = "Key";
		image = util.getImage(filePath);
	}
}
