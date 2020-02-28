import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

//FINAL
public class Bullet  {
	JPanel BulletAppearance = new JPanel();
	int [] Position = new int[2];
	int direction=0;
	
	
public Bullet(int direction, int[]Position) {
	this.direction=direction;
	System.out.println(this.direction);
	switch (this.direction) {
	
	case 0:
		this.Position[0]=Position[0];
		this.Position[1]=Position[1]-80;
		
		break;
		
	case 1:
		this.Position[0]=Position[0]+80;
		this.Position[1]=Position[1]-80;
		break;
		
	case 2:
		this.Position[0]=Position[0]+80;
		this.Position[1]=Position[1];
		
		break;
		
	case 3:
		this.Position[0]=Position[0]+80;
		this.Position[1]=Position[1]+80;
		
		break;
		
	case 4:
		this.Position[0]=Position[0];
		this.Position[1]=Position[1]+80;
	
		break;
	
	case 5:
		this.Position[0]=Position[0]-80;
		this.Position[1]=Position[1]+80;
		
		break;
		
	case 6:
		this.Position[0]=Position[0]-80;
		this.Position[1]=Position[1];
		break;
		
	case 7:
		this.Position[0]=Position[0]-80;
		this.Position[1]=Position[1]-80;
		
	break;
	
		
	}
	BulletAppearance.setBounds(	this.Position[0], this.Position[1], 80, 80);
	BulletAppearance.setBackground(Color.GREEN);
}

public void BulletTrajectory() {
switch (this.direction) {
	
	case 0:
		this.Position[0]=Position[0];
		this.Position[1]=Position[1]-80;
		
		break;
		
	case 1:
		this.Position[0]=Position[0]+80;
		this.Position[1]=Position[1]-80;
		break;
		
	case 2:
		this.Position[0]=Position[0]+80;
		this.Position[1]=Position[1];
		
		break;
		
	case 3:
		this.Position[0]=Position[0]+80;
		this.Position[1]=Position[1]+80;
		
		break;
		
	case 4:
		this.Position[0]=Position[0];
		this.Position[1]=Position[1]+80;
	
		break;
	
	case 5:
		this.Position[0]=Position[0]-80;
		this.Position[1]=Position[1]+80;
		
		break;
		
	case 6:
		this.Position[0]=Position[0]-80;
		this.Position[1]=Position[1];
		break;
		
	case 7:
		this.Position[0]=Position[0]-80;
		this.Position[1]=Position[1]-80;
		
	break;
	
}
}
}