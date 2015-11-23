package test.Scanner;

import org.junit.Test;

import Scanner.Input;
import Scanner.Scanner;
import token.PatternMatcher;
import token.Token;

public class TScanner
{
	@Test
	public void TestScanner()
	{
		try
		{
			Input input = new Input("data/test.jsst");
			PatternMatcher matcher = new PatternMatcher();
			Scanner scanner = new Scanner(input, matcher);
			
			while(scanner.isNext())
			{
				Token t = scanner.getNext();
				System.out.println(t.geTokenType() + "<" + t.getContent()+">");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
