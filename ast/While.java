package ast;
import environment.Environment;

/**
 * Represents the While statement.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public class While extends Statement
{
    private Expression cond;
    private Statement stmt;

    /**
     * Constructor for objects of class While
     * 
     * @param cond the condition
     * @param stmt a statement 
     */
    public While(Condition cond, Statement stmt)
    {
        this.cond = cond;
        this.stmt = stmt;
    }

    /**
     * Executes a statement
     * 
     * @param env the Environment that remembers variables
     */
    public void exec(Environment env)
    {
        while (cond.eval(env) == 1)
        {
            stmt.exec(env);
        }
    }
}
