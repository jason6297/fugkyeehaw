//Jason Peavy & Hannah Saffel
//CSC 415 Assignment 5
//PIFL language lex & parse

import java.io.File;

public class Parser 
{
	Token current; //holds current token
	Lexer lexer; //holds our lexer
	
	public Parser(String filename)
	{
		try
		{
			lexer = new Lexer(filename);
			current = lexer.lex();
			program();
		}
		catch(Exception e)
		{
			System.out.println("Cannot open input file.");
		}
		System.exit(0);
	}
	
	public void match(Token token) throws Exception
	{
		if(token.equals(current)) 
		{
			System.out.println("Matched " + token.getDescription());
			current = lexer.lex();
		}
		else if (token.equals(null))
		{
			System.out.println("Token returned null"); 
			System.exit(0);
		}
		else
		{
			throw new Exception();
		}
	}
	
	public void program() 
	{
		try
		{
			System.out.println("Begin <program>");
			stmts();
		}
		catch(Exception e)
		{
			System.out.println("End of file reached");
			System.exit(0);
		}
	} 
	
	public void stmts() //<stmts> -> <stmt> {<stmt>}
	{	
		System.out.println("Begin <stmts>");
		stmt();
		while (current.getDescription().equals("num keyword")
				|| current.getDescription().equals("string keyword")
				|| current.getDescription().equals("repeat keyword")
				|| current.getDescription().equals("if keyword")
				|| current.getDescription().equals("show keyword")
				|| current.getDescription().equals("identifier"))
		{
			stmt();
		}
		//cannot handle nested statements
//		if (current.equals(null))
//		{
//			System.out.println("End of file reached");
//			System.exit(0);
//		}
//		else
//		{
//			System.out.println("invalid syntax: '" + current.getLexeme() + "'" + ", expected a statement");
//		}
			
	} 
	
	public void stmt() //<stmt> -> <decl_stmt> | <ass_stmt> | <loop_stmt> | <if_stmt> | <print_stmt>
	{
		try 
		{
			System.out.println("Begin <stmt>");
			switch(current.getDescription()) 
			{
				case "num keyword":
					decl_stmt();
					break;
				case "string keyword":
					decl_stmt();
					break;
				case "repeat keyword":
					loop_stmt();
					break;
				case "if keyword":
					if_stmt();
					break;
				case "show keyword":
					print_stmt();
					break;
				case "identifier":
					ass_stmt();
					break;
				default:
					throw new Exception();
			}
		}
		catch (Exception e)
		{
			System.out.println("incorrext statement syntax: '" + current.getLexeme() + "'" + ", expected a statement");
			System.exit(0);
		}
	} 
	
	public void decl_stmt() //<decl_stmt> -> (num|string) %identifier%
	{
		try 
		{
			System.out.println("Begin <decl_stmt>");
			switch(current.getDescription()) 
			{
				case "num keyword":
					match(new Token(current.getLexeme(), "num keyword"));
					break;
				case "string keyword":
					match(new Token(current.getLexeme(), "string keyword"));
					break;
				default:
					throw new Exception();
			}
			match(new Token(current.getLexeme(), "identifier"));
		}
		catch (Exception e)
		{
			System.out.println("incorrext declaration syntax: '" + current.getLexeme() + "'" + ", expected an identifier");
			System.exit(0);
		}
	} 
	
	public void ass_stmt() //<ass_stmt> -> %identifier% = <expr>
	{
		try {
		System.out.println("Begin <ass_stmt>");
		match(new Token(current.getLexeme(), "identifier"));
		match(new Token(current.getLexeme(), "assignment"));
		expr();
		}
		catch(Exception e)
		{
			System.out.println("incorrext assignment syntax: '" + current.getLexeme() + "'" + ", expected '='");
			System.exit(0);
		}
	} 
	
	public void loop_stmt()//<loop_stmt> -> repeat (<expr> | <bool_expr>) \( <stmts> \)
	{
		try {
			System.out.println("Begin <loop_stmt>");
			match(new Token(current.getLexeme(), "repeat keyword"));
			expr();
			if (current.getDescription().equals("is equal to"))
			{
				match(new Token(current.getLexeme(), "is equal to"));
				expr();
			}
			match(new Token(current.getLexeme(), "open parenthesis"));
			stmts();
			match(new Token(current.getLexeme(), "close parenthesis"));
		}
		catch(Exception e)
		{
			System.out.println("incorrext loop syntax: '" + current.getLexeme() + "'" + ", expected 'boolean operator or (statement)'");
			System.exit(0);
		}
	} 
	
	public void if_stmt() //<if_stmt> -> if <bool_expr> \( <stmts> \)
	{
		try {
			System.out.println("Begin <if_stmt>");
			match(new Token(current.getLexeme(), "if keyword"));
			bool_expr();
			match(new Token(current.getLexeme(), "open parenthesis"));
			stmts();
			match(new Token(current.getLexeme(), "close parenthesis"));
		}
		catch(Exception e)
		{
			System.out.println("incorrext if statement syntax: '" + current.getLexeme() + "'" + ", expected parenthesis around expression");
			System.exit(0);
		}
	} 
	
	public void print_stmt() //<print_stmt> -> show \( <expr> \)
	{
		try {
			System.out.println("Begin <print_stmt>");
			match(new Token(current.getLexeme(), "show keyword"));
			match(new Token(current.getLexeme(), "open parenthesis"));
			expr();
			match(new Token(current.getLexeme(), "close parenthesis"));
		}
		catch(Exception e)
		{
			System.out.println("incorrext print statement syntax: '" + current.getLexeme() + "'" + ", expected an expression");
			System.exit(0);
		}
	}
	
	public void expr() //<expr> -> <val> { + <val>}
	{
		try {
			System.out.println("Begin <expr>");
			if(current.getDescription().equals("string literal") 
					|| current.getDescription().equals("numeric literal")
					|| current.getDescription().equals("identifier"))
			{
				val();
				while(current.getDescription().equals("add/concatenate"))
				{
					match(new Token(current.getLexeme(), "add/concatenate"));
					val();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("incorrext if statement syntax: '" + current.getLexeme() + "'" + ", expected '+'");
			System.exit(0);
		}
	} 
	
	public void bool_expr() //<bool_expr> -> <expr> : <expr>
	{
		try {
			System.out.println("Begin <bool_expr>");
			expr();
			match(new Token(current.getLexeme(), "is equal to"));
			expr();
		}
		catch(Exception e)
		{
			System.out.println("incorrext if statement syntax: '" + current.getLexeme() + "'" + ", expected ':'");
			System.exit(0);
		}
	} 
	
	public void val() //<val> -> %identifier% | %numeric_literal% | %string_literal%
	{
		try 
		{
			System.out.println("Begin <val>");
			switch(current.getDescription()) 
			{
				case "string literal":
					match(new Token(current.getLexeme(), "string literal"));
					break;
				case "numeric literal":
					match(new Token(current.getLexeme(), "numeric literal"));
					break;
				case "identifier":
					match(new Token(current.getLexeme(), "identifier"));
					break;
				default:
					throw new Exception();
			}
		}
		catch (Exception e) 
		{
			System.out.println("incorrext expression syntax: '" + current.getLexeme() + "'" + ", expected an identifier or literal value");
			System.exit(0);
		}
	} 
}
