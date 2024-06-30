public class Xnor extends BinaryExpression{
    public Xnor(Expression left, Expression right) {
        super(left, right,'#');
    }
    public boolean calc(boolean a, boolean b) {
        return a==b;
    }
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        return new Nand(new Nand(new Nand(a, a), new Nand(b, b)), new Nand(a, b));
    }

    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        return new Nor(new Nor(a, new Nor(a, b)), new Nor(b, new Nor(a, b)));
    }
    @Override
    public Expression simplify() {
        /**
         * supports
         * x # x = 1
         */
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
