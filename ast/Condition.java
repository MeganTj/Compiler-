package ast;
import environment.Environment;

/**
 * Represents a condition of an if or while statement
 * 
 * @author Megan Tjandrasuwita 
 * @version 3/17/2018
 */
public class Condition extends Expression
{
    private String relop;
    private Expression exp1;
    private Expression exp2;
    
    /**
     * Constructor for objects of class Condition
     * 
     * @param relop the relative operator
     * @param exp1 the first expression
     * @param exp2 the second expression
     */
    public Condition(String relop, Expression exp1, Expression exp2)
    {
        this.relop = relop;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * Evaluates an expression and returns resulting Integer
     * 
     * @param env the Environment that remembers variables
     * @return the Integer value from evaluating the expression
     */
    public Integer eval(Environment env)
    {
        if (relop.equals("="))
        {
            if (exp1.eval(env) == exp2.eval(env))
            {
                return 1;
            }
        }
        else if (relop.equals("<>"))
        {
            if (exp1.eval(env) != exp2.eval(env))
            {
                return 1;
            }
        }
        else if (relop.equals("<"))
        {
            if (exp1.eval(env) < exp2.eval(env))
            {
                return 1;
            }
        }
        else if (relop.equals(">"))
        {
            if (exp1.eval(env) > exp2.eval(env))
            {
                return 1;
            }
        }
        else if (relop.equals("<="))
        {
            if (exp1.eval(env) <= exp2.eval(env))
            {
                return 1;
            }
        } 
        else if (relop.equals(">="))
        {
            if (exp1.eval(env) >= exp2.eval(env))
            {
                return 1;
            }
        } 
        return -1;        
    }
}
