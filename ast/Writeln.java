package ast;
import environment.Environment;

/**
 * Represents Writeln.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public class Writeln extends Statement
{
    private Expression exp;

    /**
     * Constructor for objects of class Writeln
     * 
     * @param exp an expression
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }
    
    /**
     * Executes a statement
     * 
     * @param env the Environment that remembers variables
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}
