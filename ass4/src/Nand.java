public class Nand extends BinaryExpression{
    public Nand(Expression left, Expression right) {
        super(left, right,'A');
    }
    public boolean calc(boolean a, boolean b) {
        return !a || !b;
    }
    @Override
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        return new Nand(a,b);
    }

    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        Expression aa = new Nor(a, a);
        Expression bb = new Nor(b, b);
        return new Nor(new Nor(aa, bb), new Nor(aa, bb));
    }
    @Override
    public Expression simplify() {
        /**
         * supports:
         * x A 1 = ~(x)
         * x A 0 = 1
         * x A x = ~(x)
         */
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