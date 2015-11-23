package Scanner;

import token.PatternMatcher;

public class ScannerStateMachine
{
	private PatternMatcher matcher;
	private ScannerStates state;
	
	public ScannerStateMachine(PatternMatcher matcher)
	{
		this.matcher = matcher;
		this.state = ScannerStates.TOKENIZENOMATCH;
	}
	public ScannerStates getState()
	{
		return this.state;
	}
	public void setState(ScannerStates state)
	{
		this.state = state;
	}
	public ScannerStates updateScannerStates(String input)
	{	
		//System.out.print(this.state + " --(\""+ input +"\")---> ");
		switch (this.state)
		{
		case SKIPMATCH:
			if (this.matcher.isEndOfSkip(input))
			{
				this.state = ScannerStates.SKIPMATCH;
			}
			else if(this.matcher.isStartOfSkip(input))
			{
				this.state = ScannerStates.SKIPNOMATCH;
			}
			else if (this.matcher.isMatching(input))
			{
				this.state = ScannerStates.TOKENIZEMATCH;
			}
			else
			{
				this.state = ScannerStates.TOKENIZENOMATCH;
			}
			break;
		case SKIPNOMATCH:
			if (this.matcher.isEndOfSkip(input))
			{
				this.state = ScannerStates.SKIPMATCH;
			}
			else
			{
				this.state = ScannerStates.SKIPNOMATCH;
			}
			break;
		case TOKENIZEMATCH:
			if (this.matcher.isStartOfSkip(input))
			{
				this.state = ScannerStates.SKIPNOMATCH;
			}
			else if (!this.matcher.isMatching(input))
			{
				this.state = ScannerStates.TOKENIZENOMATCH;
			}
			else
			{
				this.state = state.TOKENIZEMATCH;
			}
			break;
		case TOKENIZENOMATCH:
			if (this.matcher.isEndOfSkip(input))
			{
				this.state = ScannerStates.SKIPMATCH;
			}
			else if (this.matcher.isStartOfSkip(input))
			{
				this.state = ScannerStates.SKIPNOMATCH;
			}
			else if (this.matcher.isMatching(input))
			{
				this.state = ScannerStates.TOKENIZEMATCH;
			}
			else
			{
				this.state = ScannerStates.TOKENIZENOMATCH;
			}
			break;
		}
		//System.out.println(this.state);
		return this.state;
	}
}
