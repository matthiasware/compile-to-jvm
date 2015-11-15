package token;
import java.util.regex.Pattern;
public enum TokenType
{	
	CLASS(Pattern.compile("class",Pattern.CASE_INSENSITIVE)),
	FINAL(Pattern.compile("final",Pattern.CASE_INSENSITIVE)),
	PUBLIC(Pattern.compile("public",Pattern.CASE_INSENSITIVE)),
	VOID(Pattern.compile("void",Pattern.CASE_INSENSITIVE)),
	INT(Pattern.compile("int",Pattern.CASE_INSENSITIVE)),
	IF(Pattern.compile("if",Pattern.CASE_INSENSITIVE)),
	ELSE(Pattern.compile("else",Pattern.CASE_INSENSITIVE)),
	WHILE(Pattern.compile("while",Pattern.CASE_INSENSITIVE)),
	RETURN(Pattern.compile("return",Pattern.CASE_INSENSITIVE)),
	
	LCURLYBRACK(Pattern.compile("\\{")),
	RCURLYBRACK(Pattern.compile("\\}")),
	
	EQUALS(Pattern.compile("=")),
	SEMICOLON(Pattern.compile(";")),
	COMMA(Pattern.compile(",")),
	
	LPARANTHESE(Pattern.compile("\\(")),
	RPARANTHESE(Pattern.compile("\\)")),
	
	LRACK(Pattern.compile("\\[")),
	RBRACK(Pattern.compile("\\]")),
	
	LOGICALOPERATOR(Pattern.compile("==")),
	LOGICALLESS(Pattern.compile("<")),
	LOGICALGREATER(Pattern.compile(">")),
	LOGICALLESSEQ(Pattern.compile("<=")),
	LOGICALGREATEREQ(Pattern.compile(">=")),
	
	
	OPERATORMULT(Pattern.compile("\\*")),
	OPERATORDIV(Pattern.compile("/")),
	OPERATORADD(Pattern.compile("\\+")),
	OPERATORMIN(Pattern.compile("-")),
	
	NUMBER(Pattern.compile("[1-9][\\d]*")),
	IDENT(Pattern.compile("[a-zA-Z][\\w]+")),
	
	SHORTCOMMENT(Pattern.compile("//.*\\n")),
	LONGCOMMENT(Pattern.compile("/\\*(.|[\\r\\n])*\\*/")),
	WHITESPACE(Pattern.compile("\\s+"));
	
	private final Pattern pattern;
	private TokenType(Pattern pattern)
	{
	      this.pattern = pattern;
	}
	public Pattern getPattern()
	{
		return this.pattern;
	}
}
