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
		BufferedReader br = new BufferedReader (new FileReader(fileName));

		int readIn = Integer.parseInt(br.readLine());
		pw.print(table.get(readIn));
		int beginning = readIn;


		
		while (br.ready())
		{
		

			readIn = Integer.parseInt(br.readLine());
			if (readIn <table.size()) // if code read in is in dictionary
			{
				String entry = table.get(readIn);
				pw.print(entry);
				table.add(table.get(beginning) + entry.charAt(0) + "");
				beginning = table.indexOf(entry);
			}
			else //if code is not in dictionary
			{
				newEntry = table.get(beginning) + table.get(beginning).charAt(0)+ ""
				pw.print(newEntry);
				table.add(newEntry);
				beginning=table.indexOf(newEntry);
			}
		}
		
		
	}
	catch (IOException e)
	{
		System.out.println("error");
	}

}
}
