import java.util.Scanner;

//Jason Peavy & Hannah Saffel
//CSC 415 Assignment 5
//PIFL language lex & parse
public class Driver 
{
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		String readit = "";
		readit = scan.next();
		Parser parse = new Parser(readit);
	} //type:  src\test.txt
}
