package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import exceptions.IllegalSyntaxException;
import logger.LoggerBuilder;
import scanner.Scanner;
import symtable.ClassEntry;
import symtable.Symboltable;
import token.Token;
import token.TokenType;

public class Parser
{
	private static final Logger log = LoggerBuilder.getLogger(Parser.class.getName());
	
	/**
	 * Source of Tokens
	 **/
	private Scanner scanner;
	
	/**
	 * Current Token
	 **/
	private Token sym;
	
	/**
	 * The previous symbol
	 */
	private Token lastSym;
	
	private Symboltable symboltable;
	/**
	 * Buffer to enable lookaheads 
	 **/
	private List<Token> symBuffer;
	private static final int LOOKAHEAD = 3;

	public Parser(Scanner scanner)
	{
		this.scanner = scanner;
		this.lastSym = null;
		initBuffer();
	}

	/**
	 * Check for additional Tokens
	 * @return
	 */
	public boolean isNext()
	{
		return !this.symBuffer.isEmpty();
	}
	/**
	 * Read next Token
	 */
	public void next()
	{
		this.lastSym = this.sym;
		this.sym = this.symBuffer.get(0);
		this.symBuffer.remove(0);
		fillSymBuffer();
	}

	/**
	 * Check the type of the current sym.
	 * @param type
	 * @return
	 */
	private boolean isType(TokenType type)
	{
		return this.sym.geTokenType() == type;
	}

	public void parse()
	{
		nextClass();
	}

	private void nextClass()
	{
		read(TokenType.CLASS);
		read(TokenType.IDENT);
		ClassEntry entry = new ClassEntry(this.lastSym.getContent());
		Symboltable st = new Symboltable(this.symboltable);
		
		this.symboltable.insert(entry);
		readClassbody();
	}

	private void readClassbody()
	{
		read(TokenType.LCURLYBRACK);
		readDeclarations();
		read(TokenType.RCURLYBRACK);
	}

	private void readDeclarations()
	{
		while (isNext())
		{
			if (isType(TokenType.FINAL))
			{
				readConstantDeclaration();
			}
			else if (isType(TokenType.INT))
			{
				readVariableDeclaration();
			}
			else if (isType(TokenType.PUBLIC))
			{
				readMethodDeclaration();
			}
			else
			{
				error();
			}
		}
	}

	private void readConstantDeclaration()
	{
		read(TokenType.FINAL);
		read(TokenType.INT);
		read(TokenType.IDENT);
		read(TokenType.EQUALS);
		readExpression();
		read(TokenType.SEMICOLON);
	}

	private void readVariableDeclaration()
	{
		read(TokenType.INT);
		read(TokenType.IDENT);
		read(TokenType.SEMICOLON);
	}

	private void readMethodDeclaration()
	{
		read(TokenType.PUBLIC);
		readMethodType();
		read(TokenType.IDENT);
		readFormalParameters();
		readMethodBody();
	}

	private void readMethodType()
	{
		if (isType(TokenType.VOID))
		{
			read(TokenType.VOID);
		}
		else if (isType(TokenType.INT))
		{
			read(TokenType.INT);
		}
		else
		{
			error();
		}
	}

	private void readFormalParameters()
	{
		read(TokenType.LPARANTHESE);

		if (isType(TokenType.INT))
		{
			readFpSection();

			while (isType(TokenType.COMMA))
			{
				read(TokenType.COMMA);
				readFpSection();
			}
		}

		read(TokenType.RPARANTHESE);
	}

	private void readFpSection()
	{
		read(TokenType.INT);
		read(TokenType.IDENT);
	}

	private void readMethodBody()
	{
		read(TokenType.LCURLYBRACK);

		while (isType(TokenType.INT))
		{
			readLocalDeclaration();
		}

		readStatementSequence();
		read(TokenType.RCURLYBRACK);
	}

