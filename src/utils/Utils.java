package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
	public BufferedImage getImage(String filePath) {
		try {
			BufferedImage img = ImageIO.read(getClass().getResourceAsStream(filePath));
			return img;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
