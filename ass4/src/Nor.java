/**
 * The Nor class represents a logical NOR binary expression.
 * It extends the BinaryExpression class and provides methods to calculate,
 * simplify, nandify, and norify the expression.
 */
public class Nor extends BinaryExpression {

    /**
     * Constructs a Nor expression with the given left and right expressions.
     *
     * @param left the left operand of the NOR expression
     * @param right the right operand of the NOR expression
     */
    public Nor(Expression left, Expression right) {
        super(left, right, 'V');
    }

    /**
     * Calculates the result of the NOR operation on two boolean values.
     *
     * @param a the first boolean value
     * @param b the second boolean value
     * @return the result of a NOR b
     */
    public boolean calc(boolean a, boolean b) {
        return !a && !b;
    }

    /**
     * Converts the NOR expression to an equivalent NAND expression.
     *
     * @return the nandified expression
     */
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        Expression aa = new Nand(a, a);
        Expression bb = new Nand(b, b);
        return new Nand(new Nand(aa, bb), new Nand(aa, bb));
    }

    /**
     * Converts the NOR expression to an equivalent NOR expression.
     * This method returns the expression itself as it is already a NOR expression.
     *
     * @return the norified expression
     */
    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        return new Nor(a, b);
    }
    /**
     * ok.
     * Simplifies the NOR expression according to the following rules:
     *     x V 1 = 0
     *     x V 0 = ~(x)
     *     x V x = x
     *
     * @return the simplified expression
     */
    public Expression simplify() {
        Expression a = super.getLeft().simplify();
        Expression b = super.getRight().simplify();
        if (a.toString().equals("T") || b.toString().equals("T")) {
            return new Val(false);
        }
        if (a.toString().equals("F") && b.toString().equals("F")) {
            return new Val(true);
        }
        if (a.toString().equals("F")) {
            return new Not(b);
        }
        if (b.toString().equals("F")) {
            return new Not(a);
        }
        if (a.toString().equals(b.toString())) {
            return new Not(a);
        }
        return new Nor(a, b);
    }
}
