import java.util.HashMap;

/**
 * The BaseExpression class provides a base implementation of the Expression
 * interface. It includes a method to evaluate the expression without any variables.
 */
public abstract class BaseExpression implements Expression {

    /**
     * Evaluates the expression without any variables.
     * This method is intended to be overridden by subclasses if they need to
     * provide specific evaluation logic.
     *
     * @return the result of evaluating the expression
     * @throws Exception if an error occurs during evaluation
     */
    public Boolean evaluate() throws Exception {
        return evaluate(new HashMap<>());
    }
}
