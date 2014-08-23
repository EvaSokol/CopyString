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
try (
		FileInputStream in = new FileInputStream(new File ("Chinese.txt"));
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);	)	{	
			
			ArrayList<String> Lines = new ArrayList<String>();
			String str = "";
							
			while ((str  = br.readLine()) != null) {
				Lines.add(str);
				System.out.println(str);	
				}
			
		}	
	}
}
