package Scanner;

import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.hamcrest.core.IsSame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;

import token.PatternMatcher;
import token.Token;
import token.TokenType;

public class Scanner
{
	private BufferedReader reader;
	private boolean isNextToken;
	private PatternMatcher pmatcher;
	
	private String tokenString = "";
	private boolean matching = false;
	private String current = "";

	public Scanner(BufferedReader reader, PatternMatcher pmatcher)
	{
		this.reader = reader;
		this.isNextToken = true;
		this.pmatcher = pmatcher;
	}

	public boolean isNextToken()
	{
		return this.isNextToken;
	}

	public Token getNextToken() throws IOException
	{
		Token token = null;
		while (true)
		{
			int currentCharAsInt = this.reader.read();
			if (currentCharAsInt == -1)
			{
				TokenType ttype = this.pmatcher.getTokenType(this.tokenString);
				this.isNextToken = false;
				token = new Token(ttype, this.tokenString, null);
				break;
			}
			else
			{
				this.current = String.valueOf((char) currentCharAsInt);
				if (!this.pmatcher.isMatching(this.tokenString + this.current) && this.matching)
				{
					TokenType ttype = this.pmatcher.getTokenType(this.tokenString);
					token= new Token(ttype, this.tokenString, null);
					this.tokenString = this.current;
					this.matching = this.pmatcher.isMatching(this.tokenString);
					break;
				}
				else
				{
					this.tokenString += this.current;
					this.matching = this.pmatcher.isMatching(tokenString);
				}
			}
		}
		return token;
	}

	public static void main(String args[])
	{
		Input input = new Input();
		BufferedReader reader;
		try
		{
			reader = input.createReader("data/test.jsst");
			PatternMatcher pmatcher = new PatternMatcher();
			Scanner scanner = new Scanner(reader, pmatcher);
			while(scanner.isNextToken)
			{
				Token t = scanner.getNextToken();
				if(!t.geTokenType().equals(TokenType.WHITESPACE))
				{
					System.out.printf("%-15s> %-25s \n",t.geTokenType().toString(),t.getContent());
				}
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
