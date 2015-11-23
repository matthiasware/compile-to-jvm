package Scanner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input
{
	private String filePath;
	private BufferedReader reader;
	private int linePosition;
	private boolean isNext;
	private StringBuffer buffer;
	
	public Input(String filePath) throws IOException
	{
		this.filePath = filePath;
		this.reader=initReader(filePath);
		this.buffer = new StringBuffer();
		this.readFirstLine();
	}
	private void readFirstLine() throws IOException
	{
		this.linePosition = 0;
		this.isNext=true;
		this.readNextLine();
	}
	private BufferedReader initReader(String filePath) throws FileNotFoundException
	{
		return new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
	}
	private void readNextLine() throws IOException
	{
		String nextLine = this.reader.readLine();
		if(nextLine == null)
		{
			this.isNext = false;
		}
		else
		{
			this.linePosition++;
			this.buffer.append(nextLine);
		}
	}
	public String getNext() throws IOException
	{
		if(this.buffer.length() == 1)
		{
			readNextLine();
		}
		String nextChar = String.valueOf(this.buffer.charAt(0));
		this.buffer.deleteCharAt(0);
		return nextChar;
	}
	public int getLinePosition()
	{
		return this.linePosition;
	}
	public boolean isNext()
	{
		return this.isNext;
	}
	public String getFilePath()
	{
		return this.filePath;
	}
}
