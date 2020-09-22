import java.io.*;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Decoder {
	//make table that has the list of strings and their value
	private HashMap<Integer, String> table = new HashMap<Integer, String>(); 

	public Decoder ()
	{
		for (int i=0; i<=127; i++)
		{
			table.put(i, ""+(char)(i)); //inputs values into table that are already in the ascii table
		}
	}

	public void decodeText(String fileName) throws IOException
	{
		//make printwriter so we can print as we encode
		PrintWriter pw = new PrintWriter(new FileWriter(fileName  + ".decoded"));
		
		//make scanner to scan in code
		Scanner scan = new Scanner (new File (fileName));

		int readIn = scan.nextInt(); // takes in the very beginning of the code, variable readIn represents what's just been read in from code
		pw.print(table.get(readIn)); // printing first string 
		String pastEntry = table.get(readIn); // update the past entry with the current

		String specialEntry = ""; // used for when the code is not in the dictionary

		while (scan.hasNext())
		{
			readIn = scan.nextInt(); // read in next code (so next number between space)
			if (table.containsKey(readIn)) // if code read in is in dictionary
			{
				String currentEntry = table.get(readIn); // string value of most recent code
				pw.print(currentEntry); 
				table.put(table.size(), pastEntry + currentEntry.charAt(0) + ""); // add to end of table the previous string and the first char of current code
				pastEntry = currentEntry; // update value of past entry with current code
			}
			else //if code is not in dictionary
			{
				specialEntry = (pastEntry + pastEntry.charAt(0)+ ""); // special case: entry of readIn is the past entry + its first char
				pw.print(specialEntry); //print the current entry
				table.put(table.size(), specialEntry); // add entry to table
				pastEntry=specialEntry + ""; // update value of past entry
			}
		}
		scan.close();
		pw.close();	//save
	}
}
