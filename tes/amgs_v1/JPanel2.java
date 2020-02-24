import javax.swing.*;
import java.awt.*;

/* This panel contains highscore */

public class JPanel2 extends JPanel {
	
	// attributes
	int windowWidth;
	int windowHeight;
	//JPanel0 p0; //main container, necessary to remove p1 from p1
	
	// components
	JLabel l1 = new JLabel("HIGHSCORE");
	
	public JPanel2 (int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		setLayout(null);
		setBackground(new Color(150, 190, 150));
		
		// panel size (max window size)
		setBounds(0,0,windowWidth,windowHeight);
		
		// modify components
		l1.setBounds((int)(windowWidth/2)-(int)(600/2),(int)(windowHeight/4), 600, 30);
		l1.setBackground(new Color(140, 140, 160));
		l1.setForeground(new Color(220, 200, 200));
		
		// add components
		add(l1);
	}
	
}
