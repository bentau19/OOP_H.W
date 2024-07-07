/**
 * The Xnor class represents a logical exclusive NOR (XNOR) expression.
 * It extends the BinaryExpression class and provides methods to calculate,
 * simplify, and convert the expression to NAND or NOR equivalents.
 */
public class Xnor extends BinaryExpression {

    /**
     * Constructs a Xnor expression with the given left and right operands.
     *
     * @param left the left operand
     * @param right the right operand
     */
    public Xnor(Expression left, Expression right) {
        super(left, right, '#');
    }

    /**
     * Calculates the result of the exclusive NOR (XNOR) operation on two boolean values.
     *
     * @param a the left boolean operand
     * @param b the right boolean operand
     * @return true if both operands are equal, false otherwise
     */
    public boolean calc(boolean a, boolean b) {
        return a == b;
    }

    /**
     * Converts the Xnor expression to an equivalent NAND expression.
     *
     * @return the nandified expression
     */
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        return new Nand(new Nand(new Nand(a, a), new Nand(b, b)), new Nand(a, b));
    }

    /**
     * Converts the Xnor expression to an equivalent NOR expression.
     *
     * @return the norified expression
     */
    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        return new Nor(new Nor(a, new Nor(a, b)), new Nor(b, new Nor(a, b)));
    }

    /**
     * ok.
     * Simplifies the XNOR expression according to the following rules:
     * x # x = 1
     * @return the simplified expression
     */
    @Override
    public Expression simplify() {
        Expression a = super.getLeft().simplify();
        Expression b = super.getRight().simplify();
        if ((a.toString().equals("T") && b.toString().equals("T"))
                || (a.toString().equals("F") && b.toString().equals("F"))) {
            return new Val(true);
        }
        if ((a.toString().equals("T") && b.toString().equals("F"))
                || (a.toString().equals("F") && b.toString().equals("T"))) {
            return new Val(false);
        }
        if (a.toString().equals(b.toString())) {
            return new Val(true);
        }
        return new Xnor(a, b);
    }
}
