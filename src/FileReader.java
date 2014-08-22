import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class FileReader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		FileInputStream in = new FileInputStream(new File ("French.txt"));
		InputStreamReader isr = new InputStreamReader(in, "UTF-16");
		BufferedReader br = new BufferedReader(isr);
		
		ArrayList<String> Lines = new ArrayList<String>();
		String newstr = "";
		
			int rb;
			
			while ((rb = in.read()) > 0) {
				System.out.print((char)rb);
				if (rb != '\n') newstr +=(char)rb;
					else { 
						Lines.add(newstr);
					//	System.out.println(newstr);
						newstr = "";
					}
			}
		//	System.out.println(Lines.get(0) + Lines.get(1) + Lines.get(2));
			in.close();
	}
}
