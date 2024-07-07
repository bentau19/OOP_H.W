import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The BinaryExpression class represents a binary expression with a left and right operand.
 * It extends the BaseExpression class and provides common functionality for binary expressions.
 */
public abstract class BinaryExpression extends BaseExpression {
    private final Expression left;
    private final Expression right;
    private final char sign;
    /**
     * Constructs a BinaryExpression with the given left and right expressions and a sign.
     *
     * @param left the left operand of the binary expression
     * @param right the right operand of the binary expression
     * @param sign the sign representing the binary operation
     */
    public BinaryExpression(Expression left, Expression right, char sign) {
        this.left = left;
        this.right = right;
        this.sign = sign;
    }

    /**
     * Returns the left operand of the binary expression.
     *
     * @return the left operand
     */
    protected Expression getLeft() {
        return left;
    }

    /**
     * Returns the right operand of the binary expression.
     *
     * @return the right operand
     */
    protected Expression getRight() {
        return right;
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of variable names
     */
    public List<String> getVariables() {
        List<String> leftVars = this.left.getVariables();
        List<String> rightVars = this.right.getVariables();
        List<String> variables = new ArrayList<>(leftVars);
        variables.removeAll(rightVars);
        variables.addAll(rightVars);
        return variables;
    }

    /**
     * Assigns a given expression to a variable within this expression.
     *
     * @param var the variable to be replaced
     * @param expression the expression to replace the variable with
     * @return a new expression with the variable replaced
     */
    public Expression assign(String var, Expression expression) {
        try {
            Constructor<?> constructor = this.getClass().getConstructor(Expression.class, Expression.class);
            return (Expression) constructor.newInstance(left.assign(var, expression), right.assign(var, expression));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Calculates the result of the binary operation on two boolean values.
     *
     * @param a the first boolean value
     * @param b the second boolean value
     * @return the result of the binary operation
     */
    public abstract boolean calc(boolean a, boolean b);

    /**
     * Evaluates the binary expression with the given variable assignment.
     *
     * @param assignment a map of variable assignments
     * @return the result of evaluating the expression
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return calc(left.evaluate(assignment), right.evaluate(assignment));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Returns a string representation of the binary expression.
     *
     * @return a string representation of the expression
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " " + sign + " " + right.toString() + ")";
    }
}
