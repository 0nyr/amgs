package amgs.input;

import amgs.*;

import java.awt.event.*;

public class KeyManager implements KeyListener {
	
	private boolean[] justPressed, cantPress;
	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean aUp, aDown, aLeft, aRight, closeCombatAtt;
	public boolean exit;
	public boolean fullscreen, normalscreen;
	
	public KeyManager() {
		keys = new boolean[256]; // linked to keycode
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
	}
	
	public void tick() {
		// movement direction
		up = keys[KeyEvent.VK_Z];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_Q];
		right = keys[KeyEvent.VK_D];

		// close combat attack 
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];
		//closeCombatAtt = keys[KeyEvent.VK_SPACE];
		
		exit = keys[KeyEvent.VK_ESCAPE];

		fullscreen = keys[KeyEvent.VK_F11];
		normalscreen = keys[KeyEvent.VK_F12];

		// key just pressed
		for(int i = 0;i < keys.length;i++) {
			if(cantPress[i] && !keys[i]){
				cantPress[i] = false;
			}else if(justPressed[i]){
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]){
				justPressed[i] = true;
			}
		}
		testExampleHowToUseJustPressed();
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
			return;
		}
		keys[e.getKeyCode()] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
			return;
		}
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}

	public boolean[] getJustPressed() {
		return justPressed;
	}

	private void testExampleHowToUseJustPressed() {
		if(Constants.isTest) {
			if(keyJustPressed(KeyEvent.VK_E)) {
				System.out.println("E JUST PRESSED");
			}
		}
	}
	
}


