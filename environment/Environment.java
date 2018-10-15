package environment;
import java.util.*;

/**
 * The Environment remembers the values of variables.
 * 
 * @author Megan Tjandrasuwita
 * @version 3/16/2018
 */
public class Environment
{
    private Map<String, Integer> map;

    /**
     * Constructor for objects of class Environment
     */
    public Environment()
    {
        map = new HashMap<String, Integer>();
    }
    
    
    /**
     * Associates the given variable name with the given value
     * 
     * @param variable the name of a variable
     * @param value the given value of a variable
     */
    public void setVariable(String variable, int value)
    {
        map.put(variable, value);
    }
    
    /**
     * Returns the value associated with the given variable name
     * 
     * @param variable the given variable name
     * @return the value of the variable 
     */
    public int getVariable(String variable)
    {
        return map.get(variable);
    }
}
