//tenny merged the parser and driver
public class Parser {
	Token current; //holds current
	Lexer lexer; //holds our lexer
	
	public Parser(String filename)
	{
		try
		{
			lexer = new Lexer(new File(filename));
			current = lexer.lex();
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
		//try
		//{
		//	
		//}
		//catch(Exception e)
		//{
		//	System.out.println(currentToken + " does not match a valid statement");
		//}
		//finally
		//{
		//kill program
		//}
	} 
	public void stmts()
	{
		System.out.println("Begin <stmts>");
		//check if there is at least one statement
	} 
	public void stmt()
	{
		System.out.println("Begin <stmt>");
		//check if the following statement is a valid type
	} 
	public void decl_stmt()
	{
		
	} 
	public void ass_stmt()
	{
		
	} 
	public void loop_stmt()
	{
		
	} 
	public void if_stmt()
	{
		
	} 
	public void print_stmt()
	{
		
	} 
	public void expr()
	{
		
	} 
	public void bool_expr()
	{
		
	} 
	public void val()
	{
		
	} 
	public static void main(String[] args) {
		Parser parser = new Parser(args[0]);
		parser.program();
		//we gonna have to make sure the lexer works right 
	}
}
