package test.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import token.TokenType;

public class TTokenType
{
	private static List<String> testSetCLASS=Arrays.asList("class","Class","CLASS");
	private static List<String> testSetFINAL=Arrays.asList("final","Final","FINAL");
	private static List<String> testSetPUBLIC=Arrays.asList("public","Public","PUBLIC");
	private static List<String> testSetVOID;
	private static List<String> testSetINT;
	private static List<String> testSetIF;
	private static List<String> testSetELSE;
	private static List<String> testSetWHILE;
	private static List<String> testSetRETURN;
	private static List<String> testSetLCURLYBRACK;
	private static List<String> testSetRCURLYBRACK;
	private static List<String> testSetEQUALS;
	private static List<String> testSetSEMICOLON;
	private static List<String> testSetCOMMA;
	private static List<String> testSetLPARANTHESE;
	private static List<String> testSetRPARANTHESE;
	private static List<String> testSetLRACK;
	private static List<String> testSetRBRACK;
	private static List<String> testSetLOGICALOPERATOR;
	private static List<String> testSetLOGICALLESS;
	private static List<String> testSetLOGICALGREATER;
	private static List<String> testSetLOGICALLESSEQ;
	private static List<String> testSetLOGICALGREATEREQ;
	private static List<String> testSetOPERATORMULT;
	private static List<String> testSetOPERATORDIV;
	private static List<String> testSetOPERATORADD;
	private static List<String> testSetOPERATORMIN;
	private static List<String> testSetNUMBER;
	private static List<String> testSetIDENT;
	private static List<String> testSetSHORTCOMMENT=Arrays.asList("// sad \n");
	private static List<String> testSetLONGCOMMENT=Arrays.asList("/*  */","/* asd * asda / */");
	private static List<String> testSetWHITESPACE=Arrays.asList("\n","\t"," ","\n\t\t");
	
	private static Matcher matcher;
	@Before
	public void init()
	{
		
	}
	@Test 
	public void testForWhiteSpace()
	{
		
	}
	
	
	public void test()
	{
		for(TokenType tt : TokenType.values())
		{
			System.out.println(tt);
		}
	}
	
}
