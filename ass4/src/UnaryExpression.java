import java.util.List;
import java.util.Map;

/**
 * The UnaryExpression class represents an abstract unary expression.
 * It extends the BaseExpression class and provides common functionality
 * for unary expressions.
 */
public abstract class UnaryExpression extends BaseExpression {
    private final Expression exp;
    private final char sign;

    /**
     * Constructs a UnaryExpression with the given operand expression and sign.
     *
     * @param exp the operand expression
     * @param sign the sign representing the unary operation
     */
    public UnaryExpression(Expression exp, char sign) {
        this.exp = exp;
        this.sign = sign;
    }

    /**
     * Returns the operand expression of this unary expression.
     *
     * @return the operand expression
     */
    protected Expression getExp() {
        return exp;
    }
    /**
     * Assigns a given expression to a variable within this expression.
     *
     * @param var the variable to be replaced
     * @param exp the expression to replace the variable with
     * @return a new expression with the variable replaced
     */
    @Override
    public Expression assign(String var, Expression exp) {
        try {
            return this.getClass()
                    .getConstructor(Expression.class)
                    .newInstance(exp.assign(var, exp));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns a list of the variables in the expression.
     *
     * @return a list of variable names
     */
    public List<String> getVariables() {
        return exp.getVariables();
    }

    /**
     * Calculates the result of the unary operation on a boolean value.
     *
     * @param a the boolean value
     * @return the result of the unary operation
     */
    public abstract boolean calc(boolean a);

    /**
     * Evaluates the unary expression with the given variable assignment.
     *
     * @param assignment a map of variable assignments
     * @return the result of evaluating the expression
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return calc(exp.evaluate(assignment));
    }

    /**
     * Converts the unary expression to an equivalent NAND expression.
     *
     * @return the nandified expression
     */
    public Expression nandify() {
        Expression curexp = exp.nandify();
        return new Nand(curexp, curexp);
    }

    /**
     * Converts the unary expression to an equivalent NOR expression.
     *
     * @return the norified expression
     */
    @Override
    public Expression norify() {
        Expression curexp = exp.norify();
        return new Nor(curexp, curexp);
    }

    /**
     * Returns a string representation of the unary expression.
     *
     * @return a string representation of the expression
     */
    public String toString() {
        return sign + "(" + exp.toString() + ")";
    }
}
