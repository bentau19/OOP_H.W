import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Val class represents a boolean value expression.
 * It implements the Expression interface and provides methods to evaluate,
 * get variables, assign variables, and simplify the expression.
 */
public class Val implements Expression {
    private final boolean value;

    /**
     * Constructs a Val expression with the given boolean value.
     *
     * @param value the boolean value of this expression
     */
    public Val(boolean value) {
        this.value = value;
    }

    /**
     * Evaluates the boolean value expression with the given variable assignments.
     * This method is required by the Expression interface.
     *
     * @param assignments the variable assignments (ignored for Val)
     * @return the evaluated boolean value
     * @throws Exception if an error occurs during evaluation (not thrown by Val)
     */
    public Boolean evaluate(Map<String, Boolean> assignments) throws Exception {
        return this.evaluate();
    }

    /**
     * Evaluates the boolean value expression.
     * This method is required by the Expression interface.
     *
     * @return the evaluated boolean value
     * @throws Exception if an error occurs during evaluation (not thrown by Val)
     */
    public Boolean evaluate() throws Exception {
        return value;
    }

    /**
     * Returns an empty list of variables.
     * This method is required by the Expression interface.
     *
     * @return an empty list
     */
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    /**
     * Returns a string representation of the boolean value.
     *
     * @return "T" if the value is true, otherwise "F"
     */
    public String toString() {
        if (this.value) {
            return "T";
        }
        return "F";
    }

    /**
     * Assigns a given expression to a variable within this expression.
     * This method is required by the Expression interface.
     *
     * @param var the variable to be replaced (ignored for Val)
     * @param expr the expression to replace the variable with (ignored for Val)
     * @return this Val object itself as it cannot be modified
     */
    public Expression assign(String var, Expression expr) {
        return this;
    }

    /**
     * Converts the expression to an equivalent NOR expression.
     * This method is required by the Expression interface.
     *
     * @return a new Val expression with the same boolean value
     */
    @Override
    public Expression norify() {
        return new Val(this.value);
    }

    /**
     * Converts the expression to an equivalent NAND expression.
     * This method is required by the Expression interface.
     *
     * @return a new Val expression with the same boolean value
     */
    @Override
    public Expression nandify() {
        return new Val(this.value);
    }

    /**
     * Simplifies the expression.
     * This method is required by the Expression interface.
     *
     * @return a new Val expression with the same boolean value
     */
    @Override
    public Expression simplify() {
        return new Val(this.value);
    }
}
