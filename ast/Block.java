package ast;
import java.util.*;
import environment.Environment;

/**
 * Represents a block of statements.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public class Block extends Statement
{
    private List<Statement> stmts;

    /**
     * Constructor for objects of class Block
     * 
     * @param stmts a list of statements
     */
    public Block(List<Statement> stmts)
    {
        this.stmts = stmts;
    }
    
    /**
     * Executes a statement
     * 
     * @param env the Environment that remembers variables
     */
    public void exec(Environment env)
    {
        for (int i = 0; i < stmts.size(); i++)
        {
            stmts.get(i).exec(env);
        }
    }
}
