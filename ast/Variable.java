package ast;
import environment.Environment;

/**
 * Represents a variable.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public class Variable extends Expression
{
    private String name;

    /**
     * Constructor for objects of class Variable
     * 
     * @param name the name of the variable
     */
    public Variable(String name)
    {
        this.name = name;
    }
    
    /**
     * Evaluates an expression and returns resulting Integer
     * 
     * @param env the Environment that remembers variables
     * @return the Integer value from evaluating the expression
     */
    public Integer eval(Environment env)
    {
        return env.getVariable(name);
    }
}
