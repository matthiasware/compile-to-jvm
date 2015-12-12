package scanner;

import java.io.IOException;
import java.util.logging.Logger;

import token.PatternMatcher;
import token.Token;
import token.TokenPosition;
import token.TokenType;

import org.apache.commons.lang3.StringEscapeUtils;

import logger.LoggerBuilder;;

public class Scanner
{
	private static final Logger log = LoggerBuilder.getLogger(Scanner.class.getName());
	
	private Input input;
	private PatternMatcher matcher;

	private boolean isNext;
	private String tokenString;

	public Scanner(Input input, PatternMatcher matcher)
	{
		this.input = input;
		this.matcher = matcher;
		this.isNext = this.input.isNext();
		this.tokenString = "";
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
			if (this.input.isNext())
			{
				String current = input.getNext();
				
				if (matcher.isSkipable(tokenString + current))
				{
					log.info("SKIP <"+ StringEscapeUtils.escapeJava(this.tokenString+current) +">");
					this.tokenString = "";
				}
				else if (matcher.isStartOfSkipable(tokenString + current))
				{
						this.tokenString = tokenString+current;
						this.skipUntilMatches();
				}
				else if (matcher.isMatching(this.tokenString) && !matcher.isMatching(this.tokenString + current))
				{
					nextToken = createTokenFromTokenString(this.tokenString);
					this.tokenString = current;
					if(matcher.isSkipable(this.tokenString))
					{
						this.tokenString = "";
					}
					break;
				}
				else
				{
					this.tokenString += current;
				}
			}
			else
			{
				nextToken = this.createTokenFromTokenString(this.tokenString);
				this.isNext = false;
				break;
			}
		}
		return nextToken;
	}

	private void skipUntilMatches() throws IOException
	{
		while (true)
		{
			if (this.input.isNext())
			{
				String currentChar = input.getNext();
				
				if (matcher.isSkipable(this.tokenString+currentChar))
				{
					log.info("SKIP <"+ StringEscapeUtils.escapeJava(this.tokenString+currentChar) +">");
					this.tokenString = "";
					break;
				}
				else
				{
					this.tokenString +=currentChar;
				}
			}
			else
			{
				break;
			}
		}
	}
	private Token createTokenFromTokenString(String s)
	{
		TokenType type = this.matcher.getTokenType(s);
		TokenPosition position = new TokenPosition(this.input.getFilePath(), this.input.getLinePosition());
		Token t = new Token(type, s, position);
		log.info("CREATE "+t.geTokenType()+"<"+ t.getContent()+">");
		return t;

	}
}
