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
		String code = "";
		
		while (br.ready()) {
			String last = "" + (char)(br.read());
			String pattern = "" + code + last;
			
			if (map.containsKey(pattern) || pattern.length()==1) {
				code = pattern;
			}
					
			else {
				if (code.length()==1)
				{
					pw.print(pattern.charAt(0))
				}
				else
				{
				pw.print(map.get(last);
				}
			}
			
			code = last;
		}
		if (prefix.length() == 1)
		{
			pw.print(last.charAt(0));
		}
		else
		{
			pw.print(map.get(last));
		}
		
		br.close();
		pw.close();
	}
}