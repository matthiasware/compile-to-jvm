package test.Scanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Scanner.Input;
import Scanner.Scanner;
import Scanner.ScannerStateMachine;
import Scanner.ScannerStates;
import token.PatternMatcher;
import token.Token;
import static java.lang.System.out;

public class TScannerStates
{
	private static ScannerStateMachine ssm;
	
	@Before
	public void init()
	{
		PatternMatcher matcher = new PatternMatcher();
		this.ssm = new ScannerStateMachine(matcher);
	}
	
	@Test
	public void testTransitionsFromTOKENIZENOMATCH()
	{
		ssm.setState(ScannerStates.TOKENIZENOMATCH);
		ScannerStates s = ssm.updateScannerStates(" ");
		Assert.assertEquals(ScannerStates.SKIPMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZENOMATCH);
		s = ssm.updateScannerStates("\t");
		Assert.assertEquals(ScannerStates.SKIPMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZENOMATCH);
		s = ssm.updateScannerStates("\t\n");
		Assert.assertEquals(ScannerStates.SKIPMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZENOMATCH);
		s = ssm.updateScannerStates("\n");
		Assert.assertEquals(ScannerStates.SKIPMATCH, s);
		
		
		ssm.setState(ScannerStates.TOKENIZENOMATCH);
		s = ssm.updateScannerStates("/*");
		Assert.assertEquals(ScannerStates.SKIPNOMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZENOMATCH);
		s = ssm.updateScannerStates("//");
		Assert.assertEquals(ScannerStates.SKIPNOMATCH, s);
		
		
		ssm.setState(ScannerStates.TOKENIZENOMATCH);
		s = ssm.updateScannerStates("as");
		Assert.assertEquals(ScannerStates.TOKENIZEMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZENOMATCH);
		s = ssm.updateScannerStates("154");
		Assert.assertEquals(ScannerStates.TOKENIZEMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZENOMATCH);
		s = ssm.updateScannerStates("00");
		Assert.assertEquals(ScannerStates.TOKENIZENOMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZENOMATCH);
		s = ssm.updateScannerStates("-78");
		Assert.assertEquals(ScannerStates.TOKENIZENOMATCH, s);
	}
	
	@Test
	public void testTransitionsFromTOKENIZEMATCH()
	{
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		ScannerStates s = ssm.updateScannerStates("abc");
		Assert.assertEquals(ScannerStates.TOKENIZEMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		s = ssm.updateScannerStates("asdcsad00151");
		Assert.assertEquals(ScannerStates.TOKENIZEMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		s = ssm.updateScannerStates("publicI");
		Assert.assertEquals(ScannerStates.TOKENIZEMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		s = ssm.updateScannerStates("/");
		Assert.assertEquals(ScannerStates.TOKENIZEMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		s = ssm.updateScannerStates("//");
		Assert.assertEquals(ScannerStates.SKIPNOMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		s = ssm.updateScannerStates("/*");
		Assert.assertEquals(ScannerStates.SKIPNOMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		s = ssm.updateScannerStates("int ");
		Assert.assertEquals(ScannerStates.TOKENIZENOMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		s = ssm.updateScannerStates("145{");
		Assert.assertEquals(ScannerStates.TOKENIZENOMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		s = ssm.updateScannerStates("asd;");
		Assert.assertEquals(ScannerStates.TOKENIZENOMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		s = ssm.updateScannerStates("public{");
		Assert.assertEquals(ScannerStates.TOKENIZENOMATCH, s);
		
		ssm.setState(ScannerStates.TOKENIZEMATCH);
		s = ssm.updateScannerStates("sn\n");
		Assert.assertEquals(ScannerStates.TOKENIZENOMATCH, s);
		
	}
	
	@Test
	public void testTransitionsFromSKIPNOMATCH()
	{
		try
		{	
			ssm.setState(ScannerStates.SKIPNOMATCH);
			ScannerStates s = ssm.updateScannerStates("// \n");
			Assert.assertEquals(ScannerStates.SKIPMATCH, s);
			
			ssm.setState(ScannerStates.SKIPNOMATCH);
			s = ssm.updateScannerStates("/* asd as dsad  \n */");
			Assert.assertEquals(ScannerStates.SKIPMATCH, s);
			
			ssm.setState(ScannerStates.SKIPNOMATCH);
			s = ssm.updateScannerStates("/* asd as dsad  \n\n");
			Assert.assertEquals(ScannerStates.SKIPNOMATCH, s);
			
			ssm.setState(ScannerStates.SKIPNOMATCH);
			s = ssm.updateScannerStates("// \t asd as dsad  ");
			Assert.assertEquals(ScannerStates.SKIPNOMATCH, s);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTransitionsFromSKIPMATCH()
	{
		try
		{	
			ssm.setState(ScannerStates.SKIPMATCH);
			ScannerStates s = ssm.updateScannerStates("\t");
			Assert.assertEquals(ScannerStates.SKIPMATCH, s);
			
			ssm.setState(ScannerStates.SKIPMATCH);
			s = ssm.updateScannerStates("\n");
			Assert.assertEquals(ScannerStates.SKIPMATCH, s);
			
			ssm.setState(ScannerStates.SKIPMATCH);
			s = ssm.updateScannerStates(" ");
			Assert.assertEquals(ScannerStates.SKIPMATCH, s);
			
			ssm.setState(ScannerStates.SKIPMATCH);
			s = ssm.updateScannerStates("//");
			Assert.assertEquals(ScannerStates.SKIPNOMATCH, s);
			
			ssm.setState(ScannerStates.SKIPMATCH);
			s = ssm.updateScannerStates("/*");
			Assert.assertEquals(ScannerStates.SKIPNOMATCH, s);
			
			ssm.setState(ScannerStates.SKIPMATCH);
			s = ssm.updateScannerStates("1");
			Assert.assertEquals(ScannerStates.TOKENIZEMATCH, s);
			
			ssm.setState(ScannerStates.SKIPMATCH);
			s = ssm.updateScannerStates("[");
			Assert.assertEquals(ScannerStates.TOKENIZEMATCH, s);
			
			ssm.setState(ScannerStates.SKIPMATCH);
			s = ssm.updateScannerStates("0");
			Assert.assertEquals(ScannerStates.TOKENIZENOMATCH, s);
			
			ssm.setState(ScannerStates.SKIPMATCH);
			s = ssm.updateScannerStates("%");
			Assert.assertEquals(ScannerStates.TOKENIZENOMATCH, s);
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
