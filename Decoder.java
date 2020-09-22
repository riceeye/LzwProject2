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

		PrintWriter pw = new PrintWriter(new FileWriter(fileName  + "decoded"));
		Scanner reader = new Scanner (new File(fileName));

		int readIn = reader.nextInt();
		
		pw.print(table.get(readIn));
		int beginning = readIn;

		

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
			

			readIn = reader.nextInt();
			if (readIn <table.size()) // if code read in is in dictionary
			{
				String entry = table.get(readIn);
				pw.print(entry);
				table.add(table.get(beginning) + entry.charAt(0) + "");
				beginning = table.indexOf(entry);

			}
			else //if code is not in dictionary
			{
				
				pw.print(table.get(beginning) + table.get(beginning).charAt(0)+ "");
				table.add(table.get(beginning) + table.get(beginning).charAt(0)+ "");
				beginning=table.indexOf(table.get(beginning) + table.get(beginning).charAt(0)+ "");
			}
		}
		
		
	}
	catch (IOException e)
	{
		System.out.println("error");
	}

}
}
