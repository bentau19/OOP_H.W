public class Nor extends BinaryExpression{
    public Nor(Expression left, Expression right) {
        super(left, right,'V');
    }
    public boolean calc(boolean a, boolean b) {
        return !a && !b;
    }
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        Expression aa = new Nand(a, a);
        Expression bb = new Nand(b, b);
        return new Nand(new Nand(aa, bb), new Nand(aa, bb));
    }

    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        return new Nor(a,b);
    }

    public Expression simplify() {
        /**
         * supports:
         * x V 1 = 0
         * x V 0 = ~(x)
         * x V x = x
         */
        Expression a = super.getLeft().simplify();
        Expression b = super.getRight().simplify();
        if (a.toString().equals("T") || b.toString().equals("T")) {
            return new Val(false);
        }
        if (a.toString().equals("F") && b.toString().equals("F")) {
            return new Val(true);
        } else if (a.toString().equals("F")) {
            return new Not(b);
        } else if (b.toString().equals("F")) {
            return new Not(a);
        }
        if (a.toString().equals(b.toString())) {
            return new Not(a);
        }
        return new Nor(a, b);
    }
}