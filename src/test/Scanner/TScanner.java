package test.Scanner;

import java.util.ArrayList;
import java.util.List;

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
			
			List<Token> tl = new ArrayList<Token>();
			
			while(scanner.isNext())
			{
				Token t = scanner.getNext();
				tl.add(t);
				System.out.println(t.geTokenType() + "<" + t.getContent()+">");
			}
			for(Token t : tl)
			{
				//System.out.println(t.geTokenType() + "<" + t.getContent()+">");
			}
			//System.out.println(matcher.isStartOfSkip("/*"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