	private void readLocalDeclaration()
	{
		read(TokenType.INT);
		read(TokenType.IDENT);
		read(TokenType.SEMICOLON);
	}

	private void readStatementSequence()
	{
		readStatement();

		while (isNext())
		{
			if (isType(TokenType.IDENT) && lookahead(1, TokenType.EQUALS))
			{
				readAssignment();
			}
			else if (isType(TokenType.IDENT) && lookahead(1, TokenType.LPARANTHESE))
			{
				readProcedureCall();
			}
			else if (isType(TokenType.IF))
			{
				readIfStatement();
			}
			else if (isType(TokenType.WHILE))
			{
				readWhileStatement();
			}
			else if (isType(TokenType.RETURN))
			{
				readReturnStatement();
			}
			else
			{
				break;
			}
		}
	}

	private void readStatement()
	{
		if (isType(TokenType.IDENT) && lookahead(1, TokenType.EQUALS))
		{
			readAssignment();
		}
		else if (isType(TokenType.IDENT) && lookahead(1, TokenType.LPARANTHESE))
		{
			readProcedureCall();
		}
		else if (isType(TokenType.IF))
		{
			readIfStatement();
		}
		else if (isType(TokenType.WHILE))
		{
			readWhileStatement();
		}
		else if (isType(TokenType.RETURN))
		{
			readReturnStatement();
		}
		else
		{
			error();
		}
	}

	private void readAssignment()
	{
		read(TokenType.IDENT);
		read(TokenType.EQUALS);
		readExpression();
		read(TokenType.SEMICOLON);
	}

	private void readProcedureCall()
	{
		readInternProcedureCall();
		read(TokenType.SEMICOLON);
	}

	private void readInternProcedureCall()
	{
		read(TokenType.IDENT);
		readActualParameters();
	}

	private void readIfStatement()
	{
		read(TokenType.IF);
		read(TokenType.LPARANTHESE);
		readExpression();
		read(TokenType.RPARANTHESE);
		read(TokenType.LCURLYBRACK);
		readStatementSequence();
		read(TokenType.RCURLYBRACK);
		read(TokenType.ELSE);
		read(TokenType.LCURLYBRACK);
		readStatementSequence();
		read(TokenType.RCURLYBRACK);
	}

	private void readWhileStatement()
	{
		read(TokenType.WHILE);
		read(TokenType.LPARANTHESE);
		readExpression();
		read(TokenType.RPARANTHESE);
		read(TokenType.LCURLYBRACK);
		readStatementSequence();
		read(TokenType.RCURLYBRACK);
	}

	private void readReturnStatement()
	{
		read(TokenType.RETURN);

		if (isType(TokenType.IDENT) || isType(TokenType.NUMBER) || isType(TokenType.LPARANTHESE))
		{
			readSimpleExpression();
		}
		read(TokenType.SEMICOLON);
	}

	private void readActualParameters()
	{
		read(TokenType.LPARANTHESE);

		if (isType(TokenType.IDENT) || isType(TokenType.NUMBER) || isType(TokenType.LPARANTHESE))
		{
			readExpression();

			while (isType(TokenType.COMMA))
			{
				readExpression();
			}
		}
		read(TokenType.RPARANTHESE);
	}

	private void readExpression()
	{
		readSimpleExpression();

		if (isType(TokenType.LOGICALOPERATOR) || isType(TokenType.LOGICALLESS) || isType(TokenType.LOGICALGREATER)
				|| isType(TokenType.LOGICALLESSEQ) || isType(TokenType.LOGICALGREATEREQ))
		{
			readBinaryOperator();
			readSimpleExpression();
		}
	}

