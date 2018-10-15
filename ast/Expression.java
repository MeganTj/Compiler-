package ast;
import environment.Environment;

/**
 * Abstract class Expression - represents an expression.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public abstract class Expression
{
    /**
     * Evaluates an expression and returns resulting Integer
     * 
     * @param env the Environment that remembers variables
     * @return the Integer value from evaluating the expression
     */
    public abstract Integer eval(Environment env);
}
