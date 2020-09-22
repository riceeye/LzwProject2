import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		try {
		//make printwriter so we can print as we encode

		PrintWriter pw = new PrintWriter(new FileWriter(fileName  + ".decoded"));

		Scanner scan = new Scanner (new Filereader (fileName))
		
		int readIn = scan.nextInt();
		pw.print(table.get(readIn));
		int beginning = readIn;
		String beg = "";

		String newEntry = "";
		
		while (scan.hasNext())
		{
		

			readIn = scan.getNextInt();
			if (table.containsKey(readIn)) // if code read in is in dictionary
			{
				String entry = table.get(readIn);
				pw.print(entry);
				table.put(table.size(), beg + entry.charAt(0) + "");
				beg = entry;
			}
			else //if code is not in dictionary
			{
				newEntry = (table.get(beginning) + table.get(beginning).charAt(0)+ "");
				pw.print(newEntry);
				table.put(table.size(), newEntry);
				beg=newEntry + "";
			}
		}
		
		
	}
	catch (IOException e)
	{
		System.out.println("error");
	}

}
}