	private void readBinaryOperator()
	{
		if (isType(TokenType.LOGICALOPERATOR))
		{
			read(TokenType.LOGICALOPERATOR);
		}
		else if (isType(TokenType.LOGICALLESS))
		{
			read(TokenType.LOGICALLESS);
		}
		else if (isType(TokenType.LOGICALGREATER))
		{
			read(TokenType.LOGICALGREATER);
		}
		else if (isType(TokenType.LOGICALLESSEQ))
		{
			read(TokenType.LOGICALLESSEQ);
		}
		else if (isType(TokenType.LOGICALGREATEREQ))
		{
			read(TokenType.LOGICALGREATEREQ);
		}
		else
		{
			error();
		}
	}

	private void readSimpleExpression()
	{
		readTerm();

		while (true)
		{
			if (isType(TokenType.OPERATORADD))
			{
				read(TokenType.OPERATORADD);
				readTerm();
			}
			else if (isType(TokenType.OPERATORMIN))
			{
				read(TokenType.OPERATORMIN);
				readTerm();
			}
			else
			{
				break;
			}
		}
	}

	private void readTerm()
	{
		readFactor();
		while (true)
		{
			if (isType(TokenType.OPERATORMULT))
			{
				read(TokenType.OPERATORMULT);
				readFactor();
			}
			else if (isType(TokenType.OPERATORDIV))
			{
				read(TokenType.OPERATORDIV);
				readFactor();
			}
			else
			{
				break;
			}
		}
	}

	private void readFactor()
	{
		if (isType(TokenType.IDENT) && lookahead(1, TokenType.LPARANTHESE))
		{
			readInternProcedureCall();
		}
		else if (isType(TokenType.NUMBER))
		{
			read(TokenType.NUMBER);
		}
		else if (isType(TokenType.LPARANTHESE))
		{
			read(TokenType.LPARANTHESE);
			readExpression();
			read(TokenType.RPARANTHESE);
		}
		else if (isType(TokenType.IDENT))
		{
			read(TokenType.IDENT);
		}
		else
		{
			error();
		}
	}

	public List<Token> getSymBuffer()
	{
		return this.symBuffer;
	}

	public Token getSym()
	{
		return this.sym;
	}

	private void initBuffer()
	{
		this.symBuffer = new ArrayList<Token>();
		fillSymBuffer();
		if (isNext())
		{
			next();
		}
	}

	private void fillSymBuffer()
	{
		while (this.symBuffer.size() < LOOKAHEAD)
		{
			if (this.scanner.isNext())
			{
				try
				{
					this.symBuffer.add(this.scanner.getNext());
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				break;
			}
		}
		/*
		String s = "BUFFER:";
		for(int i=0; i< this.symBuffer.size(); i++)
		{
			s += " ";
			s += symBuffer.get(i).getContent();
		}
		log.info(s);
		*/
	}

	private void error()
	{
		throw new IllegalSyntaxException(this.sym, "Unexpected Token");
	}

	private void error(TokenType tt)
	{
		throw new IllegalSyntaxException(this.sym, tt);
	}

	private boolean lookahead(int i, TokenType tt)
	{
		int indexInBuffer = i - 1;
	
		if (this.symBuffer.size() > i)
		{
			Token t = symBuffer.get(indexInBuffer);
			if (t.geTokenType() == tt)
			{
				return true;
			}
		}
		return false;
	}

	private void read(TokenType type)
	{
		if (isType(type))
		{
			this.logRead(this.sym);
	
			if (isNext())
			{
				next();
			}
		}
		else
		{
			error(type);
		}
	}

	/*
	 * private void oneOfOrThrow(TokenType... tokenTypes) { for (TokenType t :
	 * tokenTypes) { if (this.sym.geTokenType() == t) { return; } } throw new
	 * IllegalSyntaxException(sym, "Unexpected Token"); }
	 * 
	 * private void readOrThrow(TokenType expected) { if (this.sym.geTokenType()
	 * == expected) { logDetect(expected, sym.getContent()); next(); } else {
	 * throw new IllegalSyntaxException(sym, expected); } }
	 */
	private void logRead(Token t)
	{
		log.info("READ " + t.geTokenType() + "<" + t.getContent() + ">");
	}
}
