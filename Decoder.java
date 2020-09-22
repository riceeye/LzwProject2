import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Decoder {
	//make table that has the list of strings and their value
	private ArrayList<String> table = new ArrayList<String>(); 
	
	public Decoder ()
	{
		for (int i=0; i<=127; i++)
		{
			table.add(""+(char)(i)); //inputs values into table that are already in the ascii table
		}
	}
	
	public void decodeText(String fileName) throws IOException
	{
		try {
		//make printwriter so we can print as we encode
		PrintWriter pw = new PrintWriter(new FileWriter(fileName  + ".decoded"));
		
		//Scanner reader = new Scanner (new File(fileName)); replaced with a buffered reader
		BufferedReader br = new BufferedReader (new FileReader(fileName));		
		
		
		
		

		//makes a string to hold the number (the numbers are separated by spaces, so this variable holds the number between them) and adds chars until the number/code is complete
			String code = "";

		pw.print(table.get(0)); // prints very first char
		
		
		while (br.ready())
		{
			int i = br.read();
			while (i != 32) {
				code+=(""+ (char)i);
				br.read();
			}
			
			int num = Integer.parseInt(code);
			int beginning = num; // beginning is the previous code
			if (table.contains(code)) // if code read in is in dictionary
			{
				String entry = table.get(num); // find value of new code and print
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
