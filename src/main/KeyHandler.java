package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		setMovementValue(code, true);
	}
	
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		setMovementValue(code, false);
	}
	
	public void setMovementValue(int code, boolean move) {
		if(code == KeyEvent.VK_W) {
			upPressed = move;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = move;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = move;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = move;
		}
	}
}
