package ast;
import environment.Environment;

/**
 * Represents an operator.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/15/2018
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;
    
    /**
     * Constructor for objects of class BinOp
     * 
     * @param op the operator
     * @param exp1 the first expression
     * @param exp2 the second expression
     */
    public BinOp(String op, Expression exp1, Expression exp2)
    {
        this.op = op;
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
        if (op.equals("+"))
        {
            return exp1.eval(env) + exp2.eval(env);
        }
        if (op.equals("-"))
        {
            return exp1.eval(env) - exp2.eval(env);
        }
        if (op.equals("*"))
        {
            return exp1.eval(env) * exp2.eval(env);
        }
        if (op.equals("/"))
        {
            return exp1.eval(env) / exp2.eval(env);
        }        
        return exp1.eval(env) % exp2.eval(env);        
    }
}
