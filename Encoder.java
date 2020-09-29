import java.util.*;
import java.io.*;

public class Encoder {
	HashMap <String, Integer> map = new HashMap<String,Integer>();
	int mapSize = 256;
	int maxSize = 4096;
	LinkedList<String> recency = new LinkedList<String>();
	
	public Encoder(){
		//initializes the array with 0-127 from ascii table
		for (int i = 0; i < 256; i++) {
			map.put("" + (char)i, i);
			recency.addFirst(""+(char)i); // add starting hashmap to linkedlist
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
				
				if(mapSize < maxSize) { // check if map is full yet
					map.put(newcode, mapSize++);
				}
				else {
					String remove = recency.removeLast(); // get oldest string
					System.out.println(remove);
					int replace = map.get(remove); // get code for that string 
					while(replace < 256) {
						remove = recency.removeLast(); // get oldest string
						replace = map.get(remove); // get code for that string 
					}
					map.remove(remove);
					map.put(newcode, replace); // replace old key-value pair with new one
				}
				
				recency.remove(code); // move code to front of list, add newcode to list
				recency.addFirst(code);
				recency.addFirst(newcode);
				code = c; // reset string
			}
		}
		pw.print((char)(int)map.get(code));
		br.close();
		pw.close();
	}
}