package Scanner;

import java.io.IOException;

import token.PatternMatcher;
import token.Token;
import token.TokenPosition;
import token.TokenType;

public class Scanner
{
	private Input input;
	private PatternMatcher matcher;

	private boolean isNext;
	private String tokenString;
	private String curentChar;
	private State state;
	private State stateOld;


	public Scanner(Input input, PatternMatcher matcher)
	{
		this.input = input;
		this.matcher = matcher;
		this.isNext = this.input.isNext();
		this.tokenString = "";
		this.curentChar = "";
		this.state = State.TOKENIZENOMATCH;
		this.stateOld = State.TOKENIZENOMATCH;
	}

	public boolean isNext()
	{
		return this.isNext;
	}

	public Token getNext() throws IOException
	{
		Token nextToken = null;

		while (true)
		{
			System.out.println(this.state + " : " + this.stateOld);
			if (this.input.isNext())
			{
				this.curentChar = input.getNext();
				
				this.updateState(this.tokenString + this.curentChar);
				
				if(this.state == State.TOKENIZENOMATCH && this.stateOld == State.TOKENIZEMATCH)
				{
					nextToken=createTokenFromTokenString(this.tokenString);
					this.tokenString = this.curentChar;
					this.updateState(this.tokenString);
					break;
				}
				else if(this.state == State.SKIPMATCH)
				{
					this.tokenString = "";
					this.curentChar = "";
				}
			}
			else
			{
				nextToken = this.createTokenFromTokenString("");
				this.isNext = false;
				break;
			}
		}
		return nextToken;
	}

	private Token createTokenFromTokenString(String tokenString)
	{
		TokenType type = this.matcher.getTokenType(tokenString);
		TokenPosition position = new TokenPosition(this.input.getFilePath(), this.input.getLinePosition());
		Token t = new Token(type, this.tokenString, position);
		return t;

	}
}
