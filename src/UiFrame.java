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
import javax.swing.JList;
import javax.swing.JPanel;

public class UiFrame {

	static File[] catalog;
	static File currentFile = new File("French.txt");
	static UiFrame genFrame;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		catalog = FileReader.getListOfFiles(".");
		genFrame = new UiFrame(FileReader.getList(currentFile));
		
	}

	private UiFrame(ArrayList<String> list) {
		
		int listSize = list.size();
		int max = FileReader.getMaxString(list);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(max+600, listSize*65);
	
		JPanel pan = new JPanel();
		
		JComboBox<String> combo = new JComboBox(FileReader.FileNames(catalog));
		ActionListener ActListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> box = (JComboBox<String>)e.getSource();
				currentFile = new File((String)box.getSelectedItem());
//				try {
//					genFrame = new UiFrame(FileReader.getList(new File((String)box.getSelectedItem())));
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (UnsupportedEncodingException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			System.out.println((String)box.getSelectedItem());
			}
		};
		combo.addActionListener(ActListener);
		
		pan.add(combo);
		pan.setLayout(new GridLayout(listSize+1, 1));
				
		for (String str : list)
			pan.add(new MyPanel(str), BorderLayout.AFTER_LAST_LINE);

		frame.add(pan);
		frame.setVisible(true);
		
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
		
		CopyAction listener = new CopyAction(str);
		
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

class CopyAction implements ActionListener {

	String str;
	
	CopyAction(String string) {
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
