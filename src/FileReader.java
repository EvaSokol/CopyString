import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileReader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		ArrayList<String> Lines;
		
		Lines = getList(new File ("Chinese.txt"));	
		System.out.println("There are " + Lines.size() + " strings, max length is " + getMaxString(Lines));
		}

	public static ArrayList<String> getList(File file) throws IOException,
			FileNotFoundException, UnsupportedEncodingException {
			
			ArrayList<String> Lines;
		
			try (
				FileInputStream in = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(in, "UTF-8");
				BufferedReader br = new BufferedReader(isr);	)	{	
					
					Lines = new ArrayList<String>();
					String str = "";
									
					while ((str  = br.readLine()) != null) {
						if (str.length() > 0) { 
							Lines.add(str);
							System.out.println(str);
							}
						
						}
					
				}
		return Lines;
	}
	
	static int getMaxString(ArrayList<String> list) {
		int max = 0;
		for (String string : list) {
			if (string.length() > max) 
				max = string.length();
		}
		
		return max;
	}
	
	
}
