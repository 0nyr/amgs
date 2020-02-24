import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* panel title screen */

@SuppressWarnings("serial")
public class JPanel1 extends JPanel implements ActionListener {
	
	// attributes
	int windowWidth;
	int windowHeight;
	//JPanel0 p0; //main container, necessary to remove p1 from p1
	
	// components
	JButton b1 = new JButton("1 PLAYER");
	JButton b2 = new JButton("2 PLAYERS");
	JButton b3 = new JButton("HIGHSCORE");
	JButton b4 = new JButton("CREDITS");
	JLabel l1 = new JLabel("A gamed developped for 2nd Year computer science project 2020 (INSA FIMI SCAN)");
	
	// global variables
	GlobalVariables g1;
	
	public JPanel1 (int windowWidth, int windowHeight, GlobalVariables g1) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.g1 = g1;
		setLayout(null);
		setBackground(new Color(120,120,150));
		
		// panel size (max window size)
		setBounds(0,0,windowWidth,windowHeight);
		
		// declare the components
		int widthButtons = 100;
		int heightButtons = 30;
		int xPositionButtons = (int)(windowWidth/2)-(int)(widthButtons/2);
		int yPositionButtonsInit = 2*(int)(windowHeight/3);
		b1.setBounds(xPositionButtons, yPositionButtonsInit, widthButtons, heightButtons);
		b2.setBounds(xPositionButtons, yPositionButtonsInit+40, widthButtons, heightButtons);
		b3.setBounds(xPositionButtons, yPositionButtonsInit+40*2, widthButtons, heightButtons);
		b4.setBounds(xPositionButtons, yPositionButtonsInit+40*3, widthButtons, heightButtons);
		l1.setBounds((int)(windowWidth/2)-(int)(600/2),2*(int)(windowHeight/3)-40, 600, 30);
		l1.setBackground(new Color(140, 140, 160));
		l1.setForeground(new Color(220, 200, 200));
		
		// add action listeners for the buttons
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		// add the components to the panel
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(l1);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==b3) {
			// make disappear the panel
			// this.removeAll();
			//g1.currentPanel = 2; BAD PRACTICE
			g1.setCurrentPanel(2);
			setBounds(0,0,0,0);	//reduce the current panel to an invisible point
			repaint();
			System.out.println("g1.currentPanel = "+g1.getCurrentPanel());
		}
	}
	
}
