package token;

import java.util.regex.Pattern;

public class PatternMatcher
{
	public boolean isMatching(String input)
	{
		boolean isMatching = false;
		for(TokenType type : TokenType.values())
		{
			Pattern pattern = type.getPattern();
			if(pattern != null)
			{
				if(pattern.matcher(input).matches())
				{
					isMatching = true;
					break;
				}
			}
		}
		return isMatching;
	}
	public TokenType getTokenType(String input)
	{
		TokenType ttype = null;
		for(TokenType type : TokenType.values())
		{
			Pattern pattern = type.getPattern();
			if(pattern != null)
			{
				if(pattern.matcher(input).matches())
				{
					ttype = type;
					break;
				}
			}
			if(ttype == null)
			{
				ttype = TokenType.UNDEFINED;
			}
		}
		return ttype;
	}
}
