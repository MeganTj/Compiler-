package ast;
import environment.Environment;

/**
 * Represents the For statement.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public class For extends Statement
{
    private String name;
    private Statement assign;
    private Expression max;
    private Statement stmt;

    /**
     * Constructor for objects of class For
     * 
     * @param name the name of the variable
     * @param assign the assignment of the variable
     * @param max the maximum value to which the variable goes to
     * @param stmt the statement within the for loop
     */
    public For(String name, Statement assign, Expression max, Statement stmt)
    {
        this.name = name;
        this.assign = assign;
        this.max = max;
        this.stmt = stmt;
    }
    
    /**
     * Executes a statement
     * 
     * @param env the Environment that remembers variables
     */
    public void exec(Environment env)
    {
        assign.exec(env);
        int m = max.eval(env);
        while (env.getVariable(name) <= m)
        {
            stmt.exec(env);
            env.setVariable(name, env.getVariable(name) + 1);
        }
    }
}
