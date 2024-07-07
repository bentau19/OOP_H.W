/**
 * The Nand class represents a logical NAND binary expression.
 * It extends the BinaryExpression class and provides methods to calculate,
 * simplify, nandify, and norify the expression.
 */
public class Nand extends BinaryExpression {

    /**
     * Constructs a Nand expression with the given left and right expressions.
     *
     * @param left the left operand of the NAND expression
     * @param right the right operand of the NAND expression
     */
    public Nand(Expression left, Expression right) {
        super(left, right, 'A');
    }

    /**
     * Calculates the result of the NAND operation on two boolean values.
     *
     * @param a the first boolean value
     * @param b the second boolean value
     * @return the result of a NAND b
     */
    public boolean calc(boolean a, boolean b) {
        return !a || !b;
    }

    /**
     * Converts the NAND expression to an equivalent NAND expression.
     * This method returns the expression itself as it is already a NAND expression.
     *
     * @return the nandified expression
     */
    @Override
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        return new Nand(a, b);
    }

    /**
     * Converts the NAND expression to an equivalent NOR expression.
     *
     * @return the norified expression
     */
    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        Expression aa = new Nor(a, a);
        Expression bb = new Nor(b, b);
        return new Nor(new Nor(aa, bb), new Nor(aa, bb));
    }

    /**
     * ok.
     * Simplifies the NAND expression according to the following rules:
     *     x A 1 = ~(x)
     *     x A 0 = 1
     *     x A x = ~(x)
     *
     * @return the simplified expression
     */
    @Override
    public Expression simplify() {
        Expression a = super.getLeft().simplify();
        Expression b = super.getRight().simplify();
        if (a.toString().equals("F") || b.toString().equals("F")) {
            return new Val(true);
        }
        if (a.toString().equals("T") && b.toString().equals("T")) {
            return new Val(false);
        }
        if (a.toString().equals("T")) {
            return new Not(b);
        }
        if (b.toString().equals("T")) {
            return new Not(a);
        }
        if (a.toString().equals(b.toString())) {
            return new Not(a);
        }

        return new Nand(a, b);
    }
}
