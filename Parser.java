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
			System.exit(0);
		}
	}
	public void program() 
	{
		System.out.println("Begin <program>");
		stmts();
	} 
	public void match(Token token)
	{
		if(token.equals(current)) 
		{
			System.out.println("Matched " + token.getDescription());
			try
			{
				current = lexer.lex();
			}
			catch (Exception e) //end of file 
			//TODO: make sure reached end of file successfully (not expecting more tokens)
			{
				System.out.println("End of file reached [error in match()]");
				System.exit(0);
			}
		}
		else 
		{
		System.out.println("ERROR"); 
		//TODO: change this to be more descriptive
		System.exit(0);
		}
	}
	public void stmts() //<stmts> -> <stmt> {<stmt>}
	{	
		try
		{
			System.out.println("Begin <stmts>");
			//TODO: make sure this is actually valid lol
			stmt();
		}
		catch (Exception e)
		{
			System.out.println("incorrext statements syntax: \"  " + current.getLexeme() + "\"");
			e.printStackTrace();
		}
	} 
	public void stmt() //<stmt> -> <decl_stmt> | <ass_stmt> | <loop_stmt> | <if_stmt> 
	{
		try {
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
			System.out.println("incorrext statement syntax: \" " + current.getLexeme() + "\"");
			e.printStackTrace();
		}
	} 
	
	public void decl_stmt() //<decl_stmt> -> (num|string) %identifier%
	{
		System.out.println("Begin <decl_stmt>");
		match(new Token(current.getLexeme(), "<decl_stmt>"));
	} 
	
	public void ass_stmt() //<ass_stmt> -> %identifier% = <expr>
	{
		System.out.println("Begin <ass_stmt>");
		match(new Token(current.getLexeme(), "identifier"));
	} 
	
	public void loop_stmt() throws Exception //<loop_stmt> -> repeat (<expr> | <bool_expr>) \( <stmts> \)
	{
		System.out.println("Begin <loop_stmt>");
		match(new Token(current.getLexeme(), "repeat keyword"));
		expr();
		match(new Token(current.getLexeme(), "open parenthesis"));
		stmts();
		match(new Token(current.getLexeme(), "close parenthesis"));
	} 
	
	public void if_stmt() //<if_stmt> -> if <bool_expr> \( <stmts> \)
	{
		System.out.println("Begin <if_stmt>");
		bool_expr();
		match(new Token(current.getLexeme(), "open parenthesis"));
		stmts();
		match(new Token(current.getLexeme(), "close parenthesis"));
	} 
	
	public void print_stmt() //<print_stmt> -> show \( <expr> \)
	{
		System.out.println("Begin <print_stmt>");
		match(new Token(current.getLexeme(), "show keyword"));
		match(new Token(current.getLexeme(), "open parenthesis"));
		expr();
		match(new Token(current.getLexeme(), "close parenthesis"));
	} 
	
	public void expr() //<expr> -> <val> { + <val>}
	{
		try {
			System.out.println("Begin <expr>");
			switch(current.getDescription()) 
			{
				case "string literal":
					match(new Token(current.getLexeme(), "string literal"));
					break;
				case "numeric literal":
					match(new Token(current.getLexeme(), "numeric literal"));
					break;
				default:
					throw new Exception();
			}
		}
		catch (Exception e) {
			System.out.println("incorrext expression syntax: \" " + current.getLexeme() + "\"");
			e.printStackTrace();
		}
	} 
	
	public void bool_expr() //<bool_expr> -> <expr> : <expr>
	{
		System.out.println("Begin <bool_expr>");
		expr();
		match(new Token(current.getLexeme(), "is equal to"));
		expr();
	} 
	
	public void val() //<val> -> %identifier% | %numeric_literal% | %string_literal%
	{
		System.out.println("Begin <val>");
		match(new Token(current.getLexeme(), "<val>"));
	} 
//	public static void main(String[] args) 
//	{
//		Parser parser = new Parser(args[0]);
//		parser.program();
//		//TODO: make sure the lexer works right 
//	}
}
