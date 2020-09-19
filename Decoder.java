import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LZWDecoder {
	//make table that has the list of string and their value
	private ArrayList<String> table = new ArrayList<String>(); 
	
	public LZWDecoder ()
	{
	}
	
		
	public void decodeText(String fileName) throws FileNotFoundException
	{
		try {
		//make printwriter so we can print as we encode
		PrintWriter pw = new PrintWriter(new FileWriter(fileName  + "encoded"));
		Scanner reader = new Scanner (new File(fileName));
		
		for (int i=0; i<=127; i++)
		{
			table.add(""+(char)(i)); //inputs values into table that are already in the ascii table; began at 33rd character to avoid weird chars
		}
		int code1=0;
		code1 = reader.nextInt();
		
		pw.print(table.get(code1));
		int w = code1;
		
		while (reader.hasNext())
		{
			code1 = reader.nextInt();
			if (code1 <= 127) // if code read in is in dictionary
			{
				String entry = table.get(code1);
				pw.print(entry);
				table.add(table.get(w) + entry.charAt(0) + "");
				w = table.indexOf(entry);
			}
			else //if code is not in dictionary
			{
				
				pw.print(table.get(w) + table.get(w).charAt(0)+ "");
				table.add(table.get(w) + table.get(w).charAt(0)+ "");
				w=table.indexOf(table.get(w) + table.get(w).charAt(0)+ "");
			}
		}
		
		
	}
	catch (IOException e)
	{
		System.out.println("error");
	}

}
}
