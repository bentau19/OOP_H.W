/**
 * The Or class represents a logical OR binary expression.
 * It extends the BinaryExpression class and provides methods to calculate,
 * simplify, nandify, and norify the expression.
 */
public class Or extends BinaryExpression {

    /**
     * Constructs an Or expression with the given left and right expressions.
     *
     * @param left the left operand of the OR expression
     * @param right the right operand of the OR expression
     */
    public Or(Expression left, Expression right) {
        super(left, right, '|');
    }

    /**
     * Converts the OR expression to an equivalent NAND expression.
     *
     * @return the nandified expression
     */
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        Expression aa = new Nand(a, a);
        Expression bb = new Nand(b, b);
        return new Nand(aa, bb);
    }

    /**
     * Converts the OR expression to an equivalent NOR expression.
     *
     * @return the norified expression
     */
    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        Expression c = new Nor(a, b);
        return new Nor(c, c);
    }

    /**
     * Calculates the result of the OR operation on two boolean values.
     *
     * @param a the first boolean value
     * @param b the second boolean value
     * @return the result of a OR b
     */
    public boolean calc(boolean a, boolean b) {
        return a || b;
    }

    /**
     * ok.
     * Simplifies the OR expression according to the following rules:
     *     x ∨ 1 = 1
     *     x ∨ 0 = x
     *     x ∨ x = x
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
        if (a.toString().equals("T") || b.toString().equals("T")) {
            return new Val(true);
        }
        if (a.toString().equals("F")) {
            return b;
        }
        if (b.toString().equals("F")) {
            return a;
        }
        return new Or(a, b);
    }
}
