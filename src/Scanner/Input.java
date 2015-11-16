package Scanner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Input
{
	public BufferedReader createReader(String filepath) throws FileNotFoundException
	{
		InputStreamReader reader = null;
		
		reader = new InputStreamReader(new FileInputStream(filepath));
		BufferedReader bufReader = new BufferedReader(reader);
		return bufReader;
	}
}
