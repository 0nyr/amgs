package amgs.input;

import java.awt.event.*;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean exit;
	public boolean fullscreen, normalscreen;
	
	public KeyManager() {
		keys = new boolean[256]; // linked to keycode
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_Z] || keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_Q] || keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		
		exit = keys[KeyEvent.VK_ESCAPE];

		fullscreen = keys[KeyEvent.VK_F11];
		normalscreen = keys[KeyEvent.VK_F12];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}


