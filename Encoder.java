import java.util.*;
import java.io.*;

public class Encoder {
	HashMap <String, Integer> map = new HashMap<String,Integer>();
	int mapSize = 128;
	int maxSize = 4096;
	public Encoder(){
		//initializes the array with 0-127 from ascii table
		for (int i = 0; i < 128; i++) {
			map.put("" + (char)i, i);
		}
	}

	public void encodeText(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName)); 
		PrintWriter pw = new PrintWriter (new FileWriter (new File (fileName +".lzw")));
		String code = "";
		
		while (br.ready()) {
			String c = "" + (char)(br.read()); //reads in new char as variable 'c'
			String newcode = "" + code + c; //appends c onto code
			
			if (map.containsKey(newcode)) {
				code = newcode;
			}
					
			else {
				pw.print((char)(int)map.get(code));
				if(mapSize < maxSize) {
					map.put(newcode, mapSize++);
				}
				else {
					// ???
				}
				code = c;
			}
		}
		pw.print((char)(int)map.get(code));
		br.close();
		pw.close();
	}
}