import javax.swing.*;
import java.awt.*;

/* Game background panel */

@SuppressWarnings("serial")
public class JPanel0 extends JPanel {
	
	// attributes
	int windowWidth;
	int windowHeight;
	
	public JPanel0 (int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		setLayout(null);
		setBackground(new Color(150, 150, 170));
		
		// panel size (max window size)
		setBounds(0,0,windowWidth, windowHeight);
	}
	
}
