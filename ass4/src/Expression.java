import java.util.List;
import java.util.Map;

/**
 * The Expression interface defines the contract for logical expressions.
 * It includes methods for evaluating, transforming, and simplifying expressions.
 */
public interface Expression {

    /**
     * Evaluates the expression using the variable values provided
     * in the assignment, and returns the result.
     * If the expression contains a variable which is not in the assignment,
     * an exception is thrown.
     *
     * @param assignment a map of variable assignments
     * @return the result of evaluating the expression
     * @throws Exception if an error occurs during evaluation
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method for evaluating the expression with an empty assignment.
     *
     * @return the result of evaluating the expression
     * @throws Exception if an error occurs during evaluation
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of variable names
     */
    List<String> getVariables();

    /**
     * Returns a string representation of the expression.
     *
     * @return a string representation of the expression
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (does not modify the
     * current expression).
     *
     * @param var the variable to be replaced
     * @param expression the expression to replace the variable with
     * @return a new expression with the variable replaced
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nand operation.
     *
     * @return the nandified expression
     */
    Expression nandify();

    /**
     * Returns the expression tree resulting from converting all the operations
     * to the logical Nor operation.
     *
     * @return the norified expression
     */
    Expression norify();

    /**
     * Simplifies the expression according to logical simplification rules.
     *
     * @return the simplified expression
     */
    Expression simplify();
}
