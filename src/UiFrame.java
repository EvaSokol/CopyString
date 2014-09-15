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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UiFrame {

	static File[] catalog;
	static File currentFile;
	static UiFrame genFrame;
	static ArrayList<String> list;
	static int listSize;
	JPanel strPan;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		catalog = FileReader.getListOfFiles(".");
		currentFile = new File("French.txt");
		list = FileReader.getList(currentFile);
		genFrame = new UiFrame();
	
	}

	private UiFrame() {
		
		listSize = list.size();
		int max = FileReader.getMaxString(list);
		
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(max+600, listSize*65);
	
		final JPanel mainPanel = new JPanel();
		strPan = new StringsPanel();
		
		JComboBox<String> combo = new JComboBox(FileReader.FileNames(catalog));
		ActionListener ActListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> box = (JComboBox<String>)e.getSource();
				currentFile = new File((String)box.getSelectedItem());
				try {
					list = FileReader.getList(currentFile);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					list = FileReader.getList(currentFile);
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				strPan = new StringsPanel();
				try {
					UiFrame.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			System.out.println((String)box.getSelectedItem());
			System.out.println("Current File: " + currentFile);
			frame.repaint();
			System.out.println("Current File: " + currentFile);
			}
		};
		combo.addActionListener(ActListener);
		
		mainPanel.add(combo, BorderLayout.AFTER_LAST_LINE);
				
		mainPanel.add(strPan, BorderLayout.AFTER_LAST_LINE);
		frame.add(mainPanel);
		
		frame.setVisible(true);
		
	}
}

class StringsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JPanel strPanel = new JPanel();

	StringsPanel() {
		
		strPanel.setLayout(new GridLayout(UiFrame.listSize, 1));
				
		for (String str : UiFrame.list)
			strPanel.add(new MyPanel(str), BorderLayout.AFTER_LAST_LINE);
		
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
