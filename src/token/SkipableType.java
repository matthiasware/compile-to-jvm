package token;
import java.util.regex.Pattern;

public enum SkipableType 
{
	SHORTCOMMENT("//","//.*\\n"),
	LONGCOMMENT("/\\*","/\\*(.|[\\r\\n])*\\*/"),
	WHITESPACE("\\s","\\s+");
	
	private final Pattern startPattern;
	private final Pattern totalPattern;
	
	private SkipableType(String startPattern, String totalPatter)
	{
		this.startPattern = Pattern.compile(startPattern);
		this.totalPattern = Pattern.compile(totalPatter);
	}
	public Pattern getStartPattern()
	{
		return this.startPattern;
	}
	public Pattern getTotalPattern()
	{
		return this.totalPattern;
	}
}
