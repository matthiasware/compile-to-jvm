package token;
import java.util.regex.Pattern;
public enum TokenType
{	
	CLASS("class"),
	FINAL("final"),
	PUBLIC("public"),
	VOID("void"),
	INT("int"),
	IF("if"),
	ELSE("else"),
	WHILE("while"),
	RETURN("return"),
	NEW("new"),
	
	LCURLYBRACK("\\{"),
	RCURLYBRACK("\\}"),
	
	EQUALS("="),
	SEMICOLON(";"),
	COMMA(","),
	
	LPARANTHESE("\\("),
	RPARANTHESE("\\)"),
	
	LBRACK("\\["),
	RBRACK("\\]"),
	
	LOGICALOPERATOR("=="),
	LOGICALLESS("<"),
	LOGICALGREATER(">"),
	LOGICALLESSEQ("<="),
	LOGICALGREATEREQ(">="),
	
	
	OPERATORMULT("\\*"),
	OPERATORDIV("/"),
	OPERATORADD("\\+"),
	OPERATORMIN("-"),
	
	NUMBER("[1-9][\\d]*"),
	IDENT("[a-zA-Z][\\w]+"),

	UNDEFINED;
	
	
	private final Pattern pattern;
	private TokenType(String pattern)
	{
	      this.pattern = Pattern.compile(pattern);
	}
	private TokenType()
	{
		this.pattern=null;
	}
	public Pattern getPattern()
	{
		return this.pattern;
	}
}
