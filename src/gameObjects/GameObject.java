package gameObjects;

import utils.Utils;

public class GameObject extends SuperObject {
	
	public String name, filePath;
	Utils util = new Utils();
	
	public GameObject(String name, String filePath) {
		System.out.println(filePath);
		this.name = name;
		image = util.getImage(filePath);
	}
}
