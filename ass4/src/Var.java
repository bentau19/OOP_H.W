import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Var class represents a variable expression.
 * It implements the Expression interface and provides methods to evaluate,
 * get variables, assign variables, and simplify the expression.
 */
public class Var implements Expression {
    private final String value;

    /**
     * Constructs a Var expression with the given variable name.
     *
     * @param value the name of the variable
     */
    public Var(String value) {
        this.value = value;
    }

    /**
     * Returns a list containing the variable name.
     * This method is required by the Expression interface.
     *
     * @return a list containing the variable name
     */
    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.add(value);
        return list;
    }

    /**
     * Returns the string representation of the variable.
     *
     * @return the variable name as a string
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Evaluates the variable expression with the given variable assignments.
     * This method is required by the Expression interface.
     *
     * @param assignments the variable assignments
     * @return the evaluated boolean value of the variable
     * @throws Exception if the variable is not found in the assignments map
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignments) throws Exception {
        if (!assignments.containsKey(value)) {
            throw new Exception("Variable '" + value + "' not found in assignment");
        }
        return assignments.get(value);
    }

    /**
     * Evaluates the variable expression.
     * This method is required by the Expression interface.
     *
     * @return throws an exception as variables should not be evaluated directly
     * @throws Exception always throws an exception indicating variables should not be evaluated directly
     */
    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Variables should not be evaluated directly");
    }

    /**
     * Assigns a given expression to a variable within this expression.
     * This method is required by the Expression interface.
     *
     * @param var the variable to be replaced
     * @param expr the expression to replace the variable with
     * @return a new Var expression with the assigned variable replaced
     */
    @Override
    public Expression assign(String var, Expression expr) {
        if (var.equals(value)) {
            return expr;
        }
        return this;
    }

    /**
     * Converts the expression to an equivalent NOR expression.
     * This method is required by the Expression interface.
     *
     * @return a new Var expression with the same variable name
     */
    @Override
    public Expression norify() {
        return new Var(this.value);
    }

    /**
     * Converts the expression to an equivalent NAND expression.
     * This method is required by the Expression interface.
     *
     * @return a new Var expression with the same variable name
     */
    @Override
    public Expression nandify() {
        return new Var(this.value);
    }

    /**
     * Simplifies the expression.
     * This method is required by the Expression interface.
     *
     * @return a new Var expression with the same variable name
     */
    @Override
    public Expression simplify() {
        return new Var(this.value);
    }
}
