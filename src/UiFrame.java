import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class UiFrame extends JFrame{
	
	final JFrame frame;
	static File[] catalog;
	static File currentFile;
	static UiFrame genFrame;
	static ArrayList<String> list;
	static int listSize;
	JPanel comboPanel;
	JPanel strPan;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		catalog = FileReader.getListOfFiles(".");
	//	currentFile = new File("french.txt");
		currentFile = catalog[0];
		genFrame = new UiFrame();
	
	}

	private UiFrame(){
				
		frame = new JFrame();
		frameInit(frame);
	
		final JPanel mainPanel = new JPanel();
				
		final JPanel comboPanel = new JPanel();
		
		JComboBox<String> combo = new JComboBox<String>(FileReader.FileNames(catalog));
		ActionListener ActListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> box = (JComboBox<String>)e.getSource();
				currentFile = new File((String)box.getSelectedItem());
				try {
					list = FileReader.getListOfStrings(currentFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				frameInit(frame);
				mainPanel.remove(strPan);								
				strPan = new StringsPanel(comboPanel);
								
				mainPanel.add(strPan, BorderLayout.AFTER_LAST_LINE);
								
				frame.add(mainPanel);
				frame.setVisible(true);
							
			System.out.println((String)box.getSelectedItem());
			System.out.println("Current File: " + currentFile);
			
			frame.repaint();
			
			}
		};
		combo.addActionListener(ActListener);
		comboPanel.add(combo);
		
		strPan = new StringsPanel(comboPanel);
	
		mainPanel.add(strPan, BorderLayout.AFTER_LAST_LINE);

		mainPanel.scrollRectToVisible(frame.getBounds());
		
		frame.add(mainPanel);
						
		frame.setVisible(true);
		
	}

	private void frameInit(JFrame frame) {
		
		try {
			list = FileReader.getListOfStrings(currentFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		listSize = list.size();
		int maxString = FileReader.getMaxString(list);
		int width = maxString * 11 + 300;
		int height = listSize*80;
		frame.setSize(width, height);
		

		
	}
}

class StringsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JPanel strPanel = new JPanel();

	StringsPanel(JPanel comboPanel) {
		
		strPanel.setLayout(new GridLayout(UiFrame.listSize + 1, 1));
		
		strPanel.add(comboPanel);
		
		for (String str : UiFrame.list)
			strPanel.add(new MyPanel(str));
		
		strPanel.setVisible(true);
		add(strPanel);
		
	}
}

class MyPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JPanel pan = new JPanel();
	
	MyPanel(String str) {
			
		JLabel display = new JLabel(str);
		display.setForeground(Color.BLACK);
		Font font = new Font(null, 0, 20); 
		display.setFont(font);
		pan.add(display);
		
		CopyListener listener = new CopyListener(str);
		
		JButton copyBtn = new JButton("Copy string");
		copyBtn.addActionListener(listener);
		pan.add(copyBtn);
		
		JButton partBtn = new JButton("Copy part");
		partBtn.addActionListener(listener);
		pan.add(partBtn);
		
		pan.setVisible(true);
		add(pan, BorderLayout.CENTER);
				
	}
	
}

class CopyListener implements ActionListener {

	String str;
	
	CopyListener(String string) {
		str = string;
	}
		
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().compareTo("Copy string") == 0) {
			String cbst = toBuffer(str);
			System.out.println("from buffer: " + cbst);
		}
		else {
			
			String result = "";
			for (int i=0; i< 5; i++) {
				result += str.toCharArray()[(int)(Math.random()*str.length())];
						}
			System.out.println("Result: " + result);
		};
	}

	private String toBuffer(String string) throws HeadlessException {
		
		StringSelection ss = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
				 
		  String cbst = "";
		try {
			cbst = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).getTransferData(Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null).getTransferDataFlavors()[0]);
		} catch (HeadlessException | UnsupportedFlavorException
				| IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return cbst;
	}
	
}
