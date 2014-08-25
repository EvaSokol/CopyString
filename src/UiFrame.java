import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UiFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout());
				
		for (int i = 0; i<10; i++)
			pan.add(new MyPanel(), BorderLayout.AFTER_LAST_LINE);
				
		frame.add(pan, FlowLayout.LEFT);
				
		frame.setVisible(true);
	}

}

class MyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JPanel pan = new JPanel();
	
	MyPanel() {
	
		pan.setBackground(Color.GREEN);
		pan.setSize(300, 200);
		pan.setLayout(new BorderLayout(5,5));
		pan.setBounds(10, 10, 200, 300);		
		
		JButton display = new JButton("Display");
		display.setSize(50, 20);
		pan.add(display, BorderLayout.EAST);
		JButton copyBtn = new JButton("Copy string");
		pan.add(copyBtn, BorderLayout.WEST);
		JButton partBtn = new JButton("Copy part");
		pan.add(partBtn, BorderLayout.CENTER);
		pan.setVisible(true);
		
		add(pan, BorderLayout.CENTER);
	}
	
}
