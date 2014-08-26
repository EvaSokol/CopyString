import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UiFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int StringCount = 20;
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 50*StringCount);
	
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(StringCount, 1));
				
		for (int i = 0; i<StringCount; i++)
			pan.add(new MyPanel(), BorderLayout.AFTER_LAST_LINE);

		frame.add(pan);
		
				
		frame.setVisible(true);
	}

}

class MyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JPanel pan = new JPanel();
	
	MyPanel() {
			
		JButton display = new JButton("Display01234567890Display");
		display.setSize(600, 20);
		display.setEnabled(false);
		pan.add(display);
		JButton copyBtn = new JButton("Copy string");
		pan.add(copyBtn);
		JButton partBtn = new JButton("Copy part");
		pan.add(partBtn);
		pan.setVisible(true);
		
		add(pan, BorderLayout.CENTER);
	}
	
}
