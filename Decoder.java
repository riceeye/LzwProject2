import java.io.*;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Decoder {
	//make table that has the list of strings and their value
	private HashMap<Integer, String> table = new HashMap<Integer, String>(); 
	int maxSize = 4096;
	LinkedList<Integer> recency = new LinkedList<Integer>();
	public Decoder ()
	{
		for (int i=0; i<128; i++)
		{
			table.put(i, ""+(char)(i)); //inputs values into table that are already in the ascii table
			recency.addFirst(i); // add starting hashmap to linkedlist
		}
	}

	public void decodeText(String fileName) throws IOException
	{
		//make printwriter so we can print as we encode
		PrintWriter pw = new PrintWriter(new FileWriter(fileName  + ".decoded"));
		
		//make scanner to scan in code
		BufferedReader scan = new BufferedReader (new FileReader (fileName));

		int readIn = (int)scan.read(); // takes in the very beginning of the code, variable readIn represents what's just been read in from code	
		pw.print(table.get(readIn)); // printing first string 
		recency.remove(readIn);
		recency.addFirst(readIn);
		String pastEntry = table.get(readIn); // update the past entry with the current
		String specialEntry = ""; // used for when the code is not in the dictionary

		while (scan.ready())
		{
			readIn = (int)scan.read(); // read in next code (so next number between space)
			if (table.containsKey(readIn)) // if code read in is in dictionary
			{
				String currentEntry = table.get(readIn); // string value of most recent code
				pw.print(currentEntry); 
				recency.remove(readIn);
				recency.addFirst(readIn); // update recency list
				
				
				if(table.size() < maxSize) {
					recency.addFirst(table.size()); // add new code to recency list
					table.put(table.size(), pastEntry + currentEntry.charAt(0) + ""); // add to end of table the previous string and the first char of current code
				}
				else {
					int remove = recency.removeLast(); // find least recently used code
					while(remove < 128) {
						recency.addFirst(remove); // in case least recent is char, move back to beginning
						remove = recency.removeLast(); // get oldest integer
					}
					table.remove(remove);
					table.put(remove,pastEntry + currentEntry.charAt(0) + ""); // replace old value with new one
					recency.addFirst(remove); // update recency list
				}
				pastEntry = currentEntry; // update value of past entry with current code
			}
			else //if code is not in dictionary
			{
				specialEntry = (pastEntry + pastEntry.charAt(0)+ ""); // special case: entry of readIn is the past entry + its first char
				pw.print(specialEntry); //print the current entry
				if(table.size() < maxSize) {
					recency.addFirst(table.size());
					table.put(table.size(), specialEntry); // add entry to table
				}
				else {
					int remove = recency.removeLast(); // find least recently used code
					while(remove < 128) {
						recency.addFirst(remove); // in case least recent is char, move back to beginning
						remove = recency.removeLast(); // get oldest integer
					}
					table.remove(remove);
					table.put(remove,specialEntry); // replace old value with new one
					recency.addFirst(remove); // update recency list
				}
				pastEntry=specialEntry + ""; // update value of past entry
			}
		}
		scan.close();
		pw.close();	//save
	}
}
