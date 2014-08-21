import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;


public class FileReader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		InputStream in = new FileInputStream(new File ("test.txt"));
				
		ArrayList<String> Lines = new ArrayList<String>();
		Byte[] line = new Byte[1024];
		String newstr = "";
		
			int rb;
			
			while ((rb = in.read()) > 0) {
				System.out.print((char)rb);
				if (rb != '\n') newstr +=(char)rb;
					else { 
						Lines.add(newstr);
						System.out.println(newstr);
						newstr = "";
					}
			}
			System.out.println(Lines.get(0) + Lines.get(1) + Lines.get(2));
			in.close();
	}
}
