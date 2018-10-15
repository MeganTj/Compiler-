package scanner;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;
import java.io.FileInputStream;



/**
 * Scanner is a simple scanner for Compilers and Interpreters
 * (2014-2015) lab exercise 1
 * 
 * @author Megan Tjandrasuwita
 * @version 1/29/2018
 *  
 * Usage: 
 * use the nextToken method to iterate through the String or file
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }

    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string.  It sets the end-of-file flag and
     * then reads the first character of the input string into the
     * instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }

    /**
     * Reads in the next character in the input file
     *
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if (inp == -1 || (char) inp == '.') 
                eof = true;
            else
                currentChar = (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Takes in a char value representing the expected value of currentChar.
     * Compares the value of the input parameter to currentChar. If the two
     * are the same, it advances the input stream one character; otherwise,
     * the method throws a ScanErrorException
     * 
     * @param expected the value representing the expected value of currentChar
     */    
    private void eat(char expected) throws ScanErrorException
    {
        if (expected == currentChar)
        {
            getNextChar();
        }
        else
        {
            throw new ScanErrorException("Illegal character - expected "
                + expected + " and found " + currentChar);
        }
    }

    /**
     * Checks whether there are more characters left (end of file hasn't
     * been reached)
     * 
     * @return true if there are more characters left; otherwise, false
     */
    public boolean hasNext()
    {
        if (eof == true)
        {
            return false;
        }
        return true;
    }

    /**
     * Tests whether a character is a letter
     * 
     * @param c the character that is being tested 
     * @return true if the character is a letter; otherwise, false
     */
    public static boolean isLetter(char c)
    {
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
        {
            return true;
        }
        return false;
    }

    /**
     * Tests whether a character is a digit
     * 
     * @param c the character that is being tested 
     * @return true if the character is a digit; otherwise, false
     */
    public static boolean isDigit(char c)
    {
        if (c >= '0' && c <= '9')
        {
            return true;
        }
        return false;
    }

    /**
     * Tests whether a character is white space
     * 
     * @param c the character that is being tested 
     * @return true if the character is white space; otherwise, false
     */
    public static boolean isWhiteSpace(char c)
    {
        if (c == ' ' || c == '\t' || c == '\r' || c == '\n')
        {
            return true;
        }
        return false;
    }

    /**
     * Tests whether a character is an operator
     * 
     * @param c the character that is being tested 
     * @return true if the character is an operator; otherwise, false
     */
    public static boolean isOperator(char c)
    {
        if (c == '+' || c == '-' || c == '*' ||
            c == '/' || c == '%' || c == '(' || c == ')' ||
            c == ':' || c == ';' || c == '.')
        {
            return true;
        }
        return false;
    }
    
    /**
     * Tests whether a character is a relative operator
     * 
     * @param c the character that is being tested 
     * @return true if the character is an operator; otherwise, false
     */
    public static boolean isRelOp(char c)
    {
        if (c == '=' || c == '<' || c == '>')
        {
            return true;
        }
        return false;
    }
    
    /**
     * Tests whether a String is a keyword
     * 
     * @param s the String that is being tested 
     * @return true if the character is a keyword; otherwise, false
     */
    public static boolean isKeyword(String s)
    {
        switch (s) 
        {
            case "VAR":
                return true;
            case "PROCEDURE":
                return true;
            case "BEGIN":
                return true;
            case "RETURN":
                return true;
            case "END":
                return true;
            case "WHILE":
                return true;
            case "WRITELN":
                return true;
            default:
                return false;
        }
    }

    /**
     * Scans the input and returns the String representing a number found
     * in the input stream or throws a ScanErrorException if no number
     * is recognized.
     * 
     * @return the number as a String if it is recognized
     * @throw a ScanErrorException if no number is recognized
     */
    private String scanNumber() throws ScanErrorException
    {
        String ans = "";
        if (!isDigit(currentChar))
        {
            throw new ScanErrorException();
        }
        else
        {
            while (isDigit(currentChar) && hasNext())
            {
                ans += currentChar;
                eat(currentChar);
            }
            return ans;
        }
    }

    /**
     * Scans the input and returns the String representing an identifier found
     * in the input stream or throws a ScanErrorException if no identifier
     * is recognized.
     * 
     * @return the identifier as a String if it is recognized
     * @throw a ScanErrorException if no identifier is recognized
     */
    private String scanIdentifier() throws ScanErrorException
    {
        String ans = "";
        if (!isLetter(currentChar))
        {
            throw new ScanErrorException();
        }
        else
        {
            while ((isLetter(currentChar) || isDigit(currentChar)) && hasNext())
            {
                ans += currentChar;
                eat(currentChar);
            }
            return ans;
        }
    }

    /**
     * Scans the input and returns the String representing an operator found
     * in the input stream or throws a ScanErrorException if no operator
     * is recognized.
     * 
     * @return the operator as a String if it is recognized
     * @throw a ScanErrorException if no operator is recognized
     */
    private String scanOperator() throws ScanErrorException
    {
        String ans = "";
        if (!isOperator(currentChar))
        {
            throw new ScanErrorException();
        }
        else
        {
            ans += currentChar;
            eat(currentChar);
            if (((ans.equals("+") || ans.equals("-") || ans.equals("*") ||
                ans.equals("/") || ans.equals("%") || ans.equals("=") ||
                ans.equals(":")) && (currentChar == '=')) && hasNext())
            {
                ans += currentChar;
                eat(currentChar);
            }
            return ans;
        }
    }
    
    /**
     * Scans the input and returns the String representing a relative operator
     * found in the input stream or throws a ScanErrorException if no operator
     * is recognized.
     * 
     * @return the relative operator as a String if it is recognized
     * @throw a ScanErrorException if no operator is recognized
     */
    private String scanRelOp() throws ScanErrorException
    {
        String ans = "";
        if (!isRelOp(currentChar))
        {
            throw new ScanErrorException();
        }
        else
        {
            ans += currentChar;
            eat(currentChar);
            if (((ans.equals("<") && (currentChar == '>' || currentChar == '='))
                || (ans.equals(">") && currentChar == '='))
                && hasNext())
            {
                ans += currentChar;
                eat(currentChar);
            }
            return ans;
        }
    }
    
    /**
     * Skips any leading white space and examines the value of the value
     * of currentChar, scans the next token in the input stream
     * 
     * @return the next token as a String
     */
    public String nextToken() throws ScanErrorException
    {
        while (hasNext() && isWhiteSpace(currentChar))
        {
            eat(currentChar);
        }
        if (!hasNext())
        {
            return ".";
        }
        if (currentChar == '(')
        {
            eat(currentChar);
            //             if (currentChar == '/')
            //             {
            //                 while (currentChar != '\n' && hasNext())
            //                 {
            //                     eat(currentChar);
            //                 }
            //                 eat(currentChar);
            //             }
            if (currentChar == '*')
            {
                eat(currentChar);
                boolean isFinished = false;
                while (isFinished == false)
                {
                    if (hasNext() == false)
                    {
                        throw new ScanErrorException("Block comment" +
                            "not closed");
                    }
                    if (currentChar == '*')
                    {
                        eat(currentChar);
                        if (currentChar == ')')
                        {
                            eat(currentChar);
                            isFinished = true;
                        }
                    }
                    else
                    {
                        eat(currentChar);
                    }
                }
            }
            //             else if (isOperator(currentChar))
            //             {
            //                 String op = scanOperator();
            //                 return "/" + op;
            //             }
            else
            {
                return "(";
            }
        }
        if (isWhiteSpace(currentChar))
        {
            while (hasNext() && isWhiteSpace(currentChar))
            {
                eat(currentChar);
            }
        }
        if (isDigit(currentChar))
        {
            return scanNumber();
        }
        else if (isLetter(currentChar))
        {
            return scanIdentifier();          
        }
        else if (isOperator(currentChar))
        {
            return scanOperator();
        }
        else if (isRelOp(currentChar))
        {
            return scanRelOp();
        }
        else
            throw new ScanErrorException("Not a valid character");
    }    

    /**
     * The main method
     * 
     * @param args an array of arguments
     */
    public static void main(String [] args)
    {
        File file = new File("parserTest5.5.txt");
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            Scanner lex = new Scanner(is);
            String token = lex.nextToken();
            System.out.println(token);
            while (!token.equals("."))
            {
                token = lex.nextToken();
                System.out.println(token);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
