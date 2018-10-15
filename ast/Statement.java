package ast;
import environment.Environment;

/**
 * Abstract class Statement - represents a statement.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public abstract class Statement
{
    /**
     * Executes a statement
     * 
     * @param env the Environment that remembers variables
     */
    public abstract void exec(Environment env);
}
