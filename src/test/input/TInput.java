package test.input;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Scanner.Input;

public class TInput
{
	private static Input input;
	private static String filePath = "data/test.jsst";

	@Before
	public void init()
	{
		try
		{
			this.input = new Input(filePath);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test()
	{
		try
		{
			while (this.input.isNext())
			{
				this.input.getNext();
				//System.out.println(input.getLinePosition() + ": " + input.getNext());
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
