/**
 * The And class represents a logical AND binary expression.
 * It extends the BinaryExpression class and provides methods to calculate,
 * simplify, nandify, and norify the expression.
 */
public class And extends BinaryExpression {

    /**
     * Constructs an And expression with the given left and right expressions.
     *
     * @param left the left operand of the AND expression
     * @param right the right operand of the AND expression
     */
    public And(Expression left, Expression right) {
        super(left, right, '&');
    }

    /**
     * Calculates the result of the AND operation on two boolean values.
     *
     * @param a the first boolean value
     * @param b the second boolean value
     * @return the result of a AND b
     */
    public boolean calc(boolean a, boolean b) {
        return a && b;
    }

    /**
     * Simplifies the AND expression according to the following rules:
     * <ul>
     *     <li>x ∧ 1 = x</li>
     *     <li>x ∧ 0 = 0</li>
     *     <li>x ∧ x = x</li>
     * </ul>
     *
     * @return the simplified expression
     */
    @Override
    public Expression simplify() {
        Expression a = super.getLeft().simplify();
        Expression b = super.getRight().simplify();
        if (a.toString().equals(b.toString())) {
            return a;
        }
        if (a.toString().equals("F") || b.toString().equals("F")) {
            return new Val(false);
        }
        if (a.toString().equals("T")) {
            return b;
        }
        if (b.toString().equals("T")) {
            return a;
        }
        return new And(a, b);
    }

    /**
     * Converts the AND expression to an equivalent NAND expression.
     *
     * @return the NANDified expression
     */
    @Override
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        return new Nand(
                new Nand(a, b),
                new Nand(a, b)
        );
    }

    /**
     * Converts the AND expression to an equivalent NOR expression.
     *
     * @return the NORified expression
     */
    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        return new Nor(
                new Nor(a, a),
                new Nor(b, b)
        );
    }

}
