package token;

public class TokenPosition
{
	private String filePath;
	private int line;
	
	public TokenPosition(String filePath, int line)
	{
		this.filePath = filePath;
		this.line = line;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public int getLine()
	{
		return line;
	}
}
