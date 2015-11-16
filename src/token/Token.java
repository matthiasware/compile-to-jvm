package token;

public class Token
{
	private final TokenType type;
	private final String content;
	private final TokenPosition position;

	public Token(TokenType type, String content, TokenPosition position)
	{
		this.type = type;
		this.content = content;
		this.position = position;
	}
	public TokenType geTokenType()
	{
		return this.type;
	}
	public String getContent()
	{
		return content;
	}
	public TokenPosition getPosition()
	{
		return position;
	}
}
