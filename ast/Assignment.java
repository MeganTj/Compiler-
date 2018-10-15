package ast;
import environment.Environment;

/**
 * Represents assigment of variables.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;
    
    /**
     * Constructor for objects of class Assignment
     * 
     * @param var the name of a variable
     * @param exp the expression
     */
    public Assignment(String var, Expression exp)
    {
        this.var = var;
        this.exp = exp;
    }
    
    /**
     * Executes a statement
     * 
     * @param env the Environment that remembers variables
     */
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env));
    }
}
