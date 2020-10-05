import java.io.IOException;

public class Tester {
	public static void main (String [] args) throws IOException{
		Encoder encoded = new Encoder ();
		encoded.encodeText("big.txt");
		
		Decoder decoded = new Decoder();
		decoded.decodeText("big.txt.lzw");
	}
}
