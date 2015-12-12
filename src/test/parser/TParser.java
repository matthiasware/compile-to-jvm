package test.parser;

import java.util.List;

import org.junit.Test;

import exceptions.IllegalSyntaxException;
import parser.Parser;
import scanner.Input;
import scanner.Scanner;
import token.PatternMatcher;
import token.Token;
import token.TokenPosition;

public class TParser
{
	@Test
	public void test()
	{
		try
		{
			Input input = new Input("data/test/testParserData.jsst");
			PatternMatcher matcher = new PatternMatcher();
			Scanner scanner = new Scanner(input, matcher);
			Parser parser = new Parser(scanner);
			parser.parse();
		}
		catch (IllegalSyntaxException e)
		{
			System.err.println("---------SYNTAXERROR-----------");
			String msg = e.getMsg();
			if(msg != null)
			{
				System.out.println(msg);
			}
			Token t = e.getToken();
			if (t != null)
			{
				TokenPosition p = t.getPosition();
				System.out.println("Expected: " + e.getExpected() + " but was: " + t.geTokenType());
				System.out.println("File: " + p.getFilePath());
				System.out.println("Line: " + p.getLine());
				System.out.println("Content: " + t.getContent());
			}
			e.printStackTrace();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
