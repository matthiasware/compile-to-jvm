package exceptions;

import token.Token;
import token.TokenType;

public class IllegalSyntaxException extends RuntimeException
{
	private Token actual;
	private TokenType expected;
	private String msg;
	
	public IllegalSyntaxException(Token t, TokenType tt, String msg)
	{
		this(msg);
		this.actual = t;
		this.expected = tt;
	}
	public IllegalSyntaxException(Token t, TokenType tt)
	{
		this(t,tt, null);
	}
	public IllegalSyntaxException(String msg)
	{
		this.msg = msg;
	}
	public IllegalSyntaxException(Token t, String msg)
	{
		this.msg = msg;
		this.actual = t;
	}

	public Token getToken()
	{
		return actual;
	}

	public String getMsg()
	{
		return msg;
	}
	public TokenType getExpected()
	{
		return expected;
	}
}
