import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class FileReader {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		InputStream in = new InputStream("test.txt");
		Reader Source = new InputStreamReader(in);
		
		BufferedReader br = new BufferedReader(Source);
		
		byte[] Line = new byte[1024];
		
			String str = br.readLine();
		
			buf.read(Line);
			
			for (int i=0; i<18; i++) {
				System.out.print((char)Line[i]);
				if ((char)Line[i] == '\n') System.out.println("end of string: " + i);
				if ((int)Line[i] < 0) System.out.println("eof: " + i);
//				if ((char)Line[i] == 'i') {
//					System.out.println();
//					System.out.println("i= " + i);
//				}
							}
			
			buf.close();
	}
}
