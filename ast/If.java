package ast;
import environment.Environment;


/**
 * Represents the If statement.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public class If extends Statement
{
    private Expression cond;
    private Statement stmt;
    private Statement stmt2;
    
    /**
     * Constructor for objects of class If
     * 
     * @param cond a condition
     * @param stmt a statement
     */
    public If(Condition cond, Statement stmt)
    {
        this.cond = cond;
        this.stmt = stmt;
    }
    
    /**
     * Constructor for objects of class If
     * 
     * @param cond a condition
     * @param stmt a statement
     * @param stmt2 a statement following else 
     */
    public If(Condition cond, Statement stmt, Statement stmt2)
    {
        this.cond = cond;
        this.stmt = stmt;
        this.stmt2 = stmt2;
    }

    /**
     * Executes a statement
     * 
     * @param env the Environment that remembers variables
     */
    public void exec(Environment env)
    {
        if (cond.eval(env) == 1)
        {
            stmt.exec(env);
        }
        else
        {
            if (stmt2 != null)
            {
                stmt2.exec(env);
            }
        }
    }
}
