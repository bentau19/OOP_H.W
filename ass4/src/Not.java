/**
 * The Not class represents a logical NOT unary expression.
 * It extends the UnaryExpression class and provides methods to calculate and simplify the expression.
 */
public class Not extends UnaryExpression {

    /**
     * Constructs a Not expression with the given operand.
     *
     * @param expr the operand of the NOT expression
     */
    public Not(Expression expr) {
        super(expr, '~');
    }

    /**
     * Calculates the result of the NOT operation on a boolean value.
     *
     * @param a the boolean value
     * @return the result of NOT a
     */
    public boolean calc(boolean a) {
        return !a;
    }

    /**
     * Simplifies the NOT expression. If the expression has a boolean value, it returns the negation of it.
     *
     * @return the simplified expression
     */
    public Expression simplify() {
        Expression a = super.getExp();
        if (a.toString().equals("F")) {
            return new Val(true);
        }
        if (a.toString().equals("T")) {
            return new Val(false);
        }
        return new Not(a);
    }
}
