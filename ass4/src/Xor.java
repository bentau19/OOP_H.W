/**
 * The Xor class represents a logical exclusive OR (XOR) expression.
 * It extends the BinaryExpression class and provides methods to calculate,
 * simplify, and convert the expression to NAND or NOR equivalents.
 */
public class Xor extends BinaryExpression {

    /**
     * Constructs a Xor expression with the given left and right operands.
     *
     * @param left the left operand
     * @param right the right operand
     */
    public Xor(Expression left, Expression right) {
        super(left, right, '^');
    }

    /**
     * Calculates the result of the exclusive OR (XOR) operation on two boolean values.
     *
     * @param a the left boolean operand
     * @param b the right boolean operand
     * @return true if the operands are different, false if they are the same
     */
    public boolean calc(boolean a, boolean b) {
        return a != b;
    }

    /**
     * Converts the Xor expression to an equivalent NAND expression.
     *
     * @return the nandified expression
     */
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        Expression ab = new Nand(a, b);
        return new Nand(new Nand(a, ab), new Nand(b, ab));
    }

    /**
     * Converts the Xor expression to an equivalent NOR expression.
     *
     * @return the norified expression
     */
    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        Expression aa = new Nor(a, a);
        Expression bb = new Nor(b, b);
        return new Nor(new Nor(aa, bb), new Nor(a, b));
    }

    /**
     * Simplifies in the mission the Xor expression according to specific rules.
     * @return the simplified expression
     */
    @Override
    public Expression simplify() {
        Expression a = super.getLeft().simplify();
        Expression b = super.getRight().simplify();
        if ((a.toString().equals("F") && b.toString().equals("F"))
                || (a.toString().equals("T") && b.toString().equals("T"))) {
            return new Val(false);
        }
        if ((a.toString().equals("T") && b.toString().equals("F"))
                || (a.toString().equals("F") && b.toString().equals("T"))) {
            return new Val(true);
        }
        if (a.toString().equals(b.toString())) {
            return new Val(false);
        }

        if (a.toString().equals("T")) {
            return new Not(b);
        }

        if (b.toString().equals("T")) {
            return new Not(a);
        }
        if (a.toString().equals("F")) {
            return b;
        }
        if (b.toString().equals("F")) {
            return a;
        }
        return new Xor(a, b);
    }
}
