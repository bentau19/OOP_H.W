public class Xor extends BinaryExpression{
    public Xor(Expression left, Expression right){
        super(left, right,'^');
    }
    public boolean calc(boolean a, boolean b) {
        return a != b;
    }
    public Expression nandify() {
        Expression a = super.getLeft().nandify();
        Expression b = super.getRight().nandify();
        Expression ab = new Nand(a, b);
        return new Nand(new Nand(a, ab), new Nand(b, ab));
    }

    @Override
    public Expression norify() {
        Expression a = super.getLeft().norify();
        Expression b = super.getRight().norify();
        Expression aa = new Nor(a, a);
        Expression bb = new Nor(b, b);
        return new Nor(new Nor(aa,bb), new Nor(a, b));
    }
    @Override
    public Expression simplify() {
        /**
         * supports
         *x ⊕ 1 =∼ (x)
         * • x ⊕ 0 = x
         * • x ⊕ x = 0
         */
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
