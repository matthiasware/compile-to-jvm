package test.token;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import token.PatternMatcher;

public class TPatternMatcher
{
	private static PatternMatcher matcher;
	
	@Before
	public void init()
	{
		this.matcher = new PatternMatcher();
	}
	@Test
	public void testIsMatching()
	{
		Assert.assertTrue(this.matcher.isMatching("an"));
		Assert.assertTrue(this.matcher.isMatching("class"));
		Assert.assertTrue(this.matcher.isMatching("final"));
		Assert.assertTrue(this.matcher.isMatching("public"));
		Assert.assertTrue(this.matcher.isMatching("void"));
		Assert.assertTrue(this.matcher.isMatching("if"));
		Assert.assertTrue(this.matcher.isMatching("else"));
		Assert.assertTrue(this.matcher.isMatching("while"));
		Assert.assertTrue(this.matcher.isMatching("return"));
		
		Assert.assertTrue(this.matcher.isMatching("}"));
		Assert.assertTrue(this.matcher.isMatching("{"));
		Assert.assertTrue(this.matcher.isMatching("="));
		Assert.assertTrue(this.matcher.isMatching(";"));
		Assert.assertTrue(this.matcher.isMatching(","));
		Assert.assertTrue(this.matcher.isMatching("("));
		Assert.assertTrue(this.matcher.isMatching(")"));
		Assert.assertTrue(this.matcher.isMatching("["));
		Assert.assertTrue(this.matcher.isMatching("]"));
		
		Assert.assertTrue(this.matcher.isMatching("=="));
		Assert.assertTrue(this.matcher.isMatching("<"));
		Assert.assertTrue(this.matcher.isMatching(">"));
		Assert.assertTrue(this.matcher.isMatching("<="));
		Assert.assertTrue(this.matcher.isMatching(">="));
		Assert.assertTrue(this.matcher.isMatching("*"));
		Assert.assertTrue(this.matcher.isMatching("/"));
		Assert.assertTrue(this.matcher.isMatching("+"));
		Assert.assertTrue(this.matcher.isMatching("-"));
		
		Assert.assertTrue(this.matcher.isMatching("1410004"));
		Assert.assertTrue(this.matcher.isMatching("asdasd"));
		Assert.assertTrue(this.matcher.isMatching("A044asd"));
		
	}
	@Test
	public void isNotMatching()
	{
		Assert.assertFalse(this.matcher.isMatching("0a"));
		Assert.assertFalse(this.matcher.isMatching("a{"));
	}
	@Test
	public void testIsStartOfSkip()
	{
		String[] s = {" ","//","/*"," ","\n"};
		for(int i=0; i<s.length; i++)
		{
			boolean b = this.matcher.isStartOfSkip(s[i]);
			Assert.assertTrue(b);
		}
	}
	@Test
	public void testEndOfSkipTrue()
	{
		String[] s={"/* asdsa  \n */","// asdas dasd as \n","   \n \t"};
		for(int i=0; i<s.length; i++)
		{
			boolean b = this.matcher.isEndOfSkip(s[i]);
			Assert.assertTrue(b);
		}
	}
	@Test
	public void testEndOfSkipFalse()
	{
		String[] s={"/* asdsaas dsad {","// asdas "};
		for(int i=0; i<s.length; i++)
		{
			boolean b = this.matcher.isEndOfSkip(s[i]);
			Assert.assertFalse(b);
		}
	}
}
