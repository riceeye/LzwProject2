import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Decoder {
	//make table that has the list of string and their value
	private ArrayList<String> table = new ArrayList<String>(); 
	
	public Decoder ()
	{
	}
	
		
	public void decodeText(String fileName) throws IOException
	{
		try {
		//make printwriter so we can print as we encode
		PrintWriter pw = new PrintWriter(new FileWriter(fileName  + "decoded"));
		// make reader to read in integers
		Scanner reader = new Scanner (new File(fileName));
		
		for (int i=0; i<=127; i++)
		{
			table.add(""+(char)(i)); //inputs values into table that are already in the ascii table; began at 33rd character to avoid weird chars
		}

		
		int readIn = reader.nextInt(); // new number
		
		pw.print(table.get(readIn)); // prints very first char
		int beginning = readIn; // beginning is the previous code
		
		while (reader.hasNext()==true)
		{
			
			readIn = reader.nextInt(); // read in next number
			if (readIn <table.size()) // if code read in is in dictionary
			{
				String entry = table.get(readIn); // find value of new code and print
				pw.print(entry); 
				table.add(table.get(beginning) + entry.charAt(0) + ""); // add to table the char of the last code + first letter of current code
				beginning = table.indexOf(entry); // current code becomes previous
			}
			else //if code is not in dictionary
			{
				
				pw.print(table.get(beginning) + table.get(beginning).charAt(0)+ ""); // print the value or chars of previous code + first letter of new code
				table.add(table.get(beginning) + table.get(beginning).charAt(0)+ "");// add what you printed to table
				beginning=table.indexOf(table.get(beginning) + table.get(beginning).charAt(0)+ ""); // this printed value becomes the previous code
			}
		}
		
		
	}
	catch (IOException e)
	{
		System.out.println("error");
	}

}
}
