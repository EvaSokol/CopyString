import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;


public class FileReader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		InputStream in = new FileInputStream(new File ("French.txt"));
				
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
