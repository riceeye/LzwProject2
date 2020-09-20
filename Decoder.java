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
		Scanner reader = new Scanner (new File(fileName));
		
		for (int i=0; i<=127; i++)
		{
			table.add(""+(char)(i)); //inputs values into table that are already in the ascii table; began at 33rd character to avoid weird chars
		}

		
		int code1 = reader.nextInt();
		
		pw.print(table.get(code1));
		int beginning = code1;
		
		while (reader.hasNext()==true)
		{
			
			code1 = reader.nextInt();
			if (code1 <table.size()) // if code read in is in dictionary
			{
				String entry = table.get(code1);
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
