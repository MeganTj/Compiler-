package ast;
import environment.Environment;

/**
 * Represents an integer value.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public class Number extends Expression
{
    private int value;

    /**
     * Constructor for objects of class Number
     * 
     * @param value the value of the number
     */
    public Number(int value)
    {
        this.value = value;
    }
    
    /**
     * Evaluates an expression and returns resulting Integer
     * 
     * @param env the Environment that remembers variables
     * @return the Integer value from evaluating the expression
     */
    public Integer eval(Environment env)
    {
        return value;
    }
}
