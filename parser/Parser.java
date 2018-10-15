package parser;

import environment.Environment;
import ast.*;
import ast.Statement;
import ast.Expression;
import ast.Writeln;
import ast.Assignment;
import ast.Block;
import ast.Number;
import ast.Variable;
import ast.BinOp;
import scanner.Scanner;
import scanner.ScanErrorException;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;


/**
 * Parses a stream of token and evaluates expressions. Has methods
 * parseNumber, parseFactor, parseTerm, parseExpression
 * 
 * @author Megan Tjandrasuwita 
 * @version 2/25/2018
 */
public class Parser
{
    private Scanner scan;
    private String current;
    //private Map<String, Expression> m;
    //private Environment env;
    
    /**
     * Constructor for objects of class Parser
     * 
     * @param scan a Scanner
     */
    public Parser(Scanner scan)
    {
        this.scan = scan;
        try 
        {
            current = scan.nextToken();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //env = new Environment();
        //m = new HashMap<String, Expression>();
    }

    /**
     * Eats a token
     * 
     * @param s a token
     */
    private void eat(String s) 
    {
        if (s.equals(current))
        {
            try
            {
                current = scan.nextToken();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new IllegalArgumentException("Illegal token - expected "
                                    + s + " and found " + current);
        }
    }
    
    /**
    * Parses a number by eating it and returning its value
    * @precondition: current token is an integer
    * @postcondition: number token has been eaten
    * @return the value of the parsed integer
    */
    private int parseNumber()
    {
        int num = Integer.parseInt(current);
        eat(current);
        return num;
    }
    
    /**
    * Parses a statement that either starts with WRITELN, BEGIN, IF,
    * WHILE, or a variable
    * @precondition: current token is WRITELN, BEGIN, IF, WHILE, or a variable
    * @postcondition: all tokens within the statement has been eaten
    * 
    * @return a Statement
    */
    public Statement parseStatement()
    {
        if (current.equals("WRITELN"))
        {
            eat(current);
            eat("(");
            Expression exp = parseExpr();
            eat(")");
            eat(";");
            return new Writeln(exp);
        }
        else if (current.equals("BEGIN"))
        {
            eat(current);
            List<Statement> st = new ArrayList<Statement>();
            while (!current.equals("END"))
            {
                st.add(parseStatement());
            }
            eat("END");
            eat(";");
            return new Block(st);
        }
        else if (current.equals("IF"))
        {
            eat(current);
            Expression exp1 = parseExpr();
            String relop = current;
            eat(current);
            Expression exp2 = parseExpr();
            eat("THEN");
            Statement stmt = parseStatement();
            if (current.equals("ELSE"))
            {
                eat(current);
                return new If(new Condition(relop, exp1, exp2), stmt,
                                                    parseStatement());
            }
            return new If(new Condition(relop, exp1, exp2), stmt);
        }
        else if (current.equals("WHILE"))
        {
            eat(current);
            Expression exp1 = parseExpr();
            String relop = current;
            eat(current);
            Expression exp2 = parseExpr();
            eat("DO");
            Statement stmt = parseStatement();
            return new While(new Condition(relop, exp1, exp2), stmt);
        }
        else if (current.equals("FOR"))
        {
            eat(current);
            String name = current;
            eat(current);
            eat(":=");
            Expression exp = parseExpr();
            eat("TO");
            Expression num = parseFactor();
            eat("DO");
            Statement stmt = parseStatement();
            return new For(name, new Assignment(name, exp), num, stmt);
        }
        else
        {
            String id = current;
            eat(current);
            eat(":=");
            Expression exp = parseExpr();
            //m.put(id, num);
            eat(";");
            return new Assignment(id, exp);
        }
    }
    
    /**
     * Parses a factor, which is defined as either a number, a negative number,
     * or an expression within parentheses.
     * 
     * @precondition: current token is either a number, parenthesis,
     *                negative sign
     * @postcondition: all tokens part of the factor are eaten
     * @return a Expression
     */
    public Expression parseFactor()
    {
        if (current.equals(")"))
        {
            eat(current);
            return new Number(1);
        }
        if (current.equals("-"))
        {
            eat(current);
            return new BinOp("-", new Number(0), parseFactor());
        }
        else if (current.equals("("))
        {
            eat(current);
            Expression factor = parseExpr();
            if (current.equals(")"))
            {
                eat(current);
            }
            return factor;
        }
        else
        {
            try
            {
                int num = Integer.parseInt(current);
                eat(current);
                return new Number(num);
            }
            catch (Exception e)
            {
                String key = current;
                eat(current);
                return new Variable(key);
                //throw e;
            }
        }
    }
    
    /**
     * Parses a term which consists of factors multiplied and divided
     * 
     * @precondition: current token is a factor
     * @postcondition: all tokens part of the term are eaten
     * @return a Expression
     */
    private Expression parseTerm()
    {
        Expression factor = parseFactor();
        while (current.equals("*")  || current.equals("/") ||
               current.equals("mod"))
        {
            if (current.equals("*"))
            {
                eat(current);
                factor = new BinOp("*", factor, parseFactor());
            }
            else if (current.equals("/"))
            {
                eat(current);
                factor = new BinOp("/", factor, parseFactor());
            }
            else
            {
                eat(current);
                factor = new BinOp("%", factor, parseFactor());
            }
        }
        return factor;
    }
    
    /**
     * Parses a expression which consists of terms added and subracted
     * 
     * @precondition: current token is a term
     * @postcondition: all tokens part of the expression are eaten
     * @return a Expression
     */
    private Expression parseExpr()
    {
        Expression term = parseTerm();
        while (current.equals("+") || current.equals("-"))
        {
            if (current.equals("+"))
            {
                eat(current);
                term = new BinOp("+", term, parseTerm());
            }
            else
            {
                eat(current);
                term = new BinOp("-", term, parseTerm());
            }
        }
        return term;       
    }
    
    /**
     * The main method
     * 
     * @param args an array of arguments
     */
    public static void main(String [] args)
    {
        try {
            Scanner scan = new Scanner(new FileInputStream(
                                    new File("parserTest6.txt")));
            //Scanner scan = new Scanner("WRITELN(10 mod 4);. ");
            Parser pars = new Parser(scan);
            Environment env = new Environment();
            while (scan.hasNext())
            {                
                Statement st = pars.parseStatement();
                st.exec(env);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
