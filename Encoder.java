import java.util.*;
import java.io.*;

public class Encoder {
	HashMap <String, Integer> map = new HashMap<String,Integer>();
	int mapSize = 128;
	public Encoder(){
		//initializes the array with 0-127 from ascii table
		for (int i = 0; i < 128; i++) {
			map.put("" + (char)i, i);
		}
	}

	public void encodeText(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName)); 
		PrintWriter pw = new PrintWriter (new FileWriter (new File (fileName +".lzw")));
		String code = "" + (char)br.read();
		
		while (br.ready()) {
			String last = "" + (char)(br.read());
			String pattern = "" + code + last;
			
			if (map.containsKey(pattern)) {
				code = pattern;
			}
					
			else {
				map.put(pattern, mapSize++);
			}
			pw.print(map.get(code) + " ");
			code = last;
		}
		
		br.close();
		pw.close();
	}
}