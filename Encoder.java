import java.util.*;
import java.io.*;

public class Encoder {
	HashMap <String, Integer> map = new HashMap<String,Integer>();
	int mapSize = 128;
	int maxSize = 4096;
	ArrayDeque<Integer> recency = new ArrayDeque<Integer>();
	int[] freq = new int[4096];
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
				
				recency.add((int)map.get(code)); // add int to queue tracking recency
				freq[(int)map.get(code)]++; // add one to the frequency of the code in the queue
				
				if(mapSize < maxSize) { // check if map is full yet
					map.put(newcode, mapSize++);
				}
				else {
					int least = recency.poll(); // take first int from queue
					while(freq[least] != 1) { // check if it's the most recent iteration of that int
						freq[least]--; // if not, remove from queue and subtract from frequency
						least = recency.poll(); // get next int from queue to check
					}
					map.put(newcode, least); // if yes, replace that code with new one
					freq[least] = 0; // set frequency for the new code to 0 since it hasn't been used yet
				}
				code = c;
			}
		}
		pw.print((char)(int)map.get(code));
		br.close();
		pw.close();
	}
}