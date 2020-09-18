import java.io.*;
import java.util.Scanner;

public class Decoder {
	
	public Decoder (String filename) throws FileNotFoundException
	{
		Scanner reader = new Scanner (new File(filename));
		reader.nextInt();
		
	}

}
