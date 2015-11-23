package token;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PatternMatcher
{
	private List<Pattern> startOfSkipPattern;
	private List<Pattern> totalSkipPattern;
	private List<Pattern> tokenTypePattern;
	
	public PatternMatcher()
	{
		this.initSkipPatternList();
		this.initTokenTypePatternList();
	}
	private void initSkipPatternList()
	{
		this.startOfSkipPattern = new ArrayList<Pattern>();
		this.totalSkipPattern = new ArrayList<Pattern>();
		for(SkipableType s : SkipableType.values())
		{
			this.startOfSkipPattern.add(s.getStartPattern());
			this.totalSkipPattern.add(s.getTotalPattern());
		}
	}
	private void initTokenTypePatternList()
	{
		this.tokenTypePattern = new ArrayList<Pattern>();
		for(TokenType t : TokenType.values())
		{
			if(t.getPattern() != null)
			{
				this.tokenTypePattern.add(t.getPattern());
			}
		}
	}
	
	private boolean matchesAnyOfPattern(List<Pattern> patterns, String input)
	{
		boolean matches = false;
		for(Pattern p : patterns)
		{
			if(p.matcher(input).matches())
			{
				matches = true;
				break;
			}
		}
		return matches;
	}
	public boolean isStartOfSkip(String input)
	{
		return this.matchesAnyOfPattern(this.startOfSkipPattern, input);
	}
	public boolean isEndOfSkip(String input)
	{
		return this.matchesAnyOfPattern(this.totalSkipPattern, input);
	}
	public boolean isMatching(String input)
	{
		return this.matchesAnyOfPattern(tokenTypePattern, input);
	}
	public TokenType getTokenType(String input)
	{
		TokenType type = TokenType.UNDEFINED;
		for(TokenType t : TokenType.values())
		{
			Pattern pattern = t.getPattern();
			if(pattern != null)
			{
				if(pattern.matcher(input).matches())
				{
					type = t;
					break;
				}
			}
		}
		return type;
	}
}
