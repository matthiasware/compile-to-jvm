package Scanner;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;

public class Scanner
{
	public static void main(String args[])
	{
		Input input = new Input();
		BufferedReader reader;
		try
		{
			reader = input.createReader("src/Scanner/Scanner.java");
			int charInt;
			while((charInt = reader.read()) != -1)
			{
				
				char charac = (char) charInt;
				System.out.print(charac +" ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	// Detect Encodeing
	// only read the chars u want, ignore rest
	// Error rutines!
	// Error on line x
	// How to remember position?
	// Encoding?
}
