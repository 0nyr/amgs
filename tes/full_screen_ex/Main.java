import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

	static GraphicsDevice device = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getScreenDevices()[0];

	public static void main(String[] args) {

		final JFrame frame = new JFrame("Display Mode");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		
		// The JButtons to control the main window: frame
		JButton btn1 = new JButton("Full-Screen");
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				device.setFullScreenWindow(frame);
			}
		});
		JButton btn2 = new JButton("Normal");
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				device.setFullScreenWindow(null);
			}
		});
		JButton btn3 = new JButton("Exit");
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("*** Program exited ***");
				System.exit(0);
			}
		});

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		frame.add(panel);

		frame.pack();
		frame.setVisible(true);

	}
}
